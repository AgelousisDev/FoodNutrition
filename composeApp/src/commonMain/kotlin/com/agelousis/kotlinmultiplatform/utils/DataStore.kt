package com.agelousis.kotlinmultiplatform.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

expect class DataStoreProvider {
    fun createDataStore(): DataStore<Preferences>
}


const val PREFERENCES_FILENAME = "preferences.preferences_pb"