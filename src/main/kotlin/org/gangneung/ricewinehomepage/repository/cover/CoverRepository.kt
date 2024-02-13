package org.gangneung.ricewinehomepage.repository.cover

import org.gangneung.ricewinehomepage.domain.cover.Cover
import org.springframework.data.jpa.repository.JpaRepository

interface CoverRepository : JpaRepository<Cover, Long>
