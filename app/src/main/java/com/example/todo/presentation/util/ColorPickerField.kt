package com.example.todo.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo.domian.model.ToDoColor
import com.example.todo.domian.model.color
import com.example.todo.presentation.theme.MediumPadding
import com.example.todo.presentation.theme.SmallPadding
import com.example.todo.presentation.theme.ToDoTheme

@Composable
fun ColorPickerField(
    selectedColor: ToDoColor,
    onSelectedClicked: (ToDoColor) -> Unit,
    onCancelClicked: () -> Unit,
) {
    val colorList = remember {
        ToDoColor.values()
    }
    var selectedIndex by remember {
        mutableStateOf(colorList.indexOf(selectedColor))
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(SmallPadding),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(MediumPadding)
    ) {
        Text(
            text = "Select a color",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        LazyVerticalGrid(columns = GridCells.Fixed(3), userScrollEnabled = false) {
            items(colorList.size) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth(.2f)
                        .aspectRatio(1f)
                        .padding(SmallPadding)
                        .clip(CircleShape)
                        .background(colorList[index].color())
                        .clickable { selectedIndex = index }
                        .padding(SmallPadding)
                ) {
                    if (index == selectedIndex)
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            modifier = Modifier.fillMaxSize(),
                            tint = Color.White
                        )
                }
            }
        }
        Row(
            modifier = Modifier.align(Alignment.End),
            horizontalArrangement = Arrangement.spacedBy(SmallPadding)
        ) {
            TextButton(onClick = onCancelClicked) {
                Text(text = "Cancel")
            }
            TextButton(onClick = { onSelectedClicked(colorList[selectedIndex]) }) {
                Text(text = "Select")
            }
        }
    }
}

@Preview
@Composable
fun ColorPickerFieldPreview() {
    ToDoTheme {
        ColorPickerField(ToDoColor.BLUE, {}, {})
    }
}