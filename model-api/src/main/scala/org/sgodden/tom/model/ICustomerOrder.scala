package org.sgodden.tom.model

import org.joda.time.LocalDate

trait ICustomerOrder extends Identity[ICustomerOrder] {

  def cancel

  def confirm

  def ship

  var bookingDate: LocalDate

  def getCustomerReference: String
  def setCustomerReference(reference: String)

  var orderNumber: String

  def getCollectionDetails: ICollectionDetails
  def setCollectionDetails(details: ICollectionDetails)

  def getDeliveryDetails: IDeliveryDetails
  def setDeliveryDetails(details: IDeliveryDetails)

  def getOrderLines: Set[ICustomerOrderLine]
  def addOrderLine(line: ICustomerOrderLine)
  def removeOrderLine(line: ICustomerOrderLine)

  def getEvents: Set[IEvent]
  def addEvent(event: IEvent)
  def removeEvent(event: IEvent)

  def packages: Set[IPackage]
  def addPackage(pkg: IPackage)
  def removePackage(pkg: IPackage)

  def getStatus: CustomerOrderStatus.Value
}
