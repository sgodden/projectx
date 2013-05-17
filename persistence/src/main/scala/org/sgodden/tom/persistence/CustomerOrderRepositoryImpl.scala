package org.sgodden.tom.persistence

import org.sgodden.tom.persistence.model.CustomerOrderP
import org.sgodden.tom.model.{CustomerOrderRepository, ICustomerOrder}
import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.query.Imports._

class CustomerOrderRepositoryImpl(databaseName: String) extends BaseRepositoryImpl[ICustomerOrder] (databaseName, "customerOrders")
    with CustomerOrderRepository {

  private val _grater = grater[CustomerOrderP]

  def toDBobject(entity: ICustomerOrder): DBObject = _grater.asDBObject(CustomerOrderP(entity))
  def toObject(dbObj: DBObject): ICustomerOrder = _grater.asObject(dbObj).asObject
}
