package com.example.todo.presentation.list.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo.domian.model.ToDoList
import com.example.todo.presentation.theme.MediumPadding

@Composable
fun ListScreenTopBar(
    toDoList: ToDoList,
    onBackClicked: () -> Unit,
    onEditItemClicked: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = MediumPadding, end = MediumPadding, top = MediumPadding)
    ) {
        IconButton(onClick = onBackClicked) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "Back Arrow",
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }
        Text(
            text = toDoList.name,
            style = MaterialTheme.typography.headlineMedium
        )
        Box {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = ""
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                DropdownMenuItem(
                    onClick = onEditItemClicked,
                    text = {
                        Text(text = "Edit")
                    },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}