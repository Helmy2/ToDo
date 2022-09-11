package com.example.todo.presentation.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.todo.domian.model.ToDoList
import com.example.todo.domian.model.ToDoTask
import com.example.todo.presentation.list.components.ToDoBottomSheet
import com.example.todo.presentation.list.components.ListScreenTopBar
import com.example.todo.presentation.list.components.TaskList
import com.example.todo.presentation.theme.LargePadding
import com.example.todo.presentation.theme.MediumPadding
import com.example.todo.presentation.util.getCurrentDate
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun ListScreen(
    toDoList: ToDoList,
    onItemClick: (ToDoTask) -> Unit,
    onSwipeToDelete: (ToDoTask) -> Unit,
    onCheckItemClick: (ToDoTask) -> Unit,
    onAddToDoItemClick: (title: String, note: String, dueDate: Long?, listId: String) -> Unit,
    onBackClicked: () -> Unit,
) {
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val keyboard = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = bottomSheetState) {
        if (bottomSheetState.currentValue == ModalBottomSheetValue.Hidden)
            keyboard?.hide()
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            ToDoBottomSheet { title, note, date ->
                onAddToDoItemClick(
                    title, note, date,
                    toDoList.id
                )
                coroutineScope.launch {
                    bottomSheetState.hide()
                    keyboard?.hide()
                }
            }
        },
    ) {
        Scaffold(
            topBar = { ListScreenTopBar(onBackClicked, toDoList) },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    coroutineScope.launch { bottomSheetState.show() }
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = MediumPadding)
            ) {
                TaskList(
                    list = toDoList.tasks,
                    taskColor = toDoList.color,
                    onSwipeToDelete = onSwipeToDelete,
                    onTaskItemClick = onItemClick,
                    onCheckItemClick = onCheckItemClick
                )
            }
        }
    }
}

