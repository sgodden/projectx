package org.sgodden.tom.model.impl

import javax.validation.constraints.NotNull
import org.sgodden.tom.model.IAddress

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
