package mx.com.gilsa.foody.util

import android.content.Context
import android.content.res.Configuration

class Features {

    fun checkIsNight(context: Context): Boolean{
        val isNightTheme = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        var isNight = true
        when (isNightTheme) {
            Configuration.UI_MODE_NIGHT_YES -> {
                isNight = true
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                isNight = false
            }
        }
        return isNight
    }


}