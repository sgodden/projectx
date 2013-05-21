package org.sgodden.tom.services.customerorder.impl

import org.sgodden.tom.services.customerorder.CustomerOrderService
import org.sgodden.tom.model.EventType._
import org.sgodden.tom.model._
import org.springframework.stereotype.Service
import org.sgodden.tom.model.EventType.EventType
import org.joda.time.DateTime

@Service
class CustomerOrderServiceImpl(
    orderRepo: CustomerOrderRepository,
    scannerRepo: ScannerRepository,
    orderFactory: CustomerOrderFactory,
    eventFactory: EventFactory) extends CustomerOrderService {

  override def create: ICustomerOrder = orderFactory.create

  override def persist(customerOrder: ICustomerOrder) = {
    orderRepo.persist(customerOrder)
    customerOrder.id
  }

  override def remove(id: String) = orderRepo.remove(orderRepo.findById(id))

  override def findAll: List[ICustomerOrder] = orderRepo.findAll

  override def merge(order: ICustomerOrder) =  orderRepo merge order

  override def findById(id: String): ICustomerOrder = orderRepo.findById(id)

  def scan(orderId: String, scannerId: String, eventType: EventType) {
    val order = findById(orderId)
    val scanner = scannerRepo.findById(scannerId)
    order.addEvent(eventFactory.create(scanner.location, new DateTime, eventType))
    orderRepo.merge(order)
  }
}
