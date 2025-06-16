package com.example.ckdiplom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MakeRequest(query: String) {
    Text("Запрос выполнен: $query")
    // Здесь можно добавить побочные действия (например, навигацию, логирование и пр.)
}