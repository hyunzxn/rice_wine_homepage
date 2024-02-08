package org.gangneung.rice_wine_homepage.domain.cover

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ForeignKey
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class CoverImage private constructor(
    @Column(nullable = false)
    val imageUrl: String,

    @Column(nullable = false)
    val title: String,

    @ManyToOne
    @JoinColumn(name = "cover_id", foreignKey = ForeignKey(name = "fk_cover_image"))
    val cover: Cover,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    companion object {
        fun createInstance(imageUrl: String, title: String, cover: Cover): CoverImage {
            return CoverImage(imageUrl = imageUrl, title = title, cover = cover)
        }
    }
}