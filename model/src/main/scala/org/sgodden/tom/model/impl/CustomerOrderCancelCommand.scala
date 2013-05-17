package org.sgodden.tom.model.impl

import org.sgodden.tom.model.CustomerOrderStatus

class CustomerOrderCancelCommand {

  def cancel(order: CustomerOrder) {
    order.setStatus(CustomerOrderStatus.CANCELLED)
  }

}
