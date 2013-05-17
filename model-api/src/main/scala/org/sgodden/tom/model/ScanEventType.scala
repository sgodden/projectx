package org.sgodden.tom.model

object ScanEventType extends Enumeration("LOAD", "UNLOAD") {
  type ScanEventType = Value
  val LOAD, UNLOAD = Value
}
