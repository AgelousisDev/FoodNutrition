package com.agelousis.kotlinmultiplatform.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

expect class DataStoreProvider {
    fun createDataStore(): DataStore<Preferences>
}

//region Model
suspend inline fun <reified T> DataStore<Preferences>.saveModel(
    key: Preferences.Key<String>,
    model: T
) {
    edit { preferences ->
        preferences[key] = jsonWorker.encodeToString(
            value = model
        )
    }
}

inline fun <reified T> DataStore<Preferences>.getModel(
    key: Preferences.Key<String>
): Flow<T?> = data.map { preferences ->
    preferences[key]?.toModel<T>()
}
//endregion
suspend inline fun <reified T> DataStore<Preferences>.addModel(
    key: Preferences.Key<String>,
    model: T
) {
    edit { preferences ->
        // Read the current list inside the edit block for atomicity
        val currentJson = preferences[key]
        val currentList = currentJson?.toModel<List<T>>() ?: emptyList()

        if (model !in currentList) {
            val newList = currentList + model
            preferences[key] = jsonWorker.encodeToString(
                value = newList
            )
        }
    }
}

suspend inline fun <reified T> DataStore<Preferences>.removeModel(
    key: Preferences.Key<String>,
    model: T
) {
    edit { preferences ->
        val currentJson = preferences[key]
        val currentList = currentJson?.toModel<List<T>>() ?: emptyList()

        if (model in currentList) {
            val newList = currentList - model
            preferences[key] = jsonWorker.encodeToString(
                value = newList
            )
        }
    }
}

suspend inline infix fun <reified T> DataStore<Preferences>.removeModels(
    key: Preferences.Key<String>
) {
    edit { preferences ->
        preferences[key] = jsonWorker.encodeToString(
            value = emptyList<T>()
        )
    }
}

inline fun <reified T> DataStore<Preferences>.getModels(
    key: Preferences.Key<String>
): Flow<List<T>> = data.map { preferences ->
    preferences[key]?.toModel<List<T>>() ?: emptyList()
}
//region List

//endregion

const val PREFERENCES_FILENAME = "preferences.preferences_pb"