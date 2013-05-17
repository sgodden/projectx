package org.sgodden.tom.model.impl

import org.sgodden.tom.model.ICustomerOrderLine

case class CustomerOrderLine(
    packageType: String,
    descriptionOfGoods: String)
  extends ICustomerOrderLine {

}
