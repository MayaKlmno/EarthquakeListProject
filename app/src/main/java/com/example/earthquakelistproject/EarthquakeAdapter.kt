package com.example.earthquakelistproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class EarthquakeAdapter (var earthquakeList: List<Feature>) :
        RecyclerView.Adapter<EarthquakeAdapter.ViewHolder>() {

    /**
     * provide a reference to the type of view that you are using
     * (custom ViewHolder)
     */

    //TODO: put all of the widgets and wire them
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textViewMagnitude: TextView
        val textViewlocation: TextView
        val textViewTime: TextView
        // to enable clicking on an item
        //var layout: ConstraintLayout

        init{
            textViewMagnitude = view.findViewById(R.id.textView_earthquake_magnitude)
            textViewlocation = view.findViewById(R.id.textView_earthquake_location)
            textViewTime = view.findViewById(R.id.textView_earthquake_date)
            //layout = view.findViewById(R.id.)
        }
    }

    // create new views (invoked by the layout manager)
    // this is where you choose the layout to use (item_hero.xml)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_earthquake, viewGroup, false )
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
        viewHolder.textViewMagnitude.text = earthquakeList[position].toString()


        // Example Code:
        // This is the code like not the specific code, but you have to tweak it
        // you can keep on using the .thenBy{} and keep on like comparing it
            // over and over and over again
        //adapter.dataset = adapter.dataSet.sortedWith(compareBy<Feature> {it.properties.title}.thenBy{it.properties.time})


        /*
        when {
            magnitude < 6.5 -> {
                 holder.magTextView.setTextColor(context.resources.getColor(R.color.significant, context.theme))
                 holder.magTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0 ,0 ,0)
            }
        */
    }

}