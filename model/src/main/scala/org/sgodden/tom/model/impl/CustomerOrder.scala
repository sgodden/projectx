package org.sgodden.tom.model.impl

import javax.validation.constraints.{Pattern,NotNull}
import collection.mutable.{HashSet, Set => MutableSet}
import org.springframework.beans.factory.annotation.{Configurable, Autowired}
import java.util.{Date, Map => JavaMap}
import org.joda.time.LocalDate
import scala.collection.{mutable, JavaConverters}
import JavaConverters._
import javax.validation.{ConstraintViolation, Validation}
import org.slf4j.{LoggerFactory, Logger}
import org.sgodden.tom.model._

@Configurable
class CustomerOrder() extends ICustomerOrder with Identifiable with ValidatingEntity {

  @NotNull
  @Pattern(regexp = "cr.*", message = "{customerReferenceMustBeginWithCr}")
  var customerReference: String = null
  private var _orderNumber: String = null

  /*
   * Example of information hiding - the trait defines a var but we are still in control :)
   */
  def orderNumber: String = { _orderNumber }
  def orderNumber_=(value: String) {
    println("Setting order number to: " + value)
    this._orderNumber = value
  }

  @NotNull
  var status: CustomerOrderStatus.Value = CustomerOrderStatus.NEW
  @NotNull
  var bookingDate: LocalDate = new LocalDate(new Date().getTime)
  var collectionDetails: CollectionDetails = null
  var deliveryDetails: DeliveryDetails = null
  val orderLines: MutableSet[CustomerOrderLine] = new HashSet[CustomerOrderLine]
  val events: MutableSet[Event] = new HashSet[Event]
  val _packages: MutableSet[Packige] = new HashSet[Packige]

  @Autowired
  private var stateObjects: JavaMap[String, CustomerOrderState] = null

  /*
   * Method here purely to satisfy hibernate validator which insists on JavaBeans conventions, so if you
   * are doing the information hiding tricks above you need to provide a method like this on which to put
   * your validator annotations.
   */
  @NotNull
  def getOrderNumber: String = {
    _orderNumber
  }

  override def cancel {
    getStateObject.cancel(this)
  }

  override def confirm {
    getStateObject.confirm(this)
  }

  override def ship {
    getStateObject.ship(this)
  }

  def approveRemove(onApprove: () => Unit) {
    onApprove()
  }

  def approvePersist(onApprove: () => Unit) {
    validate()
    onApprove()
  }

  def validate() {
    val constraints = CustomerOrder.validator.validate(this).asScala.toSet
    if (constraints.size > 0) {
      if (CustomerOrder.LOG.isDebugEnabled) {
        for (violation <- constraints) {
          CustomerOrder.LOG.debug(violation.toString)
        }
      }
      throw new ValidationException(constraints.asInstanceOf[Set[ConstraintViolation[AnyRef]]])
    }

  }

  override def getCustomerReference = customerReference
  override def setCustomerReference(customerReference: String) {
    this.customerReference = customerReference
  }

  override def getCollectionDetails: ICollectionDetails = collectionDetails
  override def setCollectionDetails(collectionDetails: ICollectionDetails) {
    this.collectionDetails = collectionDetails.asInstanceOf[CollectionDetails]
  }

  override def getDeliveryDetails: IDeliveryDetails = deliveryDetails
  override def setDeliveryDetails(deliveryDetails: IDeliveryDetails) {
    this.deliveryDetails = deliveryDetails.asInstanceOf[DeliveryDetails]
  }

  override def getOrderLines: Set[ICustomerOrderLine] = orderLines.toSet
  override def addOrderLine(line: ICustomerOrderLine) {
    orderLines.add(line.asInstanceOf[CustomerOrderLine])
  }
  override def removeOrderLine(line: ICustomerOrderLine) {
    orderLines.remove(line.asInstanceOf[CustomerOrderLine])
  }

  override def getEvents: Set[IEvent] = events.toSet
  override def addEvent(event: IEvent) {
    events.add(event.asInstanceOf[Event])
  }
  override def removeEvent(event: IEvent) {
    events.remove(event.asInstanceOf[Event])
  }

  override def packages: Set[IPackage] = packages.toSet
  override def addPackage(pkg: IPackage) {
    _packages.add(pkg.asInstanceOf[Packige])
  }
  override def removePackage(pkg: IPackage) {
    _packages.remove(pkg.asInstanceOf[Packige])
  }

  override def getStatus: CustomerOrderStatus.Value = status
  def setStatus(status: CustomerOrderStatus.Value) {
    this.status = status
  }

  private def getStateObject: CustomerOrderState = stateObjects.get(getStatus.toString)

  def getStateObjects: JavaMap[String, CustomerOrderState] = stateObjects
  def setStateObjects(stateObjects: JavaMap[String, CustomerOrderState]) {
    this.stateObjects = stateObjects
  }
}

object CustomerOrder {
  private final val LOG: Logger = LoggerFactory.getLogger(classOf[CustomerOrder])
  val validator = Validation.buildDefaultValidatorFactory.getValidator
}
