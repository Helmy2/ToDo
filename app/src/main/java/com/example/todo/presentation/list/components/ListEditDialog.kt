package com.example.todo.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.color
import com.example.todo.presentation.theme.MediumPadding
import com.example.todo.presentation.theme.SmallPadding
import com.example.todo.presentation.util.ColorPickerField
import com.example.todo.presentation.util.DefaultTextField

@Composable
fun ListEditDialog(
    toDoList: ToDoList,
    onSave: (ToDoList) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    var title by remember { mutableStateOf(toDoList.name) }
    var color by remember { mutableStateOf(toDoList.color) }
    var showColorDialog by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onCancel) {
        if (showColorDialog)
            Dialog(onDismissRequest = { showColorDialog = false }) {
                ColorPickerField(
                    selectedColor = color,
                    onSelectedClicked = { color = it; showColorDialog = false },
                    onCancelClicked = { showColorDialog = false }
                )
            }
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    MaterialTheme.shapes.extraLarge
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DefaultTextField(
                value = title,
                onValueChange = { title = it },
                singleLine = true,
                focusManager = focusManager,
                hint = "Title",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.large)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
            )

            Box(modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = SmallPadding)
                .clip(CircleShape)
                .background(color.color())
                .clickable { showColorDialog = true }
                .padding(MediumPadding))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = onCancel) {
                    Text(text = "Cancel")
                }
                Button(onClick = {
                    onSave(toDoList.copy(color = color, name = title))
                }) {
                    Text(text = "Save")
                }
            }
        }
    }
}