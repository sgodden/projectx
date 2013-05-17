package org.sgodden.tom.services.customerorder.impl

import org.sgodden.tom.services.customerorder.CustomerOrderService
import org.sgodden.tom.model.ScanEventType._
import org.sgodden.tom.model.{ICustomerOrder, CustomerOrderFactory, CustomerOrderRepository}
import org.springframework.stereotype.Service

@Service
class CustomerOrderServiceImpl(
    repository: CustomerOrderRepository,
    factory: CustomerOrderFactory) extends CustomerOrderService {

  override def create: ICustomerOrder = factory.create

  override def persist(customerOrder: ICustomerOrder) = {
    repository.persist(customerOrder)
    customerOrder.id
  }

  override def remove(id: String) = repository.remove(repository.findById(id))

  override def findAll: List[ICustomerOrder] = repository.findAll

  override def merge(order: ICustomerOrder) =  repository merge order

  override def findById(id: String): ICustomerOrder = repository.findById(id)

  /**
   *
   * @param orderId
   * @param scannerId
   * @param eventType
   */
  def scan(orderId: String, scannerId: String, eventType: ScanEventType) {
    eventType match {
      case LOAD => println("LOAD!!")
      case UNLOAD => println("UNLOAD!!")
    }
  }
}
