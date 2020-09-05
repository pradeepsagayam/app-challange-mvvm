package com.dp.test.support

import android.app.Activity
import android.content.Intent
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.rule.ActivityTestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class DpActivityTestRule<T : Activity?>(activityClass: Class<T>?) :

    ActivityTestRule<T>(activityClass, false, false) {
    fun launchActivity(): T {
        return super.launchActivity(null)
    }

    override fun launchActivity(intent: Intent?): T {
        return super.launchActivity(intent)
    }

    fun <A : Activity?> checkActivityLaunched(activity: Class<A>) {
        Intents.intended(IntentMatchers.hasComponent(activity.name))
    }

    fun <V> checkIntentHasExtra(key: String?, value: V) {
        Intents.intended(IntentMatchers.hasExtra(key, value))
    }

    override fun apply(
        base: Statement,
        description: Description
    ): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                Intents.init()
                try {
                    base.evaluate()
                } finally {
                    Intents.release()
                }
            }
        }
    }
}
