package vn.lepha_khtn.androidhometest.utils

import android.graphics.Color
import java.util.*

/**
 * Created by JB Pha Le
 */
object CommonUtils {

    fun getRandomColor(): Int {
        val random = Random()
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }
}