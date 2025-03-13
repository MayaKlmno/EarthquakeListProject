package com.example.earthquakelistproject

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.inputmethod.InputBinding
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakelistproject.databinding.ActivityEarthquakeListBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.LinearLayoutManager


//TODO: set up viewbinding

class EarthquakeListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEarthquakeListBinding
    private lateinit var adapter: EarthquakeAdapter

    companion object{
        val TAG = "EarthquakeListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEarthquakeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        /*
        var gson = Gson()
        val inputStream = resources.openRawResource(R.raw.earthquake)
        val jsonString = inputStream.bufferedReader().use {
            it.readText()
        }

         */

        // create our service object
        val earthquakeService = RetrofitHelper.getInstance().create(EarthquakeService::class.java)

        val earthquakeCall = earthquakeService.getAllDayEarthquakeData()

        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)


        earthquakeCall.enqueue(object: Callback<FeatureCollection> {

            override fun onResponse(call: Call<FeatureCollection>, response: Response<FeatureCollection>) {
                //TODO:
                // This is where the code goes for when you get your data
                // create your recyclerview adapter HERE
                response.body() //--> gives you the actual data (in this case the FeatureCollection)
                // if you want to see the json that came back --> response.raw()
                val earthquake = response.body()!!
                adapter = EarthquakeAdapter(earthquake.features.filter{it.properties.mag > 1.0})
                binding.recyclerViewEarthquakeListEarthquakes.adapter = adapter
                binding.recyclerViewEarthquakeListEarthquakes.layoutManager = LinearLayoutManager(this@EarthquakeListActivity)


                /*
                // Naming the action bar
                if(response.body[] != null){
                    supportActionBar?.title = "Earthquake App"
                    supportActionBar?.subtitle = response.body()?.metadata?.title
                }
                 */

            }
            override fun onFailure(call: Call<FeatureCollection>, t: Throwable) {
                Log.d("EarthquakeList", "OnFailure: ${t.message}") // similar to the soundboards
            }

        })
        // if you try creating your adapter here, we can't guarantee that the network call has finished
        // so the list might be empty
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_earthquakelist, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            //TODO: Check to see if when you sort by mag, you want it to be smalles --> big or big --> small
            R.id.sort_by_magnitude -> {
                adapter.earthquakeList = adapter.earthquakeList.sortedBy {it.properties.mag} // or .sorted() for natural order
                adapter.notifyDataSetChanged()
                true
            }
            R.id.sort_by_recent -> {
                adapter.earthquakeList = adapter.earthquakeList.sortedBy {-it.properties.time}
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}