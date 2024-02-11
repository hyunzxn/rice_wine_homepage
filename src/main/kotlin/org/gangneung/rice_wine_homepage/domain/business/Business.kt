package org.gangneung.rice_wine_homepage.domain.business

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Lob

@Entity
class Business private constructor(
  @Column(nullable = false)
  val name: String,

  @Column(nullable = false)
  val contact: String,

  @Column(nullable = false)
  val email: String,

  @Lob
  @Column(nullable = false)
  val content: String,

  @Id
  val id: Long? = null,
) {
  companion object {
    fun createInstance(
      name: String,
      contact: String,
      email: String,
      content: String): Business {
      return Business(
        name = name,
        contact = contact,
        email = email,
        content = content
      )
    }
  }
}