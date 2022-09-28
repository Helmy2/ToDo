package com.example.todo.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.color
import com.example.todo.presentation.theme.MediumPadding
import com.example.todo.presentation.theme.SmallPadding
import com.example.todo.presentation.theme.ToDoTheme
import com.example.todo.presentation.util.ColorPickerField
import com.example.todo.presentation.util.DefaultTextField

@Composable
fun ListBottomSheet(
    toDoColor: ToDoColor,
    onSaveButtonClicked: (ToDoList) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var title by remember { mutableStateOf("") }
    var color by remember { mutableStateOf(toDoColor) }
    var showColorDialog by remember { mutableStateOf(false) }
    var valid by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = title, block = {
        valid = title.isNotBlank()
    })
    if (showColorDialog)
        Dialog(onDismissRequest = { showColorDialog = false }) {
            ColorPickerField(
                selectedColor = color,
                onSelectedClicked = { color = it; showColorDialog = false },
                onCancelClicked = { showColorDialog = false }
            )
        }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(MediumPadding)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "New List",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
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
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier
                .wrapContentSize()
                .padding(SmallPadding)
                .clip(CircleShape)
                .background(color.color())
                .clickable { showColorDialog = true }
                .padding(MediumPadding))
            TextButton(
                onClick = {
                    onSaveButtonClicked(ToDoList(color = color, name = title))
                },
                enabled = valid
            ) {
                Text(text = "Save")
            }
        }
    }
}

@Preview
@Composable
fun BottomSheetPreview() {
    ToDoTheme {
        ListBottomSheet(toDoColor = ToDoColor.BLUE, onSaveButtonClicked = { })
    }
}