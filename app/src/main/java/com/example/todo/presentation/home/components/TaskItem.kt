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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todo.domian.model.*
import com.example.todo.presentation.util.SwipeDismiss

@Composable
fun ListItem(
    toDoList: ToDoList,
    onSwipeToDelete: () -> Unit,
    onTaskItemClick: () -> Unit,
) {
    SwipeDismiss(
        backgroundModifier = Modifier
            .background(
                MaterialTheme.colorScheme.secondary, RoundedCornerShape(20)
            ),
        backgroundSecondaryModifier = Modifier.clip(
            RoundedCornerShape(20)
        ),
        onDismiss = { onSwipeToDelete() }
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .clickable {
                    onTaskItemClick()
                },
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = Icons.Default.List.name,
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(toDoList.color.color())
                        .padding(8.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = toDoList.name,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = toDoList.tasks.size.toString())
                    Spacer(modifier = Modifier.width(8.dp))
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
