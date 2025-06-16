package com.example.ckdiplom

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun User() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var currentScreen by remember { mutableStateOf("menu") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Меню", modifier = Modifier.padding(16.dp), fontSize = 18.sp)
                NavigationDrawerItem(label = { Text("Вывести") }, selected = false, onClick = {
                    currentScreen = "select"
                    scope.launch { drawerState.close() }
                })
                NavigationDrawerItem(label = { Text("Добавить") }, selected = false, onClick = {
                    currentScreen = "add"
                    scope.launch { drawerState.close() }
                })
                NavigationDrawerItem(label = { Text("Изменить") }, selected = false, onClick = {
                    currentScreen = "update"
                    scope.launch { drawerState.close() }
                })
                NavigationDrawerItem(label = { Text("Удалить") }, selected = false, onClick = {
                    currentScreen = "delete"
                    scope.launch { drawerState.close() }
                })
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Пользователь") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Меню")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (currentScreen) {
                    "menu" -> Text(
                        "Выберите действие из меню",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(32.dp)
                    )

                    "select" -> Select()
                    "add" -> Add()
                    "update" -> Update()
                    "delete" -> Delete()
                }
            }
        }
    }
}