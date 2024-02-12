package org.gangneung.ricewinehomepage.domain.cover

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob

@Entity
class Cover private constructor(
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
        fun createInstance(
            content: String,
            address: String,
            callNumber: String,
            snsName: String,
        ): Cover {
            return Cover(
                content = content,
                address = address,
                callNumber = callNumber,
                snsName = snsName,
            )
        }
    }

    fun modify(
        content: String,
        address: String,
        callNumber: String,
        snsName: String,
    ) {
        this.content = content
        this.address = address
        this.callNumber = callNumber
        this.snsName = snsName
    }
}
