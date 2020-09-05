package com.dp.test.support

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.*
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.platform.app.InstrumentationRegistry
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import org.hamcrest.CustomMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

class CustomDrawableMatchers {

    companion object {

        fun withAppCompatDrawable(@DrawableRes drawableId: Int): Matcher<View> {
            return object : CustomMatcher<View>("with drawable $drawableId") {
                override fun matches(item: Any): Boolean {
                    val drawable =
                        getDrawable(item) ?: return false
                    val expectedDrawable =
                        AppCompatResources
                            .getDrawable((item as View).context, drawableId)
                    return matchDrawable(
                        drawable,
                        expectedDrawable
                    )
                }
            }
        }

        fun withDrawable(@DrawableRes drawableId: Int): Matcher<View> {
            return object : CustomMatcher<View>("with drawable $drawableId") {
                override fun matches(item: Any): Boolean {
                    val drawable =
                        getDrawable(item)
                    val expectedCompatDrawable =
                        AppCompatResources
                            .getDrawable((item as View).context, drawableId)
                    val expectedDrawable =
                        item.context.getDrawable(drawableId)
                    return matchDrawable(
                        drawable,
                        expectedCompatDrawable
                    ) || matchDrawable(
                        drawable,
                        expectedDrawable
                    )
                }
            }
        }

        fun withActionIconDrawable(@DrawableRes resourceId: Int): Matcher<View> {
            return object :
                BoundedMatcher<View, ActionMenuItemView>(
                    ActionMenuItemView::class.java
                ) {
                override fun describeTo(description: Description) {
                    description.appendText("has image drawable resource $resourceId")
                }

                public override fun matchesSafely(actionMenuItemView: ActionMenuItemView): Boolean {
                    val expectedDrawable =
                        AppCompatResources
                            .getDrawable(actionMenuItemView.context, resourceId)
                    @SuppressLint("RestrictedApi") val itemDrawable =
                        actionMenuItemView.itemData.icon
                    return matchDrawable(
                        expectedDrawable,
                        itemDrawable
                    )
                }
            }
        }

        fun withCompoundDrawable(
            @DrawableRes drawableId: Int,
            position: Int
        ): Matcher<View> {
            return object : BoundedMatcher<View, TextView>(TextView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("with drawable$drawableId")
                }

                override fun matchesSafely(item: TextView): Boolean {
                    // Order is as follows: left, top, right, and bottom
                    val drawable =
                        item.compoundDrawables[position]
                    val expectedCompatDrawable =
                        AppCompatResources
                            .getDrawable(item.context, drawableId)
                    val expectedDrawable =
                        item.context.getDrawable(drawableId)
                    return matchDrawable(
                        drawable,
                        expectedCompatDrawable
                    ) || matchDrawable(
                        drawable,
                        expectedDrawable
                    )
                }
            }
        }

        private fun withDrawable(expectedDrawable: Drawable?): Matcher<View> {
            return object : CustomMatcher<View>("with drawable $expectedDrawable") {
                override fun matches(item: Any): Boolean {
                    return viewMatchesDrawable(
                        item,
                        expectedDrawable
                    )
                }
            }
        }

        fun withDrawableWithParentTag(
            tag: String, @IdRes drawableId: Int,
            @DrawableRes drawableRes: Int
        ): Matcher<View> {
            return object : CustomMatcher<View>(
                "with drawable $drawableId with parent tag $tag"
            ) {
                override fun matches(item: Any): Boolean {
                    val expectedDrawable =
                        AppCompatResources
                            .getDrawable((item as View).context, drawableRes)
                    val drawableParent =
                        item.findViewWithTag<ViewGroup>(tag)
                    val drawable =
                        drawableParent.findViewById<View>(drawableId)
                    return viewMatchesDrawable(
                        drawable,
                        expectedDrawable
                    )
                }
            }
        }

        fun withTextColor(@ColorRes colorRes: Int): Matcher<View> {
            return object : CustomMatcher<View>("with color drawable $colorRes") {
                override fun matches(item: Any): Boolean {
                    return if (item is TextView) {
                        val textColor = item.currentTextColor
                        val inputTextColor = ContextCompat.getColor(
                            item.context,
                            colorRes
                        )
                        textColor == inputTextColor
                    } else {
                        false
                    }
                }
            }
        }

