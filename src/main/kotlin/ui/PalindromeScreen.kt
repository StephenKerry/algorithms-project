package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import utils.isPalindrome

@Composable
fun PalindromeScreen() {
    var inputText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Palindrome Checker",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter text") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            result = if (isPalindrome(inputText)) {
                "\"$inputText\" is a palindrome"
            } else {
                "\"$inputText\" is NOT a palindrome"
            }
        }) {
            Text("Check Palindrome")
        }

        result?.let {
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = it, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
