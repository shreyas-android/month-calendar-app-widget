# Month Calendar App Widget

Elevate your Android library with the elegant and user-friendly Month Calendar app widget written using Jetpack Glance. With its customizable appearance, and seamless integration, this widget provides users with a visually appealing and hassle-free calendar experience on their Android apps and devices.



## Features

- **Customizable Styles**: Easily change the appearance and style of the calendar to align with your app's design.
- **Interactive Callbacks**: Provide callbacks for clicking and interacting with specific dates, enabling seamless integration with your app's functionality.
- **User-friendly Interface**: Intuitive and user-friendly design for hassle-free navigation and interaction within the calendar.
- **Jetpack Glance Integration**: Built using the Jetpack Glance library, ensuring robust performance and compatibility with the latest Android development standards.



## Installation

Step 1. Add the JitPack repository to your build file

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Step 2. Add the dependency

```
dependencies {
  implementation("com.github.shreyas-android:month-calendar-app-widget:${version}")
}
```

## Usage
To use the Month Calendar component in your Android application, follow these simple steps:


Add the Month Calendar widget to your GlanceAppWidget provideGlance.
Add the necessary argument to initialize the app widget component
Customize the widget's attributes to adjust its appearance.
Set up the necessary callbacks to handle user interactions with specific dates.

```
val monthCalendarArg = MonthCalendarArg(milliSecond.longValue, Calendar.SUNDAY)
val monthCalendarColors = MonthCalendarDefaults.getMonthCalendarColors(
                background = ColorProvider(day = Color.White, night = Color.Black))

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
  monthCalendarArg = monthCalendarArg,
  monthCalendarColors = monthCalendarColors)
```

