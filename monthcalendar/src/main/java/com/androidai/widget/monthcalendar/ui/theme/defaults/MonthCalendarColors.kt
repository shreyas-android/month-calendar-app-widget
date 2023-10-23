package com.androidai.widget.monthcalendar.ui.theme.defaults

import androidx.compose.ui.graphics.Color
import androidx.glance.color.ColorProvider
import androidx.glance.unit.ColorProvider


data class MonthCalendarColors(val background: ColorProvider, val weekDayTextColor:ColorProvider,
                               val iconColor:ColorProvider, val dateTextColor:ColorProvider,
                               val todayDateTextColor:ColorProvider, val selectedDateBackgroundColor:ColorProvider,
                               val monthYearHeaderColor:ColorProvider)

internal val ColorBackground = Color(0xFFF1F1F4)
internal val ColorBackgroundNight = Color(0xFF000000)

internal val ColorLabelTextColor = Color(0xFF202124)
internal val ColorLabelTextColorNight = Color(0xFFFFFFFF)

internal val ColorIcon = Color(0xB8000000)
internal val ColorIconNight = Color(0xB8FFFFFF)

internal val monthCalendarColors = MonthCalendarColors(background = ColorProvider(ColorBackground,
    ColorBackgroundNight),
        weekDayTextColor = ColorProvider(ColorLabelTextColor, ColorLabelTextColorNight),
        iconColor =ColorProvider(ColorIcon, ColorIconNight),
        dateTextColor = ColorProvider(ColorLabelTextColor, ColorLabelTextColorNight),
        todayDateTextColor =ColorProvider(ColorLabelTextColor, ColorLabelTextColorNight),
        selectedDateBackgroundColor = ColorProvider(ColorLabelTextColor, ColorLabelTextColorNight),
        monthYearHeaderColor = ColorProvider(ColorLabelTextColor, ColorLabelTextColorNight))


