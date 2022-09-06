package com.example.todo.presentation.list.components

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo.presentation.theme.ToDoTheme
import com.example.todo.presentation.util.getCurrentDate
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import java.util.*

@Composable
fun DateTimePikerField(onDateChange: (date: Long) -> Unit, currantDate: Long) {
    val context = LocalContext.current as AppCompatActivity
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = currantDate

    val february = calendar.timeInMillis
    val constraintsBuilder =
        CalendarConstraints.Builder()
            .setOpenAt(february)
            .build()

    val timePicker =
        MaterialTimePicker.Builder()
            .setTitleText("Select Time")
            .setHour(calendar[Calendar.HOUR_OF_DAY])
            .setMinute(calendar[Calendar.MINUTE])
            .build()

    val datePicker =
        MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setCalendarConstraints(constraintsBuilder)
            .build()

    datePicker.addOnPositiveButtonClickListener {
        calendar.timeInMillis = it
        timePicker.show(context.supportFragmentManager, "")
    }

    timePicker.addOnPositiveButtonClickListener {
        calendar[Calendar.HOUR_OF_DAY] = timePicker.hour
        calendar[Calendar.MINUTE] = timePicker.minute

        onDateChange(calendar.timeInMillis)
    }

    IconButton(
        onClick = {
            datePicker.show(context.supportFragmentManager, "")
        },
        modifier = Modifier.clip(CircleShape),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
    ) {
        androidx.compose.material3.Icon(
            imageVector = Icons.Default.CalendarToday, contentDescription = "Calendar"
        )
    }
}

@Preview
@Composable
fun DateTimePikerFieldPreview() {
    ToDoTheme {
        DateTimePikerField(
            onDateChange = {}, currantDate = getCurrentDate()
        )
    }
}
