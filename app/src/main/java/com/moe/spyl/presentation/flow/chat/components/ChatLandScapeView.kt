package com.moe.spyl.presentation.flow.chat.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatLandScapeView() {
    Row(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(50) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray)
                        .padding(16.dp),
                    text = "Item $it",
                    fontSize = 25.sp,

                    )
            }
        }

        // Opposite side — flip the entire column
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .graphicsLayer { rotationZ = 180f } // flip list for the opposite viewer
        ) {
            items(50) { i ->
                // Do NOT rotate the item back; it’s supposed to be upright for them,
                // which will look upside-down to you (that’s correct).
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green)
                        .padding(16.dp),
                    text = "Item $i",
                    fontSize = 25.sp
                )
            }
        }
    }
}