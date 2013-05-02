package org.sgodden.tom.model

import org.apache.commons.lang.builder.{HashCodeBuilder, EqualsBuilder}


trait Identity[T] extends Serializable {

  def getId: String

  override def equals(other: Any): Boolean = {
    if (other == null
      || !other.isInstanceOf[Identity[T]] ) {
      return false
    }

    val o = other.asInstanceOf[Identity[T]]

    if (o.getId == 0 || getId == 0) {
      return super.equals(other)
    }
    else {
      return new EqualsBuilder().append(this.getId, o.getId).isEquals
    }
  }

  override def hashCode: Int = {
    if (getId == null) {
      return super.hashCode
    }
    return new HashCodeBuilder(23, 61).append(getId).toHashCode
  }
}
