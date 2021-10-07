package xyz.p2psw.eviltwindetector.lib

import android.provider.BaseColumns

class DataLocation {
    object WifiLocationContract {
        // Table contents are grouped together in an anonymous object.
        object WifiLocation : BaseColumns {
            const val TABLE_NAME = "wifi"
            const val COLUMN_NAME_WIFI_SSID = "wifi_ssid"
            const val COLUMN_NAME_WIFI_BSSID = "wifi_bssid"
            const val COLUMN_NAME_WIFI_CHANNEL = "wifi_channel"
            const val COLUMN_NAME_WIFI_FREQ = "wifi_freq" //freq
            const val COLUMN_NAME_WIFI_LEVEL = "wifi_level" //dBm - signal
            const val COLUMN_NAME_LOCATION_LONGTITUDE = "location_longtitude"
            const val COLUMN_NAME_LOCATION_LATITUDE = "location_latitude"

        }

        private const val SQL_CREATE_TABLE =
            "CREATE TABLE ${WifiLocation.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${WifiLocation.COLUMN_NAME_WIFI_SSID} TEXT," +
                    "${WifiLocation.COLUMN_NAME_WIFI_BSSID} TEXT)," +
                    "${WifiLocation.COLUMN_NAME_WIFI_CHANNEL} TEXT," +
                    "${WifiLocation.COLUMN_NAME_WIFI_FREQ} INTEGER)," +
                    "${WifiLocation.COLUMN_NAME_WIFI_LEVEL} REAL)," +
                    "${WifiLocation.COLUMN_NAME_LOCATION_LONGTITUDE} REAL," +
                    "${WifiLocation.COLUMN_NAME_LOCATION_LATITUDE} REAL," +

        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${WifiLocation.TABLE_NAME}"
    }


}