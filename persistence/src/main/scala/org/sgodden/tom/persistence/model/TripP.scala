package org.sgodden.tom.persistence.model

import org.sgodden.tom.model.impl.Trip
import org.sgodden.tom.model.ITrip


class TripP extends BaseP[Trip]{
  def asObject: Trip = null // TODO - implement
}

object TripP {
  def apply(scanner: ITrip): TripP = null // TODO - implement
}