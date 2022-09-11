package com.example.todo.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.todo.domian.model.Category
import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.ToDoList
import com.example.todo.presentation.home.components.CategoryListField
import com.example.todo.presentation.home.components.HomeTopBar
import com.example.todo.presentation.home.components.ListBottomSheet
import com.example.todo.presentation.home.components.ToDoList
import com.example.todo.presentation.theme.MediumPadding
import com.example.todo.presentation.theme.SmallPadding
import kotlinx.coroutines.launch


@OptIn(
    ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun HomeScreen(
    lists: List<ToDoList>,
    categories: List<Category>,
    onDrawerClicked: () -> Unit,
    onAddListClicked: (title: String, color: ToDoColor) -> Unit,
    onSearchClick: (String) -> Unit,
    onListItemClick: (ToDoList) -> Unit,
    onCategoryItemClick: (Category) -> Unit,
    onDeleteListItemClick: (ToDoList) -> Unit,
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
            ListBottomSheet(ToDoColor.BLUE) { title, color ->
                onAddListClicked(title, color)
                coroutineScope.launch {
                    bottomSheetState.hide()
                    keyboard?.hide()
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                HomeTopBar(
                    onDrawerIconClick = onDrawerClicked,
                    onSearchIconClick = onSearchClick,
                    modifier = Modifier.padding(
                        top = MediumPadding, start = MediumPadding, end = MediumPadding
                    )
                )
            },
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
                    .padding(horizontal = MediumPadding),
                verticalArrangement = Arrangement.spacedBy(SmallPadding)
            ) {
                CategoryListField(
                    categories,
                    onListItemClick = onCategoryItemClick,
                )
                Text(
                    text = "My Lists",
                    modifier = Modifier.padding(horizontal = SmallPadding),
                    style = MaterialTheme.typography.titleMedium
                )
                ToDoList(
                    list = lists,
                    onListItemClick = onListItemClick,
                    onDeleteItemClick = onDeleteListItemClick,
                )
            }
        }
    }
}


