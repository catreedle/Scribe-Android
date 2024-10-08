package be.scri.extensions

import android.view.HapticFeedbackConstants
import android.view.View
import android.view.ViewTreeObserver
import be.scri.helpers.SHORT_ANIMATION_DURATION

fun View.beInvisibleIf(beInvisible: Boolean) = if (beInvisible) beInvisible() else beVisible()

fun View.beVisibleIf(beVisible: Boolean) = if (beVisible) beVisible() else beGone()

fun View.beGoneIf(beGone: Boolean) = beVisibleIf(!beGone)

fun View.beInvisible() {
    visibility = View.INVISIBLE
}

fun View.beVisible() {
    visibility = View.VISIBLE
}

fun View.beGone() {
    visibility = View.GONE
}

fun View.onGlobalLayout(callback: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(
        object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                callback()
            }
        },
    )
}

fun View.isVisible() = visibility == View.VISIBLE

fun View.isInvisible() = visibility == View.INVISIBLE

fun View.isGone() = visibility == View.GONE

fun View.performHapticFeedback() = performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)

fun View.fadeIn() {
    animate()
        .alpha(1f)
        .setDuration(SHORT_ANIMATION_DURATION)
        .withStartAction { beVisible() }
        .start()
}

fun View.fadeOut() {
    animate()
        .alpha(0f)
        .setDuration(SHORT_ANIMATION_DURATION)
        .withEndAction { beGone() }
        .start()
}
