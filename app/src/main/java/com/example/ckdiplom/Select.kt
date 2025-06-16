package com.example.ckdiplom

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

class QuestionStep(
    val question: String,
    val options: List<String>,
    val onOptionSelected: (String) -> Unit,
    var answered: Boolean = false
)

@Composable
fun Select() {
    var query by remember { mutableStateOf("SELECT ") }

    // Состояние текущего вопроса
    var currentStep by remember { mutableStateOf<QuestionStep?>(null) }

    var requestTriggered by remember { mutableStateOf(false) }

    // При первом запуске показываем первый вопрос
    LaunchedEffect(Unit) {
        if (currentStep == null) {
            currentStep = QuestionStep(
                question = "Данные из какой таблицы вы хотите вывести?",
                options = listOf("students", "employees", "products"),
                onOptionSelected = { selected ->
                    query = "SELECT * FROM $selected"
                    // Следующий вопрос
                    currentStep = QuestionStep(
                        question = "Какие поля хотите вывести?",
                        options = listOf("Все поля", "Имя и возраст", "ID и должность"),
                        onOptionSelected = { fieldChoice ->
                            query = when (fieldChoice) {
                                "Все поля" -> query
                                "Имя и возраст" -> query.replace("*", "name, age")
                                "ID и должность" -> query.replace("*", "id, position")
                                else -> query
                            }
                            // Следующий вопрос
                            currentStep = QuestionStep(
                                question = "Нужно ли добавить условие WHERE?",
                                options = listOf("Нет", "age > 20", "position = 'Manager'"),
                                onOptionSelected = { condition ->
                                    if (condition != "Нет") {
                                        query += " WHERE $condition"
                                    }
                                    // Можно продолжить цепочку или завершить
                                    currentStep = null
                                }
                            )
                        }
                    )
                }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Строка SQL-запроса
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("SQL-запрос") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        // Отображение текущего вопроса
        currentStep?.let { step ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(step.question, fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                step.options.forEach { option ->
                    Button(
                        onClick = { step.onOptionSelected(option) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(option)
                    }
                }
            }
        }

        if (currentStep == null && !requestTriggered) {
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    requestTriggered = true // ✅ включаем флаг
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Выполнить запрос")
            }
        }

        // Вызов @Composable MakeRequest
        if (requestTriggered) {
            MakeRequest(query)
        }
    }
}

