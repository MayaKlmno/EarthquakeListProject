package com.example.earthquakelistproject

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputBinding
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakelistproject.databinding.ActivityEarthquakeListBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//TODO: set up viewbinding

class EarthquakeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEarthquakeListBinding

    companion object{
        val TAG = "HeroListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEarthquakeListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // create our service object
        val earthquakeService = RetrofitHelper.getInstance().create(EarthquakeService::class.java)

        val earthquakeCall = earthquakeService.getAllDayEarthquakeData()

        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        earthquakeCall.enqueue(object: Callback<FeatureCollection> {
            override fun onResponse(call: Call<FeatureCollection>, response: Response<FeatureCollection>) {
                TODO("Not yet implemented")
                // This is where the code goes for when you get your data
                // create your recyclerview adapter HERE
                //response.body() --> gives you the actual data (in this case the FeatureCollection)
                // if you want to see the json that came back --> response.raw()


            }

            override fun onFailure(call: Call<FeatureCollection>, t: Throwable) {
                Log.d("EarthquakeList", "OnFailure: ${t.message}") // similar to the soundboards
            }

        })
        // if you try creating your adapter here, we can't guarantee that the network call has finished
        // so the list might be empty

    }
}