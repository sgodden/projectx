package org.sgodden.tom.model

import javax.validation.constraints.NotNull

case class Address (
  @NotNull
  line1: String,
  line2: String,
  line3: String,
  line4: String,
  town: String,
  postalCode: String
) extends IAddress {
}
