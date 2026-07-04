package com.agelousis.foodnutrition.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
private var dataStore: DataStore<Preferences>? = null

actual class DataStoreProvider {

    @OptIn(ExperimentalForeignApi::class)
    actual fun createDataStore(): DataStore<Preferences> {
        return dataStore ?: PreferenceDataStoreFactory.createWithPath(
            produceFile = {
                val directory = NSFileManager.defaultManager.URLForDirectory(
                    directory = NSDocumentDirectory,
                    inDomain = NSUserDomainMask,
                    appropriateForURL = null,
                    create = false,
                    error = null
                )
                (directory?.path + "/$PREFERENCES_FILENAME").toPath()
            }
        ).also {
            dataStore = it
        }
    }

}
