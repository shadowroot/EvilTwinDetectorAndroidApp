package xyz.p2psw.eviltwindetector.lib

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

class WifiScanner(context: Context){
    val TAG = "WIFISCANNER"
    val  context = context
    val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val scanResults = LinkedList<List<ScanResult>>()
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    fun scan()
    {
        val wifiScanReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context, intent: Intent) {
                val success = intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)
                if (success) {
                    scanSuccess()
                } else {
                    scanFailure()
                }
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        context.registerReceiver(wifiScanReceiver, intentFilter)

        val success = wifiManager.startScan()
        if (!success) {
            scanFailure()
        }
    }

    private fun getDistance(scanResult: ScanResult) : Double{
        return _guessDistance(scanResult.level, scanResult.frequency)
    }

    private fun _guessDistance(level: Int, freq: Int) : Double{
        val exp: Double = (27.55 - 20 * Math.log10(freq.toDouble()) + Math.abs(level)) / 20.0
        return Math.pow(10.0, exp)
    }

    private fun getBSSID(scanResult: ScanResult) : String?{
        return scanResult.BSSID
    }

    private fun getSSID(scanResult: ScanResult) : String?{
        return scanResult.SSID
    }



    private fun scanSuccess() {
        val results = wifiManager.scanResults
        scanResults.add(results)
    }

    private fun scanFailure() {
        // handle failure: new scan did NOT succeed
        // consider using old scan results: these are the OLD results!
        val results = wifiManager.scanResults
        if(results != null) {
            Log.e(TAG, "Wifi scan failed " + results?.toString())
        }
        else{
            Log.e(TAG, "Wifi scan failed")
        }
    }
}