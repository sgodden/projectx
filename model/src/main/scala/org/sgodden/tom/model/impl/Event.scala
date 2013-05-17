package org.sgodden.tom.model.impl

import org.sgodden.tom.model.IEvent
import org.sgodden.tom.model.EventType._
import org.joda.time.DateTime

case class Event(
                  location: String,
                  time: DateTime,
                  eventType: EventType)
  extends IEvent {
}
