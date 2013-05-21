package org.sgodden.tom.persistence

import com.novus.salat._
import com.novus.salat.global._
import org.sgodden.tom.persistence.model.TripP
import org.sgodden.tom.model.{TripRepository, ITrip, ScannerRepository, IScanner}
import com.mongodb.casbah.query.Imports._

class TripRepositoryImpl(databaseName: String) extends BaseRepositoryImpl[ITrip](databaseName, "trips")
    with TripRepository {

  private val _grater = grater[TripP]

  def toDBobject(entity: ITrip): DBObject = _grater.asDBObject(TripP(entity))
  def toObject(dbObj: DBObject): ITrip = _grater.asObject(dbObj).asObject
}
