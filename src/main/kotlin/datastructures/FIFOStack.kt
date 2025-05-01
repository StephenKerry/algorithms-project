package datastructures

import interfaces.Stack
import java.util.concurrent.CopyOnWriteArrayList

/**
 * A First-In-First-Out (FIFO) stack implementation that conforms to the [Stack] interface.
 *
 * This class uses a thread-safe [CopyOnWriteArrayList] to manage its elements, ensuring safe usage in concurrent scenarios.
 * Items are added to the end of the stack and removed from the beginning, adhering to the FIFO principle.
 *
 * @param T The type of elements stored in this stack.
 */
class FIFOStack<T> : Stack<T> {

    /**
     * Internal storage for stack elements, implemented as a [CopyOnWriteArrayList].
     */
    private val stack = CopyOnWriteArrayList<T>()

    /**
     * Adds an element to the end of the stack.
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
    override fun pushAll(items: Iterable<T>) = items.forEach { push(it) }

    /**
     * Removes and returns the first element of the stack (FIFO order), or `null` if the stack is empty.
     *
     * @return The first element of the stack, or `null` if the stack is empty.
     */
    override fun pop(): T? = if (isEmpty()) null else stack.removeAt(0)

    /**
     * Removes and returns all elements in the stack, maintaining their original order.
     *
     * @return A list of all elements removed from the stack.
     */
    override fun popAll(): List<T> {
        val tempStack = arrayListOf<T>()
        for (item in stack) {
            pop()?.let { tempStack.add(it) }
        }
        return tempStack
    }

    /**
     * Returns the first element of the stack without removing it, or `null` if the stack is empty.
     *
     * @return The first element of the stack, or `null` if the stack is empty.
     */
    override fun peek(): T? = stack.firstOrNull()

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
    override fun isEmpty() = stack.isEmpty()

    /**
     * Returns a string representation of the stack, with elements separated by newlines.
     *
     * @return A string representing the stack's contents.
     */
    override fun toString(): String = stack.joinToString(separator = "\n")
}

/**
 * Utility function to create a [FIFOStack] with the given [elements].
 *
 * @param T The type of elements to store in the stack.
 * @param elements The elements to initialize the stack with.
 * @return A [FIFOStack] containing the provided elements.
 */
fun <T> fifoStackOf(vararg elements: T): Stack<T> {
    val listStack = FIFOStack<T>()
    listStack.pushAll(elements.asList())
    return listStack
}