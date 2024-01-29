package org.gangneung.rice_wine_homepage.presentation.cover.request

data class CoverModify(
    val content: String,
    val address: String,
    val callNumber: String,
    val snsName: String,
) {
    fun isNotValidated(): Boolean {
        return this.content.isBlank() || this.address.isBlank() || this.callNumber.isBlank()
    }
}