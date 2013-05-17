package org.sgodden.tom.persistence

import com.novus.salat._
import com.novus.salat.global._
import org.sgodden.tom.persistence.model.ScannerP
import org.sgodden.tom.model.{ScannerRepository, Scanner, IScanner}
import com.mongodb.casbah.query.Imports._

class ScannerRepositoryImpl(databaseName: String) extends BaseRepositoryImpl[IScanner](databaseName, "scanners")
    with ScannerRepository {

  private val _grater = grater[ScannerP]

  def toDBobject(entity: IScanner): DBObject = _grater.asDBObject(ScannerP(entity))
  def toObject(dbObj: DBObject): IScanner = _grater.asObject(dbObj).asObject
}
