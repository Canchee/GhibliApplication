package com.iacanched.GhibliApp.utils

val Int.toHoursAndMinutes: String
    get() {
        val hours: Int = this / 60
        val min: Int = this % 60

        return "$hours h $min min"
    }

val Int.toRatingStars: Float
    get() {
        return this/20f
    }



