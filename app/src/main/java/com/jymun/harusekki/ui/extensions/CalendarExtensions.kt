package com.jymun.harusekki.ui.extensions

import com.prolificinteractive.materialcalendarview.CalendarDay
import java.time.LocalDate

fun CalendarDay.toLocalDate() = LocalDate.of(year, month + 1, day)

fun LocalDate.toCalendarDay() = CalendarDay.from(year, monthValue - 1, dayOfMonth)