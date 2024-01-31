package com.example.mapd721_a1_abichitrakar_301369773.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreStudentData(private val context: Context) {

    // Companion object to create a single instance of DataStore for Students Username, Email  and ID
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("StudentsData")

        // Keys to uniquely identify user email and password in DataStore
        val STUDENTS_USERNAME = stringPreferencesKey("students_username")
        val STUDENTS_EMAIL = stringPreferencesKey("students_email")
        val STUDENTS_ID = stringPreferencesKey("students_id")
    }

    // Flow representing the student's stored username
    val getUsername: Flow<String?> = context.dataStore.data
        .map { preferences ->
            // Retrieve the stored username value or return an empty string if not present
            preferences[STUDENTS_USERNAME] ?: ""
        }

    // Flow representing the student's stored email
    val getEmail: Flow<String?> = context.dataStore.data
        .map { preferences ->
            // Retrieve the stored password value or return an empty string if not present
            preferences[STUDENTS_EMAIL] ?: ""
        }

    // Flow representing the student's stored ID
    val getID: Flow<String?> = context.dataStore.data
        .map { preferences: Preferences ->
            // Retrieve the stored ID value or return an 0 if not present
            preferences[STUDENTS_ID] ?: ""
        }

    // Function to save students username, email and id in DataStore
    suspend fun studentsData(username: String, email: String, id: String) {
        // Use the DataStore's edit function to make changes to the stored preferences
        context.dataStore.edit { preferences ->
            // Update the user email and password values in the preferences
            preferences[STUDENTS_USERNAME] = username
            preferences[STUDENTS_EMAIL] = email
            preferences[STUDENTS_ID] = id
        }
        }
        // Function to clear all stored student data
        suspend fun clearStudentData() {
            context.dataStore.edit { preferences ->
                preferences.remove(STUDENTS_USERNAME)
                preferences.remove(STUDENTS_EMAIL)
                preferences.remove(STUDENTS_ID)
            }
        }
    }


