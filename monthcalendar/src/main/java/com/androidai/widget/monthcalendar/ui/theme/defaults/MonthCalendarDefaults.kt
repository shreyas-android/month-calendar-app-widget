package com.androidai.widget.monthcalendar.ui.theme.defaults

import androidx.compose.ui.graphics.Color
import androidx.glance.unit.ColorProvider

object MonthCalendarDefaults {

    fun getMonthCalendarColors(
        defaultColor: MonthCalendarColors = monthCalendarColors,
        background: ColorProvider = defaultColor.background,
        weekDayTextColor: ColorProvider = defaultColor.weekDayTextColor,
        iconColor: ColorProvider = defaultColor.iconColor,
        dateTextColor: ColorProvider = defaultColor.dateTextColor,
        todayDateTextColor: ColorProvider = defaultColor.todayDateTextColor,
        selectedDateBackgroundColor: ColorProvider = defaultColor.selectedDateBackgroundColor,
        monthYearHeaderColor: ColorProvider = defaultColor.monthYearHeaderColor
    ): MonthCalendarColors {
        return MonthCalendarColors(
            background, weekDayTextColor, iconColor, dateTextColor,
            todayDateTextColor, selectedDateBackgroundColor, monthYearHeaderColor
        )
    }
}