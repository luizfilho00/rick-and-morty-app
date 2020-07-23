package br.com.mouzinho.rxarch

import android.content.Context

class CacheManager(private val context: Context) {

    private val PREFS_NAME = "RX_ARCH_PERSISTENCE"

    private val sharedPreferences by lazy {
        with(context.applicationContext) {
            getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        }
    }

    fun persistVariable(className: String, variable: String) {
        sharedPreferences.edit().putString(className, variable).apply()
    }

    fun getPersistedVariable(className: String) = sharedPreferences.getString(className, null)
}
