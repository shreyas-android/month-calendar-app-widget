package com.androidai.demo.monthcalendar.appwidget

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class MonthCalendarDemoReceiver: GlanceAppWidgetReceiver()  {
    override val glanceAppWidget: GlanceAppWidget
        get() = MonthCalendarDemoWidget()



}