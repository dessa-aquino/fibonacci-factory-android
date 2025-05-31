package com.example.fibonaccifactory

import FibonacciViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fibonaccifactory.presentation.navigation.AppNavigation
import com.example.fibonaccifactory.ui.theme.FibonacciFactoryTheme
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FibonacciFactoryTheme {
                AppNavigation()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FibonacciActivity(
    viewModel: FibonacciViewModel = viewModel(),
    onNavigateToSummary: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fibonacci Calculator") },
                actions = {
                    IconButton(onClick = onNavigateToSummary) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "Summary"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FibonacciInput(paddingValues)
            FibonacciResultList(paddingValues)
        }
    }
}

@Composable
fun FibonacciInput(
    innerPadding: PaddingValues
) {
    val viewModel: FibonacciViewModel = viewModel()
    var textInput by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
    ) {
        TextField(
            value = textInput,
            onValueChange = { newValue ->
                textInput = newValue.filter { it.isDigit() }
            },
            label = { Text("Enter Fibonacci number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    textInput.toIntOrNull()?.let { number ->
                        viewModel.calculateAndUpdateSequence(number)
                        keyboardController?.hide()
                    }
                    textInput = ""

                }
            )
        )
    }
}

@Composable
fun FibonacciResultList(
    innerPadding: PaddingValues
) {
    val viewModel: FibonacciViewModel = viewModel()
    val results by viewModel.fibonacciResults.collectAsState()

    LazyColumn (
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
    ) {
        items(results) { result ->
            ListItem(
                headlineContent = {
                    Text("${result.index}: ${result.value}")
                }
            )
        }
    }
}

fun calculateFibonacci(n: Int): Int {

    if (n < 0) {
        throw IllegalArgumentException("Input must be non-negative")
    }

    if (n == 0) return 0
    if (n == 1) return 1

    var a = 0
    var b = 1

    for (i in 2..n) {
        val temp = a + b
        a = b
        b = temp
        println("B: $b")
    }

    return b
}
