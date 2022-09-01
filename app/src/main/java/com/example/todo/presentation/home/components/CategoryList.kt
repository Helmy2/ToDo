package com.example.todo.presentation.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.ToDoGroup
import com.example.todo.presentation.theme.ToDoTheme
import com.example.todo.presentation.util.dummyRandomGroup

@Composable
fun CategoryListField(
    list: List<ToDoGroup>,
    onListItemClick: (ToDoGroup) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = modifier
    ) {
        if (list.size % 2 == 0) {
            items(
                list,
            ) {
                CategoryItem(
                    icon = it.icon,
                    iconBackgroundColor = it.iconColor,
                    title = it.name,
                    itemNumber = it.lists.size,
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
                    iconBackgroundColor = it.iconColor,
                    title = it.name,
                    itemNumber = it.lists.size,
                    onListItemClick = { onListItemClick(it) }
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                CategoryItem(
                    icon = list[lastIndex].icon,
                    iconBackgroundColor = list[lastIndex].iconColor,
                    title = list[lastIndex].name,
                    itemNumber = list[lastIndex].lists.size,
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
    val dummyData = dummyRandomGroup()
    ToDoTheme {
        CategoryListField(dummyData, {})
    }
}


