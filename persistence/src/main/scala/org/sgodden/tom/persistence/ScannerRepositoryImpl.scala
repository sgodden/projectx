package org.sgodden.tom.persistence

import com.novus.salat._
import com.novus.salat.global._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.conversions.scala._
import org.sgodden.tom.persistence.model.{BaseP, ScannerP, CustomerOrderP}
import org.sgodden.tom.model.{IScanner, CustomerOrder, ICustomerOrder, CustomerOrderRepository}
import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.commons.MongoDBObject
import org.bson.types.ObjectId

class ScannerRepositoryImpl(databaseName: String) extends BaseRepositoryImpl[IScanner](databaseName) {
  val collectionName: String = "scanners"
  def toCaseClassInstance(entity: IScanner) = ScannerP(entity).asInstanceOf[BaseP[AnyRef]]

  def getGrater: Grater[BaseP[AnyRef]] = grater[ScannerP].asInstanceOf[Grater[BaseP[AnyRef]]]
}
