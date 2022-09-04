package com.example.todo.presentation.list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.ToDoStatus
import com.example.todo.domian.model.ToDoTask
import com.example.todo.domian.model.color
import com.example.todo.presentation.theme.CommonGrey
import com.example.todo.presentation.theme.MediumPadding
import com.example.todo.presentation.theme.SmallPadding
import com.example.todo.presentation.util.SwipeDismiss
import com.example.todo.presentation.util.convertLongToTime

@Composable
fun TaskItem(
    task: ToDoTask,
    taskColor: ToDoColor,
    onSwipeToDelete: () -> Unit,
    onTaskItemClick: () -> Unit,
    onCheckItemClick: () -> Unit,
) {
    SwipeDismiss(
        backgroundModifier = Modifier.background(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.shapes.medium
        ),
        backgroundSecondaryModifier = Modifier.clip(
            MaterialTheme.shapes.medium
        ),
        onDismiss = { onSwipeToDelete() }
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = SmallPadding)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.medium)
                .clickable {
                    onTaskItemClick()
                }
        ) {
            if (task.status == ToDoStatus.COMPLETE)
                CompleteTaskItem(task, taskColor, onCheckItemClick)
            else
                InProgressTaskItem(task, taskColor, onCheckItemClick)
        }
    }
}

@Composable
fun CompleteTaskItem(
    task: ToDoTask,
    taskColor: ToDoColor,
    onCheckItemClick: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(SmallPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            tint = taskColor.color().copy(alpha = .3f),
            imageVector = Icons.Default.Check,
            contentDescription = Icons.Default.Check.name,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onCheckItemClick() }
        )
        Spacer(modifier = Modifier.width(MediumPadding))
        Text(
            text = task.name,
            textDecoration = TextDecoration.LineThrough,
            color = CommonGrey
        )
    }
}

@Composable
fun InProgressTaskItem(
    task: ToDoTask,
    taskColor: ToDoColor,
    onCheckItemClick: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(SmallPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            tint = taskColor.color()
                .copy(alpha = .3f),
            imageVector = Icons.Outlined.Circle,
            contentDescription = Icons.Outlined.Circle.name,
            modifier = Modifier
                .clip(CircleShape)
                .clickable { onCheckItemClick() }
        )
        Spacer(modifier = Modifier.width(MediumPadding))
        Text(
            text = task.name,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    Box(
        modifier = Modifier
            .padding(SmallPadding)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = task.createdAt.convertLongToTime(),
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(SmallPadding)
        )
    }
}
