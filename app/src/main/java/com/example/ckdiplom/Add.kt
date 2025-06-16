package com.example.ckdiplom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Add() {
    Column(modifier = Modifier.padding(32.dp)) {
        Text("Экран: Добавить", fontSize = 22.sp)
    }
}
