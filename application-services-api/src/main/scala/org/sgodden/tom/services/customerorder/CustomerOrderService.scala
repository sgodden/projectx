package org.sgodden.tom.services.customerorder

import org.sgodden.tom.model.ICustomerOrder
import org.sgodden.tom.model.EventType._

trait CustomerOrderService extends BaseService[ICustomerOrder]{

  /**
   * Adds an event to the order based on the location of the scanner, and the current time.
   * TODO - surely we would scan at package level.
   * @param orderId the id of the order.
   * @param scannerId the id of the scanner which scanned the event.
   * @param eventType the type of event that occurred.
   */
  def scan(orderId: String, scannerId: String, eventType: EventType)
}
