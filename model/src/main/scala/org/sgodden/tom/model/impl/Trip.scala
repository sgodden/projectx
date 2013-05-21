package org.sgodden.tom.model.impl

import org.sgodden.tom.model.{ITripAllocation, ITrip}
import org.joda.time.DateTime
import collection.mutable.{HashSet, Set => MutableSet}

class Trip extends ITrip with Identifiable {
  var scheduledDepartureTime: DateTime = null
  var scheduledArrivalTime: DateTime = null

  private val _allocations: MutableSet[TripAllocation] = new HashSet[TripAllocation]

  val allocations: Set[ITripAllocation] = _allocations.toSet

  def addAllocation(allocation: ITripAllocation) {
    _allocations.add(allocation.asInstanceOf[TripAllocation])
  }
  def removeAllocation(allocation: ITripAllocation) {
    _allocations.remove(allocation.asInstanceOf[TripAllocation])
  }
}
