package org.sgodden.tom.model.impl

import org.sgodden.tom.model.{IAddress, IDeliveryDetails}

class DeliveryDetails extends IDeliveryDetails {

  private var address: Address = null

  def getAddress = address

  def setAddress(address: IAddress) {
    this.address = address.asInstanceOf[Address]
  }

}
