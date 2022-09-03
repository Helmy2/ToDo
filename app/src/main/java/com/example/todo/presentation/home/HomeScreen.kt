package com.example.todo.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todo.domian.model.ToDoList
import com.example.todo.presentation.home.components.CategoryListField
import com.example.todo.presentation.home.components.HomeBottomBar
import com.example.todo.presentation.home.components.HomeTopBar
import com.example.todo.presentation.home.components.ToDoList
import com.example.todo.presentation.theme.LargePadding
import com.example.todo.presentation.theme.MediumPadding
import com.example.todo.presentation.theme.SmallPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    lists: List<ToDoList>,
    categories: List<ToDoList>,
    onDrawerClicked: () -> Unit,
    onAddListClicked: () -> Unit,
    onAddToDoClicked: () -> Unit,
    onSearchClick: (String) -> Unit,
    onListItemClick: (ToDoList) -> Unit,
    onCategoryItemClick: (ToDoList) -> Unit,
    onDeleteListItemClick: (ToDoList) -> Unit,
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
        bottomBar = {
            HomeBottomBar(
                onAddListClicked = onAddListClicked,
                onAddToDoClicked = onAddToDoClicked,
                modifier = Modifier.padding(
                    horizontal = LargePadding, vertical = SmallPadding
                )
            )
        },
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


