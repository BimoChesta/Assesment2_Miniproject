package com.bimo0064.miniproject2.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bimo0064.miniproject2.model.Pembayaran
import com.bimo0064.miniproject2.model.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "day_data_store")

class DataStoreManager(private val context: Context) {

    private val paymentsKey = stringPreferencesKey("payments")

    suspend fun savePayment(pembayaran: Pembayaran) {
        context.dataStore.edit { preferences ->
            val existingPaymentsJson = preferences[paymentsKey] ?: ""
            val paymentsList = if (existingPaymentsJson.isEmpty()) {
                mutableListOf()
            } else {
                Gson().fromJson(existingPaymentsJson, Array<Pembayaran>::class.java).toMutableList()
            }
            paymentsList.add(pembayaran)
            preferences[paymentsKey] = Gson().toJson(paymentsList)
        }
    }

    suspend fun loadPayments(): List<Pembayaran> {
        val preferences = context.dataStore.data.first()
        val paymentsJson = preferences[paymentsKey] ?: return emptyList()
        return Gson().fromJson(paymentsJson, Array<Pembayaran>::class.java).toList()
    }

    private val userKey = stringPreferencesKey("user")

    suspend fun saveUser(user: User) {
        context.dataStore.edit { preferences ->
            preferences[userKey] = Gson().toJson(user)
        }
    }

    suspend fun loadUser(): User? {
        val preferences = context.dataStore.data.first()
        val userJson = preferences[userKey] ?: return null
        return Gson().fromJson(userJson, User::class.java)
    }

    private fun generateKey(month: String, year: String): String {
        return "${month}_${year}"
    }
}