package org.sgodden.tom.model

import org.joda.time.DateTime
import EventType._

/**
 * An event that occurs at a place and time.
 * <p>
 *   Currently a value object.
 * </p>
 */
trait IEvent {

  def location: String

  def time: DateTime

  def eventType: EventType

}
