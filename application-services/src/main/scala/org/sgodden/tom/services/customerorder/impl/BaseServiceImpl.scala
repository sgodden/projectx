package org.sgodden.tom.services.customerorder.impl

import org.sgodden.tom.model.{Identity, BaseFactory, BaseRepository}
import org.sgodden.tom.services.customerorder.BaseService

 class BaseServiceImpl[T <: Identity[T]](
                                   repository: BaseRepository[T],
                                   factory: BaseFactory[T]
                                   ) extends BaseService[T] {
   def create: T = factory.create

   def persist(entity: T): String = {
     repository.persist(entity)
     entity.id
   }

   def remove(id: String) { repository.remove(repository.findById(id)) }

   def findAll: List[T] = repository.findAll

   def merge(entity: T) { repository.merge(entity) }

   def findById(id: String): T = repository.findById(id)
 }
