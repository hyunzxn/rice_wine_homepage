package org.gangneung.ricewinehomepage.util

import kotlin.math.max
import kotlin.math.min

data class CustomPagingRequest(
    var page: Int = 1,
    var size: Int = 10,
) {
    companion object {
        private const val MAX_SIZE = 2000
    }

    val offset: Long
        get() = (max(1, page) - 1) * min(size, MAX_SIZE).toLong()
}
