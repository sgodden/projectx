package org.sgodden.tom.model

trait ValidatingEntity {

  def validate()

  def approvePersist(onApprove: () => Unit)

  def approveRemove(onApprove: () => Unit)

}
