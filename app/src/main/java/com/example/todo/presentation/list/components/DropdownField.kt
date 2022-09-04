package com.example.todo.presentation.list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.color

@Composable
fun DropdownField(
    list: List<ToDoList>,
    selectedIndex: Int,
    onItemClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    if (list.isNotEmpty())
        Box(modifier) {

            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(onClick = { expanded = true })
            ) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "",
                    tint = list[selectedIndex].color.color().copy(alpha = .9f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(list[selectedIndex].name)
            }


            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                list.forEachIndexed { index, taskList ->
                    DropdownMenuItem(onClick = {
                        onItemClick(index)
                        expanded = false
                    }) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "",
                            tint = taskList.color.color().copy(alpha = .9f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(taskList.name, maxLines = 1)

                    }
                }
            }
        }
}