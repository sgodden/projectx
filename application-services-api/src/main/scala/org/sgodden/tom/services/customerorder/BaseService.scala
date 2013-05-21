package org.sgodden.tom.services.customerorder

trait BaseService[T] {

  def create: T

  def persist(entity: T): String

  def remove(id: String)

  def findAll: List[T]

  def merge(entity: T)

  def findById(id: String): T


}
