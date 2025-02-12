package com.ramesh.core.util

import java.text.NumberFormat
import java.util.Locale

object UtilFunctions {
    private val localeID = Locale("in", "IN")


    fun Double?.fromDollarToRupiah(): String {
        val localId = localeID
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val fakeDollarToday = 15000.0
        val intValue = (this ?: 0.0) * fakeDollarToday
        return when {
            intValue > 0 -> formatter.format(intValue).replace(",00", "")
            intValue < 0 -> formatter.format(intValue).replace(",00", "")
            else -> "Rs"
        }
    }
}