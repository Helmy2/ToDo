package com.example.todo.presentation.home.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.domian.model.*
import com.example.todo.presentation.theme.CommonGrey
import com.example.todo.presentation.theme.MediumPadding
import com.example.todo.presentation.theme.SmallPadding
import com.example.todo.presentation.theme.ToDoTheme

@Composable
fun CategoryItem(
    icon: ImageVector,
    iconBackgroundColor: ToDoColor,
    title: String,
    itemNumber: Int,
    onListItemClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SmallPadding)
            .clip(MaterialTheme.shapes.large)
            .clickable { onListItemClick() }
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(MediumPadding)
    ) {
        Column {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(iconBackgroundColor.color())
                    .padding(SmallPadding),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(MediumPadding))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = CommonGrey
            )
        }
        Text(
            text = itemNumber.toString(),
            modifier = Modifier.align(Alignment.TopEnd),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    widthDp = 200
)
@Preview(
    showBackground = true,
    widthDp = 200
)
@Composable
private fun CategoryItemPreview() {
    ToDoTheme {
        CategoryItem(
            Icons.Default.CalendarToday,
            ToDoColor.GREEN,
            "Today",
            4
        ) {}
    }
}
