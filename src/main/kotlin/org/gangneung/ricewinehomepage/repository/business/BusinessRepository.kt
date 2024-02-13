package org.gangneung.ricewinehomepage.repository.business

import org.gangneung.ricewinehomepage.domain.business.Business
import org.springframework.data.jpa.repository.JpaRepository

interface BusinessRepository : JpaRepository<Business, Long>
