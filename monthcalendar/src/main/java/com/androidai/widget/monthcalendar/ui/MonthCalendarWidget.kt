package com.androidai.widget.monthcalendar.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.RowScope
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.androidai.widget.monthcalendar.R
import com.androidai.widget.monthcalendar.data.model.MonthCalendarArg
import com.androidai.widget.monthcalendar.ui.resources.Dimens
import com.androidai.widget.monthcalendar.ui.theme.defaults.MonthCalendarColors
import com.androidai.widget.monthcalendar.ui.theme.defaults.MonthCalendarDefaults
import com.androidai.widget.monthcalendar.utils.MonthCalendarWidgetUtils
import java.util.Calendar

@Composable
fun MonthCalendarWidget(monthCalendarArg: MonthCalendarArg, onDateClick: Action,
                        onGoToPreviousMonth: Action, onGoToNextMonth: Action,
                        monthCalendarColors: MonthCalendarColors = MonthCalendarDefaults
                            .getMonthCalendarColors()) {
    MonthCalendarView(
        calendar = MonthCalendarWidgetUtils.getCalendar(monthCalendarArg.currentMonth),
        firstDayOfMonth = monthCalendarArg.firstDayOfMonth,
        onGoToPreviousMonth = onGoToPreviousMonth,
        onGoToNextMonth = onGoToNextMonth,
        onDateClick = onDateClick,
        monthCalendarColors = monthCalendarColors
    )
}

@Composable
fun MonthCalendarView(
    modifier: GlanceModifier = GlanceModifier,
    calendar: Calendar, firstDayOfMonth: Int,
    onGoToPreviousMonth: Action, onGoToNextMonth: Action,onDateClick: Action,
    monthCalendarColors: MonthCalendarColors,
) {
    val context = LocalContext.current
    Column(modifier = modifier.background(monthCalendarColors.background)) {
        MonthCalendarHeaderView(context = context, calendar, monthCalendarColors =  monthCalendarColors,
            onGoToPreviousMonth = onGoToPreviousMonth, onGoToNextMonth = onGoToNextMonth)
        DaysOfWeek(firstDayOfWeek = firstDayOfMonth, monthCalendarColors)
        Date(
            firstDayOfMonth = firstDayOfMonth,
            calendar = calendar, monthCalendarColors, onDateClick
        )
    }
}


@Composable
private fun MonthCalendarHeaderView(
    context: Context,
    calendar: Calendar,
    onGoToPreviousMonth: Action, onGoToNextMonth: Action,
    monthCalendarColors: MonthCalendarColors,

) {
    Row(modifier = GlanceModifier.fillMaxWidth().padding(vertical = Dimens.defaultPadding)) {
        Row(
            modifier = GlanceModifier.defaultWeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = getHeaderImageModifier(onGoToPreviousMonth),
                provider = getArrowLeftIcon(),
                contentDescription = getResString(context, R.string.desc_previous_month)
            )
            Text(
                text = getFormatString(
                    calendar.timeInMillis,
                    MonthCalendarWidgetUtils.MONTH_YEAR_FORMAT
                ),
                style = TextStyle(
                    fontSize = Dimens.mediumFontSize,
                    textAlign = TextAlign.Center, color = monthCalendarColors.monthYearHeaderColor)
            )
            Image(
                modifier = getHeaderImageModifier(onGoToNextMonth),
                provider = getArrowRightIcon(),
                contentDescription = getResString(context, R.string.desc_next_month)
            )
        }
    }
}

private fun getResString(context: Context, stringResId: Int): String {
    return context.resources.getString(stringResId)
}

private fun getMonthFirstDayIndex(calendar: Calendar, firstDayOfMonth: Int): Int {
    val day = calendar.clone() as Calendar
    day.set(Calendar.DAY_OF_WEEK, firstDayOfMonth)
    var weekDayIndex = 0
    val currentWeekDayIndex = calendar[Calendar.DAY_OF_WEEK]
    for (i in 0 until 7) {
        if (day[Calendar.DAY_OF_WEEK] == currentWeekDayIndex) {
            weekDayIndex = i
            break
        }
        day.add(Calendar.DAY_OF_WEEK, 1)
    }
    return weekDayIndex
}

private fun getFormatString(timeInMillis: Long, requiredFormat: String): String {
    return MonthCalendarWidgetUtils.formatDateTime(
        timeInMillis = timeInMillis, requiredFormat = requiredFormat
    )
}

