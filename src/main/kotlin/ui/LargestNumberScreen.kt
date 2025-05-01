package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import utils.findLargestNumber

@Composable
fun LargestNumberScreen() {
    var inputText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Find Largest Number",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter numbers (comma-separated)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val numbers = inputText.split(",")
                .mapNotNull { it.trim().toIntOrNull() }

            val largest = findLargestNumber(numbers)
            result = if (largest != null) {
                "The largest number is $largest"
            } else {
                "Please enter at least one valid number."
            }
        }) {
            Text("Find Largest")
        }

        result?.let {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = it, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
