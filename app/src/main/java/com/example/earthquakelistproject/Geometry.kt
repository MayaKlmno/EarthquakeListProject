package com.example.earthquakelistproject
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// it should be parcelize, along with some other classes
@Parcelize
data class Geometry(
    var coordinates: List<Int>
):Parcelable
