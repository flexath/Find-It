package com.flexath.findit.news.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.flexath.findit.core.utils.Constants.APP_ENTRY_SETTING
import com.flexath.findit.core.utils.Constants.USER_SETTING
import com.flexath.findit.news.data.manager.LocalUserManagerImpl.PreferencesKeys.APP_ENTRY
import com.flexath.findit.news.domain.manager.LocalUserManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : LocalUserManager {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTING)

    private object PreferencesKeys {
        val APP_ENTRY = booleanPreferencesKey(APP_ENTRY_SETTING)
    }

    override suspend fun saveAppEntry() {
        context.dataStore.edit {
            it[APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[APP_ENTRY] ?: false
        }
    }
}