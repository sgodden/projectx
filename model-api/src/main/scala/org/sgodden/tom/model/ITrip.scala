package org.sgodden.tom.model

import org.joda.time.DateTime

trait ITrip extends Identity[ITrip] {

  var scheduledDepartureTime: DateTime

  var scheduledArrivalTime: DateTime

  val allocations: Set[ITripAllocation]
  def addAllocation(allocation: ITripAllocation)
  def removeAllocation(allocation: ITripAllocation)

}
