package com.example.ckdiplom


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Delete() {
    Column(modifier = Modifier.padding(32.dp)) {
        Text("Экран: Удалить", fontSize = 22.sp)
    }
}
