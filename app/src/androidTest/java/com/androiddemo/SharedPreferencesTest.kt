package com.androiddemo

import android.content.Context
import android.content.SharedPreferences
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.Exception


/**
 * Tests that modify the shared preferences.
 */
@SmallTest
class SharedPreferencesTest {
    private var sharedPreferences: SharedPreferences? = null
    @Before
    fun before() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Test
    @Throws(Exception::class)
    fun put_and_get_preference() {
        val string1 = "test"
        sharedPreferences!!.edit().putString(KEY_PREF, string1).apply()
        val string2 = sharedPreferences!!.getString(KEY_PREF, "")

        // Verify that the received data is correct.
        Assert.assertEquals(string1, string2)
    }

    @After
    fun after() {
        sharedPreferences!!.edit().putString(KEY_PREF, null).apply()
    }

    companion object {
        private const val PREFS_NAME = "prefs"
        private const val KEY_PREF = "KEY_PREF"
    }
}