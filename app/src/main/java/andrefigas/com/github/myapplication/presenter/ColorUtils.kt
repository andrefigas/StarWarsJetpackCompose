package andrefigas.com.github.myapplication.presenter

import andrefigas.com.github.myapplication.presenter.theme.*
import androidx.compose.ui.graphics.Color

object ColorUtils {

    fun getColorTheme(name: String): Color {
        return if (name.isNullOrEmpty()) {
            ColorOther
        } else when (name.uppercase()[0]) {
            'A' -> ColorA
            'B' -> ColorB
            'C' -> ColorC
            'D' -> ColorD
            'E' -> ColorE
            'F' -> ColorF
            'G' -> ColorG
            'H' -> ColorH
            'I' -> ColorI
            'J' -> ColorJ
            'K' -> ColorK
            'L' -> ColorL
            'M' -> ColorM
            'N' -> ColorN
            'O' -> ColorO
            'P' -> ColorP
            'Q' -> ColorQ
            'R' -> ColorR
            'S' -> ColorS
            'T' -> ColorT
            'U' -> ColorU
            'V' -> ColorV
            'W' -> ColorW
            'X' -> ColorX
            'Y' -> ColorY
            'Z' -> ColorZ
            else -> ColorOther
        }
    }

    fun getLightColorTheme(name: String): Color {
        return if (name.isNullOrEmpty()) {
            ColorLightOther
        } else when (name.uppercase()[0]) {
            'A' -> ColorLightA
            'B' -> ColorLightB
            'C' -> ColorLightC
            'D' -> ColorLightD
            'E' -> ColorLightE
            'F' -> ColorLightF
            'G' -> ColorLightG
            'H' -> ColorLightH
            'I' -> ColorLightI
            'J' -> ColorLightJ
            'K' -> ColorLightK
            'L' -> ColorLightL
            'M' -> ColorLightM
            'N' -> ColorLightN
            'O' -> ColorLightO
            'P' -> ColorLightP
            'Q' -> ColorLightQ
            'R' -> ColorLightR
            'S' -> ColorLightS
            'T' -> ColorLightT
            'U' -> ColorLightU
            'V' -> ColorLightV
            'W' -> ColorLightW
            'X' -> ColorLightX
            'Y' -> ColorLightY
            'Z' -> ColorLightZ
            else -> ColorLightOther
        }
    }

}