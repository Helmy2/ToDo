package com.example.todo.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todo.domian.model.*
import com.example.todo.presentation.theme.MediumPadding
import com.example.todo.presentation.theme.SmallPadding
import com.example.todo.presentation.util.SwipeDismiss

@Composable
fun ToDoItem(
    toDoList: ToDoList,
    onSwipeToDelete: () -> Unit,
    onItemClick: () -> Unit,
) {
    SwipeDismiss(
        backgroundModifier = Modifier.background(
            MaterialTheme.colorScheme.secondary, MaterialTheme.shapes.large
        ),
        backgroundSecondaryModifier = Modifier.clip(
            MaterialTheme.shapes.large
        ),
        onDismiss = { onSwipeToDelete() }
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = SmallPadding)
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
