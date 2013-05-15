package org.sgodden.tom.persistence.model

abstract class BaseP[T] {
  def asObject: T
}