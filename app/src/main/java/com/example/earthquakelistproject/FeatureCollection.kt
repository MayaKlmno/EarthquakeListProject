package com.example.earthquakelistproject

// it should be parcelized, along with some other classes
//@Parcelize
data class FeatureCollection(
// TODO: add something here
    var metadata: MetaData,
    var features: List<Feature>
)