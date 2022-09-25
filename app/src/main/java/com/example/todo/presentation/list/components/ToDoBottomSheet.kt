package com.example.todo.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.presentation.theme.MediumPadding
import com.example.todo.presentation.theme.SmallPadding
import com.example.todo.presentation.theme.ToDoTheme
import com.example.todo.presentation.util.DateTimePikerField
import com.example.todo.presentation.util.DefaultTextField
import com.example.todo.presentation.util.getCurrentDate

@Composable
fun ToDoBottomSheet(
    onSaveButtonClicked: (title: String, note: String, date: Long?) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var title by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var date by remember { mutableStateOf<Long?>(null) }

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
            Divider(
                Modifier.padding(SmallPadding),
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
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
                currantDate = getCurrentDate(),
                onDoneClicked = { date = it },
            )
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = { onSaveButtonClicked(title, note, date) },
                    enabled = title.isNotBlank()
                ) {
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
        ToDoBottomSheet(onSaveButtonClicked = { _, _, _ -> })
    }
}