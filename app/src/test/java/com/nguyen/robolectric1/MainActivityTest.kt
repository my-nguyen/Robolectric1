package com.nguyen.robolectric1

import android.content.Intent
import android.widget.Button
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    @Test
    fun clickingLogin_shouldStartSecondActivity() {
        Robolectric.buildActivity(MainActivity::class.java).use { controller ->
            controller.setup() // Moves Activity to RESUMED state
            val activity = controller.get()
            activity.findViewById<Button>(R.id.login).performClick()
            val expected = Intent(activity, SecondActivity::class.java)
            val actual = shadowOf(RuntimeEnvironment.application).nextStartedActivity
            assertEquals(expected.component, actual.component)
        }
    }
}