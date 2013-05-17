package org.sgodden.tom.model.impl

abstract class AbstractCustomerOrderState extends CustomerOrderState {

  def save(order: CustomerOrder)

}
