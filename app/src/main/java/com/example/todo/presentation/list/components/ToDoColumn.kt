package com.example.todo.presentation.list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.ToDoStatus
import com.example.todo.domian.model.ToDoTask
import com.example.todo.domian.model.color
import com.example.todo.presentation.theme.SmallPadding
import com.example.todo.presentation.util.SwipeDismiss
import com.example.todo.util.formatDateTime
import java.time.LocalDateTime

@Composable
fun ToDoColumn(
    list: List<ToDoTask>,
    taskColor: ToDoColor,
    onSwipeToDelete: (ToDoTask) -> Unit,
    onTaskItemClick: (ToDoTask) -> Unit,
    onCheckItemClick: (ToDoTask) -> Unit,
    modifier: Modifier = Modifier
) {
    val completedList = remember(list) {
        list.filter { it.status == ToDoStatus.COMPLETE }
    }
    val inProgressList = remember(list) {
        list.filter { it.status == ToDoStatus.IN_PROGRESS }
    }

    var showDone by remember { mutableStateOf(true) }

    LazyColumn(modifier) {
        items(inProgressList, key = { it.id }) {
            ToDoItem(
                task = it,
                taskColor,
                onSwipeToDelete = { onSwipeToDelete(it) },
                onTaskItemClick = { onTaskItemClick(it) },
                onCheckItemClick = { onCheckItemClick(it) }
            )
        }
        item {
            TextButton(onClick = { showDone = !showDone }) {
                Text(
                    text = "Done (${completedList.size})",
                    textDecoration = if (showDone) TextDecoration.None else TextDecoration.LineThrough,
                )
            }
        }
        items(completedList, key = { it.id }) {
            AnimatedVisibility(visible = showDone) {
                ToDoItem(
                    task = it,
                    taskColor,
                    onSwipeToDelete = { onSwipeToDelete(it) },
                    onTaskItemClick = { onTaskItemClick(it) },
                    onCheckItemClick = { onCheckItemClick(it) }
                )
            }
        }
    }
}

@Composable
fun ToDoItem(
    task: ToDoTask,
    taskColor: ToDoColor,
    onSwipeToDelete: () -> Unit,
    onTaskItemClick: () -> Unit,
    onCheckItemClick: () -> Unit,
) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = taskColor.color(),
            onPrimary = taskColor.onColor()
        )
    ) {
        SwipeDismiss(
            backgroundModifier = Modifier.clip(
                MaterialTheme.shapes.medium
            ),
            backgroundSecondaryModifier = Modifier.clip(
                MaterialTheme.shapes.medium
            ),
            onDismiss = { onSwipeToDelete() }
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(SmallPadding)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { onTaskItemClick() }
                    .padding(SmallPadding),
            ) {

                val (titleText, dueDateText, doneCheckbox) = createRefs()

                Checkbox(
                    checked = task.isComplete(),
                    onCheckedChange = { onCheckItemClick() },
                    modifier = Modifier.constrainAs(doneCheckbox) {
                        top.linkTo(parent.top)
                    },
                )
                Text(
                    text = task.name,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.constrainAs(titleText) {
                        top.linkTo(doneCheckbox.top)
                        bottom.linkTo(doneCheckbox.bottom)
                        start.linkTo(doneCheckbox.end, margin = SmallPadding)
                    }
                )
                if (task.isDueDateTimeSet() && !task.isComplete())
                    Text(
                        text = task.dueDate?.formatDateTime() ?: "",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.small)
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(SmallPadding)
                            .constrainAs(dueDateText) {
                                top.linkTo(titleText.bottom, margin = SmallPadding)
                                start.linkTo(titleText.start)
                            }
                    )
            }
        }
    }
}

@Preview
@Composable
fun ItemPrev1() {
    ToDoItem(
        task = ToDoTask(
            name = "Title",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            status = ToDoStatus.COMPLETE
        ),
        taskColor = ToDoColor.BLUE,
        onSwipeToDelete = { },
        onTaskItemClick = { }) {}
}

@Preview
@Composable
fun ItemPrev2() {
    ToDoItem(
        task = ToDoTask(
            name = "Title",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            dueDate = LocalDateTime.now()
        ),
        taskColor = ToDoColor.BLUE,
        onSwipeToDelete = { },
        onTaskItemClick = { }) {}
}