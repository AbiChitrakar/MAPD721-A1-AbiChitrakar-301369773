package com.example.mapd721_a1_abichitrakar_301369773

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mapd721_a1_abichitrakar_301369773.data.StoreStudentData
import com.example.mapd721_a1_abichitrakar_301369773.ui.theme.MAPD721A1AbiChitrakar301369773Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MAPD721A1AbiChitrakar301369773Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreStudentData(context)

    val savedUsernameState by dataStore.getUsername.collectAsState(initial = "")
    val savedEmailState by dataStore.getEmail.collectAsState(initial = "")
    val savedIdState by dataStore.getID.collectAsState("")

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Username", color = Color.Gray, fontSize = 12.sp) }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email", color = Color.Gray, fontSize = 12.sp) }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = id,
            onValueChange = { id = it },
            label = { Text(text = "ID", color = Color.Gray, fontSize = 12.sp) }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(8.dp),
                onClick = {
                    username = savedUsernameState ?: ""
                    email = savedEmailState ?: ""
                    id = savedIdState ?: ""
                }
            ) {
                Text(text = "Load", color = Color.White, fontSize = 18.sp)
            }

            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(8.dp),
                onClick = {
                    scope.launch {
                        dataStore.clearStudentData()
                        username = ""
                        email = ""
                        id = ""
                    }
                }
            ) {
                Text(text = "Clear", color = Color.White, fontSize = 18.sp)
            }

            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(8.dp),
                onClick = {
                    scope.launch {
                        dataStore.studentsData(username, email, id)
                        username = ""
                        email = ""
                        id = ""
                    }
                }
            ) {
                Text(text = "Save", color = Color.White, fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(width = 1.dp, color = Color.Black)
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = "Name: Abi Chitrakar\nStudent ID: 301369773",
                color = Color.Blue,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MAPD721A1AbiChitrakar301369773Theme {
        MainScreen()
    }
}
