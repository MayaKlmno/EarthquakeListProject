package com.example.earthquakelistproject

import android.content.Intent
import android.icu.util.TimeZone
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class EarthquakeAdapter (var earthquakeList: List<Feature>) :
        RecyclerView.Adapter<EarthquakeAdapter.ViewHolder>() {

    /**
     * provide a reference to the type of view that you are using
     * (custom ViewHolder)
     */

    //put all of the widgets and wire them
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewMagnitude: TextView
        val textViewlocation: TextView
        val textViewTime: TextView

        // to enable clicking on an item
        // add the code for the layout
        var layoutEarthquake: ConstraintLayout

        init {
            textViewMagnitude = view.findViewById(R.id.textView_earthquake_magnitude)
            textViewlocation = view.findViewById(R.id.textView_earthquake_location)
            textViewTime = view.findViewById(R.id.textView_earthquake_date)
            layoutEarthquake = view.findViewById(R.id.layout_earthquake)
        }
    }

    // create new views (invoked by the layout manager)
    // this is where you choose the layout to use (item_hero.xml)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_earthquake, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return earthquakeList.size
    }


    // Replace the contents of a view (invoked by the layout manager)
    // put onClickListeners here if needed
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // get elements from your dataset at this position
        // and replace the contents of the view with that element
        //earthquakeList[position].properties.
        val entry = earthquakeList[position]
        val context = viewHolder.textViewMagnitude.context

        viewHolder.textViewMagnitude.text = earthquakeList[position].properties.mag.toString().substring(0,3)
        viewHolder.textViewTime.text = earthquakeList[position].properties.time.toString()
        viewHolder.textViewlocation.text = earthquakeList[position].properties.place

        //val context = viewHolder.layoutEarthquake.context



        val formatter = DateTimeFormatter.ofPattern("MMM dd, YYYY, hh:mm:ss a")
        val time = entry.properties.time
        val utcTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.of("UTC"))
        viewHolder.textViewTime.text = formatter.format(utcTime)
        val magnitude = entry.properties.mag


        viewHolder.layoutEarthquake.setOnClickListener {
            val detailIntent = Intent(context, EarthquakeMapActivity::class.java)
            detailIntent.putExtra(EarthquakeMapActivity.EXTEA_FEATURE, earthquakeList[position])
            context.startActivity(detailIntent)
        }

        //viewHolder.layoutEarthquake.setOnClickListener{}

        // Example Code:
        // This is the code like not the specific code, but you have to tweak it
        // you can keep on using the .thenBy{} and keep on like comparing it
        // over and over and over again
        //adapter.dataset = adapter.dataSet.sortedWith(compareBy<Feature> {it.properties.title}.thenBy{it.properties.time})


        /*
        if (earthquakeList[position].properties.mag < 0){
            earthquakeList.remove()
        }
         */

        when {
            earthquakeList[position].properties.mag > 6.5 -> {
                viewHolder.textViewMagnitude.setTextColor(
                    context.resources.getColor(
                        R.color.significant,
                        context.theme
                    )
                )
                viewHolder.textViewMagnitude.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.baseline_error_outline_24,
                    0,
                    0,
                    0
                )
            }
            4.6 < earthquakeList[position].properties.mag  && earthquakeList[position].properties.mag < 6.5 -> {
                viewHolder.textViewMagnitude.setTextColor(
                    context.resources.getColor(
                        R.color.large,
                        context.theme
                    )
                )
                viewHolder.textViewMagnitude.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.baseline_error_24,
                    0,
                    0,
                    0
                )
            }
            2.5 < earthquakeList[position].properties.mag && earthquakeList[position].properties.mag < 4.6 -> {
                viewHolder.textViewMagnitude.setTextColor(
                    context.resources.getColor(
                        R.color.moderate,
                        context.theme
                    )
                )
            }
            1.0 < earthquakeList[position].properties.mag && earthquakeList[position].properties.mag < 2.5 -> {
                viewHolder.textViewMagnitude.setTextColor(
                    context.resources.getColor(
                        R.color.small,
                        context.theme
                    )
                )
            }
        }
    }
}