package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import utils.countVowelsAndConsonants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VowelConsonantScreen() {
    var inputText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    var isPopupVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Vowels & Consonants",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        DropdownMenu(
            expanded = isPopupVisible,
            onDismissRequest = { isPopupVisible = false },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("What does this do?", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("This tool counts how many vowels and consonants are present in your input. Only alphabetic characters are considered.")
        }

        ExtendedFloatingActionButton(
            icon = { Icon(Icons.Filled.Info, contentDescription = "Info") },
            text = { Text("Info") },
            onClick = { isPopupVisible = !isPopupVisible }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter text") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            result = countVowelsAndConsonants(inputText)
        }) {
            Text("Count")
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text("Vowels: ${it.first}")
            Text("Consonants: ${it.second}")
        }
    }
}
