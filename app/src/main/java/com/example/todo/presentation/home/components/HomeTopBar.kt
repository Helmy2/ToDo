package com.example.todo.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeTopBar(
    onDrawerIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onDrawerIconClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Drawer Icon",
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeTopBarPreview() {
    HomeTopBar(onDrawerIconClick = { })
}