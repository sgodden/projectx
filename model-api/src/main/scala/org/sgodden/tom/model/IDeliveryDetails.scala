package org.sgodden.tom.model

trait IDeliveryDetails {
  def getAddress: IAddress
  def setAddress(address: IAddress)
}
