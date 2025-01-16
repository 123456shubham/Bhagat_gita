package com.example.shreebhagvatgeeta.utils

import android.app.Activity
import android.graphics.Color
import android.provider.CalendarContract.Colors
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.example.shreebhagvatgeeta.R

object CommonFunction {

    fun changeStatusBarColour(activity: Activity, @ColorRes colorResId: Int, isLightStatusBar: Boolean = true) {
        val window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(activity, colorResId)

        WindowCompat.getInsetsController(window, window.decorView)?.apply {
            isAppearanceLightStatusBars = isLightStatusBar
        }
    }

}