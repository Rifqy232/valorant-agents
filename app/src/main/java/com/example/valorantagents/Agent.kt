package com.example.valorantagents

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agent(
    val name: String,
    val description: String,
    val photo: String,
    val voiceline: String
) : Parcelable