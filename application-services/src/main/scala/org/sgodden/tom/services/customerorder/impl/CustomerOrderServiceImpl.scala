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
    eventFactory: EventFactory) extends BaseServiceImpl[ICustomerOrder](orderRepo, orderFactory) with CustomerOrderService {

  def scan(orderId: String, scannerId: String, eventType: EventType) {
    val order = findById(orderId)
    val scanner = scannerRepo.findById(scannerId)
    order.addEvent(eventFactory.create(scanner.location, new DateTime, eventType))
    orderRepo.merge(order)
  }
}
