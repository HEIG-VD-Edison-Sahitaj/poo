package test;

import calculator.utils.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the Stack class
 */
class StackTest {
    private Stack<Integer> stack;

    /**
     * Set up the stack before each test
     */
    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    /**
     * Test the push operation
     */
    @Test
    void testPush() {
        stack.push(10);
        stack.push(20);
        assertFalse(stack.isEmpty());
        assertEquals(2, stack.size());
    }

    /**
     * Test the pop operation
     */
    @Test
    void testPop() {
        stack.push(10);
        stack.push(20);
        int poppedValue = stack.pop();
        assertEquals(20, poppedValue, "Pop should return the last pushed value");
        assertEquals(1, stack.size());
    }

    /**
     * Test popping from an empty stack
     */
    @Test
    void testPopEmptyStack() {
        assertThrows(EmptyStackException.class, stack::pop);
    }

    /**
     * Test if the stack is empty after pop
     */
    @Test
    void testIsEmpty() {
        assertTrue(stack.isEmpty(), "Newly created stack should be empty");
        stack.push(5);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    /**
     * Tests the size of the stack after push and pop operations
     */
    @Test
    void testSize() {
        assertEquals(0, stack.size(), "Initial stack size should be 0");
        stack.push(10);
        stack.push(20);
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
    }

    /**
     * Test converting the stack to an Object array
     */
    @Test
    void testToArray() {
        stack.push(10);
        stack.push(20);
        Object[] expectedArray = {20, 10};
        assertArrayEquals(expectedArray, stack.toArray(), "toArray should return elements in LIFO order");
    }

    /**
     * Test iterators of the stack
     */
    @Test
    void testIterator() {
        stack.push(10);
        stack.push(20);
        Iterator<Integer> iterator = stack.iterator();
        assertTrue(iterator.hasNext(), "Iterator should have elements");
        assertEquals(20, iterator.next(), "First element from iterator should be the top of the stack");
        assertEquals(10, iterator.next(), "Second element from iterator should be the next in the stack");
        assertFalse(iterator.hasNext(), "Iterator should have no more elements after traversing the stack");
    }

    /**
     * Test converting the stack to a string
     */
    @Test
    void testToString() {
        stack.push(10);
        stack.push(20);
        assertEquals("20, 10", stack.toString(), "toString should return elements in LIFO order, separated by ', '");
    }
}
