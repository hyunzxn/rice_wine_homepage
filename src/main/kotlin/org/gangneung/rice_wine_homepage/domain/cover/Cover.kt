package org.gangneung.rice_wine_homepage.domain.cover

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob

@Entity
class Cover(
    @Lob
    @Column(nullable = false)
    var content: String,

    @Column(nullable = false)
    var address: String,

    @Column(nullable = false)
    var callNumber: String,

    var snsName: String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    companion object {
        fun of(
            content: String,
            snsName: String,
            address: String,
            callNumber: String,
        ): Cover {
            return Cover(
                content = content,
                snsName = snsName,
                address = address,
                callNumber = callNumber
            )
        }
    }
}