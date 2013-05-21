package org.sgodden.tom.services.customerorder

import org.sgodden.tom.model.ITrip

trait TripService extends BaseService[ITrip] {

  def departed(id: String)

  def arrived(id: String)

}
