package datastructures

import interfaces.Stack
import java.util.concurrent.CopyOnWriteArrayList

/**
 * A Last-In-First-Out (LIFO) stack implementation that conforms to the [Stack] interface.
 *
 * This class uses a thread-safe [CopyOnWriteArrayList] to manage its elements, ensuring safe usage in concurrent scenarios.
 * Items are added to and removed from the end of the stack, adhering to the LIFO principle.
 *
 * @param T The type of elements stored in this stack.
 */
class LIFOStack<T> : Stack<T> {

    /**
     * Internal storage for stack elements, implemented as a [CopyOnWriteArrayList].
     */
    private val stack = CopyOnWriteArrayList<T>()

    /**
     * Adds an element to the top of the stack.
     *
     * @param element The element to be added.
     */
    override fun push(element: T) {
        stack.add(element)
    }

    /**
     * Adds all elements from the given [Iterable] to the stack.
     *
     * @param items The iterable collection of elements to add.
     */
    override fun pushAll(items: Iterable<T>) {
        for (item in items) {
            push(item)
        }
    }

    /**
     * Removes and returns the top element of the stack (LIFO order), or `null` if the stack is empty.
     *
     * @return The top element of the stack, or `null` if the stack is empty.
     */
    override fun pop(): T? {
        if (isEmpty()) {
            return null
        }
        return stack.removeAt(size() - 1)
    }

    /**
     * Removes and returns all elements in the stack, maintaining their reverse order (LIFO).
     *
     * @return A list of all elements removed from the stack.
     */
    override fun popAll(): List<T> {
        synchronized(stack) {
            val tempStack = arrayListOf<T>()
            for (item in stack) {
                pop()?.let { tempStack.add(it) }
            }
            return tempStack
        }
    }

    /**
     * Returns the top element of the stack without removing it, or `null` if the stack is empty.
     *
     * @return The top element of the stack, or `null` if the stack is empty.
     */
    override fun peek(): T? = stack.lastOrNull()

    /**
     * Returns the element at the specified [index] without removing it, or `null` if the index is out of bounds.
     *
     * @param index The index of the element to peek at.
     * @return The element at the specified index, or `null` if the index is out of bounds.
     */
    override fun peek(index: Int): T? = stack.getOrNull(index)

    /**
     * Returns the number of elements in the stack.
     *
     * @return The size of the stack.
     */
    override fun size() = stack.size

    /**
     * Checks whether the stack is empty.
     *
     * @return `true` if the stack is empty, `false` otherwise.
     */
    override fun isEmpty() = size() == 0

    /**
     * Returns a string representation of the stack, with each element on a new line.
     *
     * @return A string representing the stack's contents.
     */
    override fun toString() = buildString {
        stack.forEach { appendLine("$it") }
    }
}

/**
 * Utility function to create a [LIFOStack] with the given [elements].
 *
 * @param T The type of elements to store in the stack.
 * @param elements The elements to initialize the stack with.
 * @return A [LIFOStack] containing the provided elements.
 */
fun <T> lifoStackOf(vararg elements: T): Stack<T> {
    val listStack = LIFOStack<T>()
    listStack.pushAll(elements.asList())
    return listStack
}