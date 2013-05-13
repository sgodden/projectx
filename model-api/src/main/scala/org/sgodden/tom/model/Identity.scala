package org.sgodden.tom.model

import org.apache.commons.lang.builder.{HashCodeBuilder, EqualsBuilder}


trait Identity[T] extends Serializable {

  def id: String

  override def equals(other: Any): Boolean = {
    if (other == null
      || !other.isInstanceOf[Identity[T]] ) {
      return false
    }

    val o = other.asInstanceOf[Identity[T]]

    if (o.id == 0 || id == 0) {
      return super.equals(other)
    }
    else {
      return new EqualsBuilder().append(this.id, o.id).isEquals
    }
  }

  override def hashCode: Int = {
    if (id == null) {
      return super.hashCode
    }
    return new HashCodeBuilder(23, 61).append(id).toHashCode
  }
}
