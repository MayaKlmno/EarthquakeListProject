package com.example.earthquakelistproject

import retrofit2.Call
import retrofit2.http.GET

interface EarthquakeService {
    // the argument for the get annotation is what comes after the base URL
    // and is the rest of the path to the endpoint
    @GET("summary/all_day.geojson")
    fun getAllDayEarthquakeData() : Call<FeatureCollection>
                                    // TODO: make the quakes.json folder
                                    // TODO: the "type": "FeatureCollection",
    // note that in Call<Type>, Type is the top-level JSON object/array
    // that you are retrieving from the api
    // a call to the pokemon API might get you Call<List<Movies>>
}