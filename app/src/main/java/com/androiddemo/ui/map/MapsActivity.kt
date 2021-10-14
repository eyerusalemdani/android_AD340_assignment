package com.androiddemo.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.androiddemo.R
import com.androiddemo.databinding.ActivityMapsBinding
import com.androiddemo.model.Traffic
import com.androiddemo.utils.Constants
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var data : ArrayList<Traffic.Feature>
    private var context = this
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var lat = "-34.0"
    private var lng = "151.0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = resources.getString(R.string.traffic_cam_map)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        val extras = intent.extras
        data = extras?.get(Constants.DATA) as ArrayList<Traffic.Feature>

        fetchLocation()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun checkLocationPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        val result1 = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
    }

    private fun fetchLocation() {
        var lastLocation: Location?
//        if(checkLocationPermission()) {
//            fusedLocationClient?.lastLocation!!.addOnCompleteListener(context) { task ->
//                if (task.isSuccessful && task.result != null) {
//                    lastLocation = task.result
//
//                    lat = (lastLocation)!!.latitude.toString()
//                    lng = (lastLocation)!!.longitude.toString()
//
//                }
//
//                // Add a marker in Sydney and move the camera
//                val latLng = LatLng(lat.toDouble(), lng.toDouble())
//                mMap.addMarker(MarkerOptions().position(latLng).title("Marker in Sydney"))
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
//            }
//        }
//        else {
//            locationPermission()
//        }
    }

    private fun locationPermission() {

        val permissionListenerLocation: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                fetchLocation()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
            }
        }

        TedPermission.with(context)
            .setPermissionListener(permissionListenerLocation)
            .setRationaleTitle(R.string.rationale_title)
            .setRationaleMessage(R.string.rationale_message_location)
            .setDeniedTitle("Permission denied")
            .setDeniedMessage(
                "If you reject permission, you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]"
            )
            .setGotoSettingButtonText("Settings")
            .setPermissions(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .check()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val latLng = LatLng(lat.toDouble(), lng.toDouble())
        mMap.addMarker(MarkerOptions().position(latLng).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
    }
}