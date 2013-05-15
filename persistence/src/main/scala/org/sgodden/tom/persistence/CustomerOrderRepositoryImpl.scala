package org.sgodden.tom.persistence

import org.sgodden.tom.persistence.model.{BaseP, CustomerOrderP}
import org.sgodden.tom.model.{CustomerOrder, ICustomerOrder}
import com.novus.salat._
import com.novus.salat.global._

class CustomerOrderRepositoryImpl(databaseName: String) extends BaseRepositoryImpl[CustomerOrder] (databaseName) {
  val collectionName: String = "customerOrders"
  def toCaseClassInstance(entity: CustomerOrder) = CustomerOrderP(entity).asInstanceOf[BaseP[AnyRef]]
  def getGrater = grater[CustomerOrderP].asInstanceOf[Grater[BaseP[AnyRef]]]
}
