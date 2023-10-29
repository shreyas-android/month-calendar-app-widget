package com.androidai.demo.monthcalendar.utils

object CalendarUtils {

    fun getCalendar(timeInMillis:Long? = null): java.util.Calendar {
        val calendar = java.util.Calendar.getInstance()
        timeInMillis?.let {
            calendar.timeInMillis = it
        }
        return calendar
    }
}