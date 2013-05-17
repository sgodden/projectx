package org.sgodden.tom.model.impl

import org.sgodden.tom.model.{ICustomerOrder, CustomerOrderFactory}

class CustomerOrderFactoryImpl extends CustomerOrderFactory {
  override def create: ICustomerOrder = {
    new CustomerOrder
  }
}
