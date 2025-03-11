package com.example.earthquakelistproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// it should be parcelize, along with some other classes
@Parcelize
data class Feature(
    var properties: Properties,
    var geometry: Geometry
):Parcelable