        fun withFont(@FontRes fontRes: Int): Matcher<View> {
            return object : CustomMatcher<View>("with typeface $fontRes") {
                override fun matches(item: Any): Boolean {
                    return if (item is TextView) {
                        val typeface = item.typeface
                        typeface == ResourcesCompat.getFont(
                            item.context,
                            fontRes
                        )
                    } else {
                        false
                    }
                }
            }
        }

        fun withCarrotDrawable(@DrawableRes drawableId: Int): Matcher<View> {
            return object : CustomMatcher<View>("with drawable $drawableId") {
                override fun matches(item: Any): Boolean {
                    val drawable: Drawable = (item as View).background
                    val expectedDrawable =
                        AppCompatResources
                            .getDrawable(item.context, drawableId)
                    return matchDrawable(
                        drawable,
                        expectedDrawable
                    )
                }
            }
        }

        fun withColor(@ColorRes colorRes: Int): Matcher<View> {
            return object : CustomMatcher<View>("with color drawable $colorRes") {
                override fun matches(item: Any): Boolean {
                    val drawable: Drawable = if (item is ViewGroup) {
                        item.background
                    } else {
                        return false
                    }
                    val expectedDrawable = ColorDrawable(
                        ContextCompat.getColor(
                            InstrumentationRegistry.getInstrumentation().context, colorRes
                        )
                    )
                    return matchDrawable(
                        drawable,
                        expectedDrawable
                    )
                }
            }
        }

        fun withTintedDrawable(
            @DrawableRes drawableId: Int,
            @ColorRes colorId: Int
        ): Matcher<View> {
            val drawable =
                AppCompatResources
                    .getDrawable(InstrumentationRegistry.getInstrumentation().context, drawableId)
            drawable!!.setTint(
                ContextCompat.getColor(
                    InstrumentationRegistry.getInstrumentation().context,
                    colorId
                )
            )
            return withDrawable(drawable)
        }

        private fun matchDrawable(
            drawable: Drawable?,
            otherDrawable: Drawable?
        ): Boolean {
            if (drawable is BitmapDrawable && otherDrawable is BitmapDrawable) {
                val bitmap = drawable.bitmap
                val otherBitmap = otherDrawable.bitmap
                return bitmap.sameAs(otherBitmap)
            }
            if (drawable is GradientDrawable && otherDrawable is GradientDrawable) {
                val constantState = drawable.getConstantState()
                val otherConstantState = otherDrawable.getConstantState()
                return constantState == otherConstantState
            }
            if (drawable is ColorDrawable && otherDrawable is ColorDrawable) {
                @ColorInt val color = drawable.color
                @ColorInt val otherColor =
                    otherDrawable.color
                return color == otherColor
            }
            if ((drawable is VectorDrawableCompat || drawable is VectorDrawable)
                && (otherDrawable is VectorDrawableCompat
                        || otherDrawable is VectorDrawable)
            ) {
                val vectorBitmap =
                    getBitmapFromVectorDrawable(drawable)
                val otherVectorBitmap =
                    getBitmapFromVectorDrawable(otherDrawable)
                return vectorBitmap.sameAs(otherVectorBitmap)
            }
            if (drawable is StateListDrawable
                && otherDrawable is StateListDrawable
            ) {
                val vectorBitmap =
                    getBitmapFromVectorDrawable(drawable)
                val otherVectorBitmap =
                    getBitmapFromVectorDrawable(otherDrawable)
                return vectorBitmap.sameAs(otherVectorBitmap)
            }
            return false
        }

        private fun viewMatchesDrawable(
            item: Any,
            expectedDrawable: Drawable?
        ): Boolean {
            val drawable: Drawable = when (item) {
                is AppCompatImageView -> {
                    item.drawable
                }
                is ImageView -> {
                    item.drawable.current
                }
                is ViewGroup -> {
                    item.background
                }
                else -> {
                    return false
                }
            }
            return matchDrawable(drawable, expectedDrawable)
        }

        private fun getBitmapFromVectorDrawable(drawable: Drawable): Bitmap {
            val bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }

        private fun getDrawable(item: Any): Drawable? {
            if (item is AppCompatImageView) {
                return item.drawable
            } else if (item is ImageView
                && item.drawable != null
            ) {
                return item.drawable.current
            } else if (item is ViewGroup) {
                return item.background
            } else if (item is AppCompatButton) {
                return item.background
            }
            return null
        }
    }
}
