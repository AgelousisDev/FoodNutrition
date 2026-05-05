package com.agelousis.kotlinmultiplatform.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import java.io.File

actual class DataStoreProvider {
    actual fun createDataStore(): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                // Stores data in user home folder, e.g., ~/.local/share/yourapp/
                val dataDir = File(System.getProperty("user.home"), ".yourapp")
                if (!dataDir.exists()) dataDir.mkdirs()
                File(dataDir, PREFERENCES_FILENAME)
            }
        )
    }
}