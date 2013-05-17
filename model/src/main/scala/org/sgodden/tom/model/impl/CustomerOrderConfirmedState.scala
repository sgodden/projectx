package org.sgodden.tom.model.impl

import org.springframework.stereotype.Component
import org.sgodden.tom.model.CustomerOrderStatus

@Component
class CustomerOrderConfirmedState extends AbstractCustomerOrderState {

  def confirm(order: CustomerOrder) {
    throw new IllegalStateException("An order may not be confirmed from the confirmed state")
  }

  def ship(order: CustomerOrder) {
    throw new IllegalStateException("An order may not be shipped from the requested state")
  }

  def cancel(order: CustomerOrder) {
    order setStatus CustomerOrderStatus.CANCELLED
  }

  override def save(order: CustomerOrder) {
    order setStatus CustomerOrderStatus.REQUESTED
  }

  def willEnter(order: CustomerOrder) = true

  def willLeave(order: CustomerOrder) = true
}
