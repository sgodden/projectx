package org.sgodden.tom.model.impl

trait ValidatingEntity {

  def validate()

  def approvePersist(onApprove: () => Unit)

  def approveRemove(onApprove: () => Unit)

}
