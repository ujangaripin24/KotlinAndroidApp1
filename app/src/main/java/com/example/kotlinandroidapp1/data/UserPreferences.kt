package com.example.kotlinandroidapp1.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("user_preferences")

object UserPreferencesKeys {
    val ONBOARDING = booleanPreferencesKey("on_boarding")
    var TERMANDPOLICY = booleanPreferencesKey("term_and_policy")
}

class UserPreferences(private val context: Context) {
    val onboardingCompleted: Flow<Boolean> = context.dataStore.data.map{prefs ->
        prefs[UserPreferencesKeys.ONBOARDING] ?: false
    }

    val termandpolicyCompleted: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[UserPreferencesKeys.TERMANDPOLICY] ?: false
    }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[UserPreferencesKeys.ONBOARDING] = completed
        }
    }

    suspend fun setTermAndPolicyCompleted(completed: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[UserPreferencesKeys.TERMANDPOLICY] = completed
        }
    }
}