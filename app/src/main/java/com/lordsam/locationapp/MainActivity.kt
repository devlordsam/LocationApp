package com.lordsam.locationapp

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val pCode = 77

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val f1 = LocationServices.getFusedLocationProviderClient(this)

        button2.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    pCode
                )
            } else {
                f1.lastLocation.addOnSuccessListener { location: Location ->
                    txt.text = "${location.longitude} ${location.latitude}"
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == pCode){
            if (grantResults != null && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Denied", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}