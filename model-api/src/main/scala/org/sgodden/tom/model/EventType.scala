package org.sgodden.tom.model

object EventType extends Enumeration("LOAD", "UNLOAD") {
  type EventType = Value
  val LOAD, UNLOAD = Value
}
