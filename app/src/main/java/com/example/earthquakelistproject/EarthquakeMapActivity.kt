package com.example.earthquakelistproject

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.osmdroid.config.Configuration.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker


class EarthquakeMapActivity: AppCompatActivity() {
    companion object{
        val EXTEA_FEATURE = "feature"
        val TAG = "EarthquakeMapActivity"
    }
    //private val REQUEST_PERMISSIONS_REQUEST_CODE = 1
    private lateinit var map : MapView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inflate and create the map
        enableEdgeToEdge()
        setContentView(R.layout.menu_earthquake_map)

        //handle permissions first, before map is created. not depicted here

        //load/initialize the osmdroid configuration, this can be done
        // This won't work unless you have imported this: org.osmdroid.config.Configuration.*
        ////getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils
        //note, the load method also sets the HTTP User Agent to your application's package name, if you abuse osm's
        //tile servers will get you banned based on this string.


        map = findViewById<MapView>(R.id.map)
        map.setTileSource(TileSourceFactory.MAPNIK)
        val earthquake = intent.getParcelableExtra<Feature>(EXTEA_FEATURE)

        val mapController = map.controller
        mapController.setZoom(11)
        val startPoint = GeoPoint(earthquake?.geometry?.coordinates?.get(1)!!, earthquake.geometry.coordinates.get(0));
        mapController.setCenter(startPoint)


        getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this))

        val startMarker = Marker(map)
        startMarker.position= startPoint
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        startMarker.title = earthquake?.properties?.place.toString()
        map.getOverlays().add(startMarker)
        supportActionBar!!.title = "${earthquake.properties.place}"
        supportActionBar!!.subtitle = "${earthquake.properties.url}"
    }

    override fun onResume() {
        super.onResume()
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume() //needed for compass, my location overlays, v6.0.0 and up
    }

    override fun onPause() {
        super.onPause()
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause()  //needed for compass, my location overlays, v6.0.0 and up
    }
}