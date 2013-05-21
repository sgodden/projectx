package org.sgodden.tom.model

import org.joda.time.DateTime
import org.sgodden.tom.model.EventType._

trait EventFactory {
  def create(location: String, time: DateTime, eventType: EventType): IEvent
}

