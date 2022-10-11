package com.example.todo.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.color
import com.example.todo.presentation.theme.MediumPadding
import com.example.todo.presentation.theme.SmallPadding
import com.example.todo.presentation.util.SwipeDismiss

@Composable
fun ToDoListColumn(
    list: List<ToDoList>,
    onListItemClick: (String) -> Unit,
    onDeleteItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier
    ) {
        items(list, key = { item -> item.id }) { toDoList ->
            ToDoItem(
                toDoList = toDoList,
                onItemClick = { onListItemClick(toDoList.id) },
                onDeleteItemClicked = { onDeleteItemClick(toDoList.id) }
            )
        }
    }
}

@Composable
fun ToDoItem(
    toDoList: ToDoList,
    onDeleteItemClicked: () -> Unit,
    onItemClick: () -> Unit,
) {
    SwipeDismiss(
        onDismiss = { onDeleteItemClicked() },
        backgroundModifier = Modifier.clip(
            MaterialTheme.shapes.medium
        ),
        backgroundSecondaryModifier = Modifier.clip(
            MaterialTheme.shapes.medium
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .clickable {
                    onItemClick()
                },
        ) {
            Row(
                modifier = Modifier
                    .padding(MediumPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = Icons.Default.List.name,
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(toDoList.color.color())
                        .padding(SmallPadding),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(SmallPadding))
                Text(
                    text = toDoList.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = toDoList.tasks.size.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.width(SmallPadding))
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = Icons.Default.ArrowRight.name,
                        modifier = Modifier
                            .size(15.dp)
                    )
                }
            }
        }
    }
}