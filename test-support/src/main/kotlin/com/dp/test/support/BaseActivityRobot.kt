package com.dp.test.support

import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import com.dp.test.support.CustomDrawableMatchers.Companion.withAppCompatDrawable
import com.dp.test.support.ToolbarMatchers.Companion.withToolbarTitle
import org.hamcrest.Matchers

@Suppress("UNCHECKED_CAST")
abstract class BaseActivityRobot<T> {

    fun launchesActivity(rule: IntentsTestRule<*>): T {
        rule.launchActivity(null)
        return this as T
    }

    fun launchesActivityWithIntent(rule: IntentsTestRule<*>, intent: Intent?): T {
        rule.launchActivity(intent)
        return this as T
    }

    fun launchesActivityWithIntent(rule: DpActivityTestRule<*>, intent: Intent?): T {
        rule.launchActivity(intent)
        return this as T
    }

    // Observation
    protected fun seesTitle(
        @IdRes toolbarId: Int,
        @StringRes titleResId: Int
    ): T {
        Espresso.onView(ViewMatchers.withId(toolbarId))
            .check(ViewAssertions.matches(withToolbarTitle(titleResId)))
        return this as T
    }

    protected fun seesTitle(@IdRes toolbarId: Int, title: String?): T {
        Espresso.onView(ViewMatchers.withId(toolbarId))
            .check(ViewAssertions.matches(withToolbarTitle(title!!)))
        return this as T
    }

    fun seesBackButton(): T {
        Espresso.onView(withContentDescription("Navigate up")).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        return this as T
    }

    protected fun seesCloseButton(@DrawableRes closeDrawableId: Int): T {
        Espresso.onView(
            Matchers.allOf(
                withAppCompatDrawable(closeDrawableId),
                withContentDescription("Navigate up")
            )
        )
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        return this as T
    }

    // Interactions
    fun clicksUpButton(): T {
        Espresso.onView(withContentDescription("Navigate up"))
            .perform(ViewActions.click())
        return this as T
    }

    fun clicksBackButton(): T {
        ViewActions.pressBack()
        return this as T
    }

    protected fun clicksCloseButton(@DrawableRes closeDrawableId: Int): T {
        Espresso.onView(
            Matchers.allOf(
                withAppCompatDrawable(closeDrawableId),
                withContentDescription("Navigate up")
            )
        )
            .perform(ViewActions.click())
        return this as T
    }

    fun doesNotSeeLoadingSpinner(): T {
        Espresso.onView(ViewMatchers.withId(spinnerId)).check(ViewAssertions.doesNotExist())
        return this as T
    }

    @get:IdRes
    protected abstract val spinnerId: Int
}
