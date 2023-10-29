package com.androidai.demo.monthcalendar.appwidget

import android.content.Context
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionSendBroadcast
import androidx.glance.appwidget.provideContent
import com.androidai.demo.monthcalendar.utils.CalendarUtils
import com.androidai.widget.monthcalendar.data.model.MonthCalendarArg
import com.androidai.widget.monthcalendar.ui.MonthCalendarWidget
import java.util.Calendar

class MonthCalendarDemoWidget : GlanceAppWidget() {

    override val sizeMode: SizeMode
        get() = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {

            val milliSecond = remember {
                mutableLongStateOf(System.currentTimeMillis())
            }


            val monthCalendarArg = MonthCalendarArg(milliSecond.longValue, Calendar.SUNDAY)

            MonthCalendarWidget(
                onDateClick = actionSendBroadcast<MonthCalendarDemoReceiver>(),
                onGoToPreviousMonth = {
                    val calendar = CalendarUtils.getCalendar(milliSecond.longValue)
                    calendar.add(Calendar.MONTH, -1)
                    milliSecond.longValue = calendar.timeInMillis
                },
                onGoToNextMonth = {
                    val calendar = CalendarUtils.getCalendar(milliSecond.longValue)
                    calendar.add(Calendar.MONTH, 1)
                    milliSecond.longValue = calendar.timeInMillis
                },
                monthCalendarArg = monthCalendarArg
            )
        }

    }
}