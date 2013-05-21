package org.sgodden.tom.model.impl

import org.sgodden.tom.model.{EventType, IEvent, EventFactory}
import org.joda.time.DateTime

class EventFactoryImpl extends EventFactory {
  def create(location: String, time: DateTime, eventType: EventType.EventType): IEvent =
    new Event(location, time, eventType)
}
