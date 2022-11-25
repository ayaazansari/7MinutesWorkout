package com.example.a7minutesworkout.db

import android.app.Application

class WorkoutApp: Application() {
    val db by lazy {
        WorkoutDatabase.getInstance(this)
    }
}