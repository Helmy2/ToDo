package com.example.todo.presentation.list.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.presentation.theme.CommonGrey
import com.example.todo.presentation.theme.MediumPadding
import com.example.todo.presentation.theme.SmallPadding
import com.example.todo.presentation.theme.ToDoTheme
import com.example.todo.presentation.util.convertLongToTime
import com.example.todo.presentation.util.getCurrentDate

@OptIn(
     ExperimentalComposeUiApi::class,
)
@Composable
fun BottomSheet(onBackClicked: () -> Unit) {
    val focusManager = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current
    var title by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

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
        DateButton(onDateChange = {
            Log.d("TAG", "BottomSheet: ${it.convertLongToTime()}")
        }, currantDate = getCurrentDate())
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = {
                keyboard?.hide()
                onBackClicked()
            }) {
                Text(text = "Cancel")
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Save")
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DefaultTextField(
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = false,
    focusManager: FocusManager,
    hint: String,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        shape = MaterialTheme.shapes.large,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        ),
        placeholder = { Text(text = hint, color = CommonGrey) },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Down) }),
    )
}

@Preview
@Composable
fun BottomSheetPreview() {
    ToDoTheme {
        BottomSheet() {}
    }
}