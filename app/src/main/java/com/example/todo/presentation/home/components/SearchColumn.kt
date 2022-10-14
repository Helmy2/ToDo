package com.example.todo.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask
import com.example.todo.presentation.list.components.TaskEditDialog
import com.example.todo.presentation.theme.SmallPadding
import com.example.todo.presentation.util.SwipeDismiss
import com.example.todo.util.formatDateTime

@Composable
fun SearchColumn(
    list: List<ToDoList>,
    onSwipeToDelete: (ToDoTask) -> Unit,
    onTaskItemClick: (ToDoTask, listId: String) -> Unit,
    onCheckItemClick: (task: ToDoTask, listId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (list.isNotEmpty()) {
        LazyColumn(modifier) {
            list.forEach { toDoList ->
                item {
                    Text(
                        text = toDoList.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(
                            SmallPadding
                        )
                    )
                }

                items(toDoList.tasks, key = { it.id }) {
                    ToDoItem(
                        task = it,
                        onSwipeToDelete = { onSwipeToDelete(it) },
                        onTaskItemClick = { onTaskItemClick(it, toDoList.id) },
                        onCheckItemClick = { onCheckItemClick(it, toDoList.id) }
                    )
                }
            }
        }
    }
}


@Composable
private fun ToDoItem(
    task: ToDoTask,
    onSwipeToDelete: () -> Unit,
    onTaskItemClick: (ToDoTask) -> Unit,
    onCheckItemClick: () -> Unit,
) {
    var currentTask: ToDoTask? by remember { mutableStateOf(null) }

    currentTask?.let {
        TaskEditDialog(
            task = it,
            onSave = { editToDoTask ->
                currentTask = null
                onTaskItemClick(editToDoTask)
            },
            onCancel = {
                currentTask = null
            }
        )
    }

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
                .clickable { currentTask = task }
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