package org.sgodden.tom.model.impl

import org.springframework.stereotype.Component
import org.sgodden.tom.model.CustomerOrderStatus

@Component
class CustomerOrderConfirmCommand {

  private[model] def execute(order: CustomerOrder) {
    order.setStatus(CustomerOrderStatus.CONFIRMED)
  }

}
