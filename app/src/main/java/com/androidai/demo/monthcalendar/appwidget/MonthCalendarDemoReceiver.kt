package com.androidai.demo.monthcalendar.appwidget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.updateAll

class MonthCalendarDemoReceiver: GlanceAppWidgetReceiver()  {
    override val glanceAppWidget: GlanceAppWidget
        get() = MonthCalendarDemoWidget()


    suspend fun updateAllWidget(context: Context) {
        glanceAppWidget.updateAll(context = context)
    }
}