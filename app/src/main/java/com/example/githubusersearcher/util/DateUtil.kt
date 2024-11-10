package com.example.githubusersearcher.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtil {
    /**
     * Converts a timestamp in milliseconds to a Turkish-formatted date and time string.
     *
     * @param millis The timestamp in milliseconds to convert.
     * @return A formatted date and time string in Turkish locale (e.g., "dd MMMM yyyy HH:mm").
     */
    fun convertMillisToTurkishDate(millis: Long): String {
        val date = Date(millis)
        val turkishLocale = Locale("tr", "TR")
        val dateFormat = SimpleDateFormat("dd MMMM yyyy HH:mm", turkishLocale)
        return dateFormat.format(date)
    }
}