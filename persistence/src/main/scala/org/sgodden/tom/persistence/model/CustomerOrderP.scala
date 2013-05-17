package org.sgodden.tom.persistence.model

import org.bson.types.ObjectId
import org.joda.time.{DateTimeZone, LocalDate, DateTime}
import org.sgodden.tom.model.{ICustomerOrderLine, CustomerOrderLine, CustomerOrder, ICustomerOrder}
import com.mongodb.DBObject
import scala.collection.mutable
import com.novus.salat._
import com.novus.salat.global._

case class CustomerOrderP (
    _id: ObjectId,
    customerReference: String = null,
    orderNumber: String = null,
    bookingDate: DateTime = null,
    orderLines: Set[CustomerOrderLine]
  ) extends BaseP[CustomerOrder] {

  implicit def dateTimeToLocalDate(dateTime: DateTime) = {
    new LocalDate(dateTime.getMillis)
  }

  def asObject = {
    val ret = new CustomerOrder
    ret.id = _id.toString
    ret.setCustomerReference(customerReference)
    ret.orderNumber = orderNumber
    ret.bookingDate = bookingDate
    orderLines.foreach(line => {
      ret.addOrderLine(line)
    })
    ret
  }
}

object CustomerOrderP {

  implicit def localDateToDateTime(localDate: LocalDate) = {
    new DateTime(localDate.toDateTimeAtStartOfDay(DateTimeZone.forID("ETC/Utc")).getMillis)
  }

  def apply(order: ICustomerOrder) = {
    new CustomerOrderP(
      _id = {if (order.id != null) new ObjectId(order.id) else null},
      customerReference = order.getCustomerReference,
      orderNumber = order.orderNumber,
      bookingDate = order.bookingDate,

      /*
       * Relationships - ones that are already case classes just need casting to the concrete class
       */
      orderLines = order.getOrderLines.map(line => line.asInstanceOf[CustomerOrderLine])
    )
  }
}
