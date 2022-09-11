package com.example.todo.presentation.home.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todo.domian.model.Category
import com.example.todo.domian.model.ToDoList

@Composable
fun CategoryListField(
    list: List<Category>,
    onListItemClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        userScrollEnabled = false
    ) {
        if (list.size % 2 == 0) {
            items(
                list,
            ) {
                CategoryItem(
                    icon = it.icon,
                    iconBackgroundColor = it.color,
                    title = it.name,
                    itemNumber = it.list.size,
                    onListItemClick = { onListItemClick(it) }
                )
            }
        } else {
            val lastIndex = list.size - 1
            items(
                list.subList(0, lastIndex),
            ) {
                CategoryItem(
                    icon = it.icon,
                    iconBackgroundColor = it.color,
                    title = it.name,
                    itemNumber = it.list.size,
                    onListItemClick = { onListItemClick(it) }
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                CategoryItem(
                    icon = list[lastIndex].icon,
                    iconBackgroundColor = list[lastIndex].color,
                    title = list[lastIndex].name,
                    itemNumber = list[lastIndex].list.size,
                    onListItemClick = { onListItemClick(list[lastIndex]) }
                )
            }
        }
    }
}

