package org.sgodden.tom.persistence.model

import org.sgodden.tom.model.{IScanner, Scanner}

class ScannerP extends BaseP[Scanner]{
  def asObject: Scanner = null // TODO - implement
}

object ScannerP {
  def apply(scanner: IScanner): ScannerP = null // TODO - implement
}
