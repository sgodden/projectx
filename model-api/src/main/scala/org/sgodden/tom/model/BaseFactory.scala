package org.sgodden.tom.model

trait BaseFactory[T] {

  def create: T

}
