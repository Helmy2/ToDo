package com.example.todo.presentation.util

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo.presentation.theme.ToDoTheme
import com.example.todo.util.toLocalDateTime
import com.example.todo.util.toMillis
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import java.time.LocalDateTime
import java.time.temporal.TemporalField
import java.util.*

@Composable
fun DateTimePikerField(
    currantDate: LocalDateTime?,
    onDoneClicked: (date: LocalDateTime) -> Unit,
    context: AppCompatActivity = LocalContext.current.activity()!!
) {
    val calendar by remember {
        mutableStateOf(
            Calendar.getInstance().also { c ->
                currantDate?.let {
                    c.timeInMillis = it.toMillis()
                }
            }
        )
    }

    val constraintsBuilder =
        CalendarConstraints.Builder()
            .setOpenAt(calendar.timeInMillis)
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

        onDoneClicked(calendar.timeInMillis.toLocalDateTime())
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
        Icon(
            imageVector = Icons.Default.CalendarToday, contentDescription = "Calendar"
        )
    }
}

@Preview
@Composable
fun DateTimePikerFieldPreview() {
    ToDoTheme {
        DateTimePikerField(
            onDoneClicked = {}, currantDate = LocalDateTime.now()
        )
    }
}
