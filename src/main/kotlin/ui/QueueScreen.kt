package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import datastructures.CustomQueue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QueueScreen(queue: CustomQueue<String>) {

    var inputText by remember { mutableStateOf("") }
    var isPopupVisible by remember { mutableStateOf(false) }


    Column(Modifier.padding(16.dp)) {
        Text(
            "Queue",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        DropdownMenu(
            expanded = isPopupVisible,
            onDismissRequest = { isPopupVisible = false },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("What is a Queue?", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("A Queue Data Structure is a fundamental concept in computer science used for storing and managing data in a specific order. It follows the principle of \"First in, First out\" (FIFO), where the first element added to the queue is the first one to be removed.")
        }

        ExtendedFloatingActionButton(
            icon = { Icon(Icons.Filled.Info, contentDescription = "Info") },
            text = { Text("Info") },
            onClick = { isPopupVisible = !isPopupVisible }
        )


        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Enter element") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                if (inputText.isNotBlank()) {
                    queue.enqueue(inputText)
                }
                inputText = ""
            }) {
                Text("Enqueue")
            }

            Button(onClick = { queue.dequeue() }) {
                Text("Dequeue")
            }

            Button(onClick = { while (!queue.isEmpty()) queue.dequeue() }) {
                Text("Clear")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Queue Size: ${queue.size()}")
        Spacer(modifier = Modifier.height(8.dp))

        Text("Queue Elements:")
        Spacer(modifier = Modifier.height(8.dp))

        for (i in 0 until queue.size()) {
            queue.peek(i)?.let { Text(it) }
        }
    }
}
