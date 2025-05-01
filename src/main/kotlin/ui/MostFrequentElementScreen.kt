package ui


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import utils.mostFrequentElement

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostFrequentElementScreen() {
    var inputListText by remember { mutableStateOf("") }
    var isPopupVisible by remember { mutableStateOf(false) }

    val inputList = inputListText
        .split(",", "\n", " ")
        .map { it.trim() }
        .filter { it.isNotEmpty() }

    val mostFrequent = mostFrequentElement(inputList)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Most Frequent Element",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isPopupVisible) {
            Text(
                "This algorithm finds the most frequently occurring element in a list. You can enter items separated by commas, spaces, or new lines.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        OutlinedTextField(
            value = inputListText,
            onValueChange = { inputListText = it },
            label = { Text("Enter elements (comma, space, or newline separated)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { isPopupVisible = !isPopupVisible }) {
            Icon(Icons.Default.Info, contentDescription = "Info")
            Spacer(modifier = Modifier.width(4.dp))
            Text("What is this?")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (mostFrequent != null)
                "Most Frequent Element: $mostFrequent"
            else
                "No elements entered.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
