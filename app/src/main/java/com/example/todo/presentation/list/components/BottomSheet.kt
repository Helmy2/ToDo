package com.example.todo.presentation.list.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.color
import com.example.todo.presentation.theme.MediumPadding
import com.example.todo.presentation.theme.SmallPadding
import com.example.todo.presentation.theme.ToDoTheme
import com.example.todo.presentation.util.DefaultTextField
import com.example.todo.presentation.util.convertLongToTime
import com.example.todo.presentation.util.getCurrentDate

@Composable
fun BottomSheet(toDoColor: ToDoColor) {
    val focusManager = LocalFocusManager.current
    var title by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var color by remember { mutableStateOf(toDoColor) }

    var openDialog by remember { mutableStateOf(false) }

    if (openDialog)
        Dialog(onDismissRequest = { openDialog = false }) {
            ColorPickerField(
                selectedColor = color,
                onSelectedClicked = {
                    color = it
                    openDialog = false
                },
                onCancelClicked = { openDialog = false })
        }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(MediumPadding)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "New ToDo",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Column(
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.secondaryContainer),
        ) {
            DefaultTextField(
                value = title,
                onValueChange = { title = it },
                singleLine = true,
                focusManager = focusManager,
                hint = "Title",
                modifier = Modifier.fillMaxWidth()
            )
            Divider(Modifier.padding(SmallPadding))
            DefaultTextField(
                value = note,
                onValueChange = { note = it },
                focusManager = focusManager,
                hint = "Notes",
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 100.dp)
            )
        }
        Row {
            DateTimePikerField(
                onDateChange = {
                    Log.d("TAG", "BottomSheet: ${it.convertLongToTime()}")
                }, currantDate = getCurrentDate()
            )
            Box(modifier = Modifier
                .fillMaxWidth(.15f)
                .aspectRatio(1f)
                .padding(SmallPadding)
                .clip(CircleShape)
                .background(color.color())
                .clickable { openDialog = true }
                .padding(SmallPadding)
            )
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Save")
                }
            }
        }
    }
}

@Preview
@Composable
fun BottomSheetPreview() {
    ToDoTheme {
        BottomSheet(ToDoColor.BLUE)
    }
}