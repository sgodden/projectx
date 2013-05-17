package org.sgodden.tom.services.customerorder

import org.sgodden.tom.model.ICustomerOrder
import org.sgodden.tom.model.ScanEventType._

trait CustomerOrderService {

  def create: ICustomerOrder

  def persist(customerOrder: ICustomerOrder): String

  def remove(id: String)

  def findAll: List[ICustomerOrder]

  def merge(order: ICustomerOrder)

  def findById(id: String): ICustomerOrder

  /**
   *
   * @param orderId
   * @param scannerId
   * @param eventType
   */
  def scan(orderId: String, scannerId: String, eventType: ScanEventType)
}
