package org.sgodden.tom.persistence

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.conversions.scala._
import org.sgodden.tom.model._
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
    val _persist = () => {
      coll.save(toDBobject(entity))
      entity.id = coll.last.get("_id").toString
    }

    if (entity.isInstanceOf[ValidatingEntity]) {
      entity.asInstanceOf[ValidatingEntity].approvePersist(_persist)
    }
    else {
      _persist
    }
  }

  def merge(entity: T) {
    val _merge = () => {
      coll.save(toDBobject(entity))
    }: Unit

    if (entity.isInstanceOf[ValidatingEntity]) {
      entity.asInstanceOf[ValidatingEntity].approvePersist(_merge)
    }
    else {
      _merge
    }
  }

  def findById(id: String) =
    toObject(coll.findOne(MongoDBObject("_id" -> new ObjectId(id))).get)

  def count: Long = {
    coll.size
  }

}
