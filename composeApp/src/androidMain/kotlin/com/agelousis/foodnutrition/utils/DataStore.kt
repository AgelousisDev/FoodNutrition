package com.agelousis.foodnutrition.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences

actual class DataStoreProvider(
    private val context: Context
) {

    actual fun createDataStore(): DataStore<Preferences> {
        return dataStore ?: synchronized(
            lock = lock
        ) {
            dataStore ?: PreferenceDataStoreFactory.create(
                produceFile = {
                    context.applicationContext.filesDir.resolve(PREFERENCES_FILENAME)
                }
            ).also { dataStore = it }
        }
    }

    companion object {
        private var dataStore: DataStore<Preferences>? = null
        private val lock = Any()
    }

}
