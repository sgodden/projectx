package org.sgodden.tom.persistence

import com.novus.salat._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.conversions.scala._
import org.sgodden.tom.persistence.model.{BaseP, CustomerOrderP}
import org.sgodden.tom.model.{Identity, CustomerOrder, ICustomerOrder, CustomerOrderRepository}
import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.commons.MongoDBObject
import org.bson.types.ObjectId

abstract class BaseRepositoryImpl[ T <: Identity[AnyRef] ](databaseName: String) {

  val conn = MongoConnection()
  val db = conn(databaseName)
  val coll = db(collectionName)

  val collectionName: String

  def toCaseClassInstance(entity: T): BaseP[AnyRef]
  def getGrater: Grater[BaseP[AnyRef]]

  RegisterConversionHelpers()
  RegisterJodaTimeConversionHelpers()

  def remove(entity: T) {
    coll.remove(MongoDBObject("_id" -> new ObjectId(entity.id)))
  }

  def findAll = {
    coll.map(dbo => {
      getGrater.asObject(dbo).asObject
    }).toList
  }

  def persist(entity: T) {
    coll.save(getGrater.asDBObject(toCaseClassInstance(entity)))
    entity.id = coll.last.get("_id").toString
  }

  def merge(entity: T) {
    coll.save(getGrater.asDBObject(toCaseClassInstance(entity)))
  }

  def findById(id: String) =
    getGrater.asObject(coll.findOne(MongoDBObject("_id" -> new ObjectId(id))).get).asObject

  def count = {
    coll.size
  }

}
