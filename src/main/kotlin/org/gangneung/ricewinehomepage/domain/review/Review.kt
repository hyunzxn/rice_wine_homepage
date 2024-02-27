package org.gangneung.ricewinehomepage.domain.review

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Lob
import jakarta.persistence.ManyToOne
import org.gangneung.ricewinehomepage.domain.BaseTimeEntity
import org.gangneung.ricewinehomepage.domain.user.User
import java.time.LocalDate

@Entity
class Review private constructor(
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false)
    @Lob
    var content: String,
    @Column(nullable = false)
    val buyDate: LocalDate,
    @Column(nullable = false)
    var grade: Int,
    @Column(nullable = false)
    var viewCount: Int = 0,
    @Column(nullable = false)
    var likeCount: Int = 0,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) : BaseTimeEntity() {
    companion object {
        fun createInstance(
            title: String,
            content: String,
            buyDate: LocalDate,
            grade: Int,
            user: User,
        ): Review {
            return Review(
                title = title,
                content = content,
                buyDate = buyDate,
                grade = grade,
                user = user,
            )
        }
    }

    fun updateViewCount() {
        viewCount++
    }

    fun addLike() {
        likeCount++
    }

    fun cancelLike() {
        likeCount--
    }
}