@Composable
fun DaysOfWeek(firstDayOfWeek: Int, monthCalendarColors: MonthCalendarColors) {
    val weekDayCalendar = MonthCalendarWidgetUtils.getCalendar()
    weekDayCalendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek)
    Row(modifier = GlanceModifier.fillMaxWidth()) {
        for (i in 0 until MonthCalendarWidgetUtils.COLUMN_COUNT) {
            Text(
                modifier = GlanceModifier.defaultWeight(), text = getFormatString(
                    weekDayCalendar.timeInMillis, MonthCalendarWidgetUtils.WEEK_DAY_FORMAT
                ), style = TextStyle(
                    color = monthCalendarColors.weekDayTextColor,
                    fontSize = Dimens.mediumFontSize,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            )

            weekDayCalendar.add(Calendar.DAY_OF_WEEK, 1)
        }
    }
}

@Composable
private fun Date(
    firstDayOfMonth: Int,
    calendar: Calendar,
    monthCalendarColors: MonthCalendarColors,
    onDateClick: Action
) {
    val todayCalendar = MonthCalendarWidgetUtils.getCalendar()
    val dateCalendar = calendar.clone() as Calendar
    dateCalendar.set(Calendar.DAY_OF_MONTH, 1)
    MonthCalendarWidgetUtils.setTimeToBeginningOfDay(dateCalendar)

    val currentMonthInt = dateCalendar.get(Calendar.MONTH)
    val futureCalendar = dateCalendar.clone() as Calendar
    futureCalendar.add(Calendar.MONTH, 1)
    val futureMonthInt = futureCalendar.get(Calendar.MONTH)

    val firstDayIndex = getMonthFirstDayIndex(dateCalendar, firstDayOfMonth)

    dateCalendar.add(Calendar.MONTH, -1)
    val prevMonthDays = dateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    val value = prevMonthDays - firstDayIndex + 1
    dateCalendar.set(Calendar.DAY_OF_MONTH, value)

    var dateMonthInt = dateCalendar.get(Calendar.MONTH)

    Column(modifier = GlanceModifier.fillMaxSize().padding(top = Dimens.padding10)) {
        for (row in 0 until MonthCalendarWidgetUtils.ROW_COUNT) {
            if (futureMonthInt == dateMonthInt) {
                break
            }
            Row(modifier = GlanceModifier.fillMaxWidth().defaultWeight()) {
                for (column in 0 until MonthCalendarWidgetUtils.COLUMN_COUNT) {
                    var dateString = ""
                    if (currentMonthInt == dateMonthInt) {
                        dateString = getFormatString(
                            dateCalendar.timeInMillis,
                            MonthCalendarWidgetUtils.DATE_FORMAT
                        )
                    }

                    DateTextView(
                        dateString = dateString,
                        isToday = MonthCalendarWidgetUtils.isSameDay(dateCalendar, todayCalendar),
                        onClick = onDateClick, monthCalendarColors
                    )

                    dateCalendar.add(Calendar.DAY_OF_MONTH, 1)
                    dateMonthInt = dateCalendar.get(Calendar.MONTH)
                }
            }
        }
    }
}

@Composable
private fun RowScope.DateTextView(
    dateString: String, isToday: Boolean, onClick: Action, monthCalendarColors: MonthCalendarColors
) {
    val isCurrentMonthDate = dateString != ""

    Column(
        modifier = getDateTextParentModifier(isCurrentMonthDate, onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.Vertical.CenterVertically
    ) {
        Text(
            modifier = getDateTextChildModifier(isCurrentMonthDate)
                .padding(Dimens.padding2), text = dateString, style = TextStyle(
                color = if (isToday) {
                        monthCalendarColors.todayDateTextColor
                    } else {
                        monthCalendarColors.dateTextColor
                    },
                fontSize = Dimens.smallFontSize, textAlign = TextAlign.Center
            ), maxLines = 1
        )
    }
}

private fun RowScope.getDateTextParentModifier(isCurrentMonth: Boolean, onClick: Action):
        GlanceModifier {
    return if (isCurrentMonth) {
        GlanceModifier.defaultWeight().clickable(onClick = onClick)
    } else {
        GlanceModifier.defaultWeight()
    }
}

private fun getDateTextChildModifier(isCurrentMonth: Boolean):
        GlanceModifier {
    return if (isCurrentMonth) {
        GlanceModifier.wrapContentSize()
    } else {
        GlanceModifier.wrapContentSize()
    }
}

private fun getHeaderImageModifier(onClick: Action): GlanceModifier {
    return GlanceModifier.size(Dimens.imageSize)
        .clickable(onClick).padding(
            vertical = Dimens.defaultHalfPadding,
            horizontal = Dimens.defaultHalfPadding
        )
}

private fun getArrowLeftIcon(): ImageProvider {
    return getImageProvider(R.drawable.ic_widget_arrow_left)
}

private fun getArrowRightIcon(): ImageProvider {
    return getImageProvider(R.drawable.ic_widget_arrow_right)
}

private fun getImageProvider(drawableResId: Int) = ImageProvider(drawableResId)
