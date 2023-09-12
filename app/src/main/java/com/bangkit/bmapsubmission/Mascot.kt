package com.bangkit.bmapsubmission

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Mascot(
    val name: String,
    val description: String,
    val photo: String,
    val winner: String,
    val bestPlayer: String,
    val topScore: String
) : Parcelable
