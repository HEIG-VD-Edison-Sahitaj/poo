package calculator.utils;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * A generic stack implementation
 * @param <T> the type of elements in this stack
 */
public class Stack<T> implements Iterable<T> {
    /**
     * A node in the stack
     * @param <T> the type of element in this node
     */
    private static class Node<T> {
        private T element; // The element in this node
        private Node<T> next; // The next node in the stack

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node<T> top; // The top of the stack
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    /**
     * Adds an element to the top of the stack
     * @param element the element to add
     */
    public void push(T element) {
        top = new Node<>(element, top);
        size++;
    }

    /**
     * Removes and return the element at the top of the stack
     * @return the element at the top of the stack
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T data = top.element;
        top = top.next;
        size--;
        return data;
    }

    /**
     * Checks if the stack is empty
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    /**
     * Converts the stack to an array
     * @return an array representation of the stack
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        Node<T> current = top;
        int index = 0;
        while (current != null) {
            array[index++] = current.element;
            current = current.next;
        }
        return array;
    }

    /**
     * Iterates over the elements in the stack
     * @return an iterator over the elements in the stack
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = top; // The current node

            // Checks if there is a next element in the stack
            @Override
            public boolean hasNext() {
                return current != null;
            }

            // Returns the next element in the stack
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more elements");
                }
                T data = current.element;
                current = current.next;
                return data;
            }
        };
    }

    /**
     * String representation of the stack
     * @return a string representation of the stack
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = top;
        while (current != null) {
            sb.append(current.element);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        return sb.toString();
    }
}