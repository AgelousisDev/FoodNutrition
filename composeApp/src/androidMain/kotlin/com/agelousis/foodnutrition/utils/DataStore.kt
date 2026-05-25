package com.agelousis.foodnutrition.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences

actual class DataStoreProvider(
    private val context: Context
) {

    actual fun createDataStore(): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.filesDir.resolve(PREFERENCES_FILENAME)
            }
        )
    }

}