package com.example.todo.presentation.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.domian.model.ToDoList
import com.example.todo.presentation.theme.ToDoTheme
import com.example.todo.presentation.util.dummyRandomList

@Composable
fun CategoryListField(
    list: List<ToDoList>,
    onListItemClick: (ToDoList) -> Unit,
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
                    itemNumber = it.tasks.size,
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
                    itemNumber = it.tasks.size,
                    onListItemClick = { onListItemClick(it) }
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                CategoryItem(
                    icon = list[lastIndex].icon,
                    iconBackgroundColor = list[lastIndex].color,
                    title = list[lastIndex].name,
                    itemNumber = list[lastIndex].tasks.size,
                    onListItemClick = { onListItemClick(list[lastIndex]) }
                )
            }
        }
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Preview(
    showBackground = true
)
@Composable
private fun CategoryListPreview() {
    val dummyData = dummyRandomList()
    ToDoTheme {
        CategoryListField(dummyData, {})
    }
}


