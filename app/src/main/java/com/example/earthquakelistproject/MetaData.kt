package com.example.earthquakelistproject
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// it should be parcelize, along with some other classes
@Parcelize
data class MetaData(
    var count: Int,
    var title: String,
    var status: String
):Parcelable
