package org.sgodden.tom.model.impl

import org.sgodden.tom.model.ITripAllocation

case class TripAllocation(
                           customerOrderId: String,
                           numberOfPackages: Int) extends ITripAllocation {
}
