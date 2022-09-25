package com.example.todo.presentation.list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.todo.domian.model.ToDoTask
import com.example.todo.presentation.util.DateTimePikerField
import com.example.todo.presentation.util.DefaultTextField
import com.example.todo.presentation.util.getCurrentDate

@Composable
fun TaskEditDialog(
    isOpen: Boolean = false,
    task: ToDoTask?,
    onSave: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    var date by remember { mutableStateOf(task?.dueDate) }

    AnimatedVisibility(visible = isOpen) {
        Dialog(onDismissRequest = onCancel) {
            Column(
                modifier = modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DefaultTextField(
                    value = task?.name ?: "",
                    onValueChange = {},
                    focusManager = focusManager,
                    hint = "Title",
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.large)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                )
                DefaultTextField(
                    value = task?.note ?: "",
                    onValueChange = {},
                    focusManager = focusManager,
                    hint = "Notes",
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.large)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                )
                DateTimePikerField(
                    currantDate = getCurrentDate(),
                    onDoneClicked = { date = it },
                )
            }
        }
    }
}
