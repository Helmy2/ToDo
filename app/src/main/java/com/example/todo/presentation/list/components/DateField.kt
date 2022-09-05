package com.example.todo.presentation.list.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.view.ContextThemeWrapper
import android.widget.CalendarView
import android.widget.DatePicker
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.viewinterop.AndroidView
import com.example.todo.R
import com.example.todo.presentation.theme.ToDoTheme
import com.example.todo.presentation.util.getCurrentDate
import java.time.LocalDate
import java.util.*

@Composable
fun DateButton(onDateChange: (date: Long) -> Unit, currantDate: Long) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = currantDate

    val timePickerDialog = TimePickerDialog(
        context, { _, hour, minute ->
            calendar[Calendar.HOUR_OF_DAY] = hour
            calendar[Calendar.MINUTE] = minute

            onDateChange(calendar.timeInMillis)
        }, calendar[Calendar.HOUR_OF_DAY], calendar[Calendar.MINUTE], false
    )

    val datePickerDialog = DatePickerDialog(
        context, { _: DatePicker, year: Int, month: Int, day: Int ->
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = month
            calendar[Calendar.DAY_OF_MONTH] = day

            timePickerDialog.show()
        }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]
    )

    IconButton(
        onClick = { datePickerDialog.show() },
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
fun DateFieldPreview() {
    ToDoTheme {
        DateButton(
            onDateChange = {}, currantDate = getCurrentDate()
        )
    }
}
