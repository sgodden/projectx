package org.sgodden.tom.model.impl

trait CustomerOrderState {

  def cancel(order: CustomerOrder)

  def confirm(order: CustomerOrder)

  def ship(order: CustomerOrder)

}
