package org.sgodden.tom.persistence

import com.novus.salat._
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.conversions.scala._
import org.sgodden.tom.persistence.model.{BaseP, CustomerOrderP}
import org.sgodden.tom.model.{Identity, CustomerOrder, ICustomerOrder, CustomerOrderRepository}
import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.commons.MongoDBObject
import org.bson.types.ObjectId

abstract class BaseRepositoryImpl[ T <: Identity[T] ](databaseName: String, collectionName: String) {

  val conn = MongoConnection()
  val db = conn(databaseName)
  val coll = db(collectionName)

  def toDBobject(entity: T): DBObject
  def toObject(dbObj: DBObject): T

  RegisterConversionHelpers()
  RegisterJodaTimeConversionHelpers()

  def remove(entity: T) {
    coll.remove(MongoDBObject("_id" -> new ObjectId(entity.id)))
  }

  def findAll = {
    coll.map(dbo => {
      toObject(dbo)
    }).toList
  }

  def persist(entity: T) {
    coll.save(toDBobject(entity))
    entity.id = coll.last.get("_id").toString
  }

  def merge(entity: T) {
    coll.save(toDBobject(entity))
  }

  def findById(id: String) =
    toObject(coll.findOne(MongoDBObject("_id" -> new ObjectId(id))).get)

  def count: Long = {
    coll.size
  }

}
