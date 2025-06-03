package com.example.fibonaccifactory.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.fibonaccifactory.presentation.viewmodel.FibonacciViewModel

@Composable
fun FibonacciInput(
    viewModel: FibonacciViewModel,
    textInput: String,
    onTextInputChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = textInput,
        onValueChange = { newValue ->
            onTextInputChange(newValue.filter { it.isDigit() })
        },
        label = { Text("Enter a number") },
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
                onTextInputChange("")
            }
        ),
        singleLine = true
    )
} 