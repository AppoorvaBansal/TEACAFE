package com.google.developers.teacup.data

enum class TeaType(val value: String) {
    GREEN_TEA("Green Tea"),
    HERBAL_TEA("Herbal Tea"),
    BLACK_TEA("Black Tea");

    companion object {
        fun findByName(name: String?) = when (name) {
            GREEN_TEA.value -> GREEN_TEA
            HERBAL_TEA.value -> HERBAL_TEA
            BLACK_TEA.value -> BLACK_TEA
            else -> BLACK_TEA
        }
    }
}