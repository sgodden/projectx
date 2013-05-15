package org.sgodden.tom.model

trait BaseRepository[T] {

  def remove(entity: T)

  def findAll: List[T]

  def persist(entity: T)

  def merge(entity: T)

  def findById(id: String): T

  def count: Long

}
