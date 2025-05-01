package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import utils.countOccurrences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountOccurrencesScreen() {
    var haystack by remember { mutableStateOf("") }
    var needle by remember { mutableStateOf("") }
    var isPopupVisible by remember { mutableStateOf(false) }

    val count = remember(haystack, needle) {
        countOccurrences(haystack, needle)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            "Count Occurrences",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Info dropdown
        if (isPopupVisible) {
            Text(
                "This algorithm counts how many times the search string (needle) appears in the input text (haystack), ignoring case.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        OutlinedTextField(
            value = haystack,
            onValueChange = { haystack = it },
            label = { Text("Enter full text (haystack)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = needle,
            onValueChange = { needle = it },
            label = { Text("Enter word to count (needle)") },
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
            text = "Occurrences of \"$needle\": $count",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
