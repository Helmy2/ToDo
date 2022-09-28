package com.example.todo.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.todo.domian.model.ToDoTask
import com.example.todo.presentation.theme.SmallPadding
import com.example.todo.presentation.util.DateTimePikerField
import com.example.todo.presentation.util.DefaultTextField

@Composable
fun TaskEditDialog(
    task: ToDoTask,
    onSave: (ToDoTask) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    var date by remember { mutableStateOf(task.dueDate) }
    var title by remember { mutableStateOf(task.name) }
    var note by remember { mutableStateOf(task.note) }

    Dialog(onDismissRequest = onCancel) {
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    MaterialTheme.shapes.extraLarge
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(SmallPadding)
        ) {
            DefaultTextField(
                value = title,
                onValueChange = { title = it },
                focusManager = focusManager,
                hint = "Title",
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            )
            DefaultTextField(
                value = note,
                onValueChange = { note = it },
                focusManager = focusManager,
                hint = "Notes",
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            )
            DateTimePikerField(
                currantDate = date,
                onDoneClicked = { date = it },
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = onCancel) {
                    Text(text = "Cancel")
                }
                Button(onClick = {
                    onSave(
                        task.copy(
                            name = title,
                            note = note,
                            dueDate = date,
                        )
                    )
                }) {
                    Text(text = "Save")
                }
            }
        }
    }
}