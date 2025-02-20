package com.example.earthquakelistproject
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// it should be parcelize, along with some other classes
@Parcelize
data class Properties(
    var mag: Double,
    var title: String,
    var place: String,
    var time: Long,
    var url: String
):Parcelable
