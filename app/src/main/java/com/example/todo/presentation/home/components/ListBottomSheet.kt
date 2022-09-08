package com.example.todo.presentation.home.components

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
import com.example.todo.presentation.util.ColorPickerField
import com.example.todo.presentation.util.DefaultTextField

@Composable
fun ListBottomSheet(
    toDoColor: ToDoColor,
    onSaveButtonClicked: (title: String, color: ToDoColor) -> Unit
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
        Row {
            Box(modifier = Modifier
                .fillMaxWidth(.15f)
                .aspectRatio(1f)
                .padding(SmallPadding)
                .clip(CircleShape)
                .background(color.color())
                .clickable { showColorDialog = true }
                .padding(SmallPadding))
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { onSaveButtonClicked(title, color) }, enabled = valid) {
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
        ListBottomSheet(toDoColor = ToDoColor.BLUE, onSaveButtonClicked = { _, _ -> })
    }
}