package collections;

import java.util.EmptyStackException;
import collections.StackOverflowException;

/**
 * Represents an array implementation of a stack.
 * 
 * @author Yurdaer Dalkic
 *
 * @param <T>
 */
public class ArrayStack<T> implements Stack<T> {
	private T[] elements;
	private int size = 0;

	/**
	 * Constructs an empty stack with 20 capacity.
	 * @param capacity
	 */
	public ArrayStack(int capacity) {
		elements = (T[]) (new Object[capacity]);
	}
	  /**
     * Placerar ett element i stacken.
     * @param element elementet att lägga på stacken
     */
	public void push(T element) {
		if (size >= elements.length)
			throw new StackOverflowException();
		elements[size] = element;
		size++;
	}
	 /**
     * Returnerar det element som senast placerades i stacken. Elementet tas bort från stacken.
     * @return det element som senast placerades i stacken
     */
	public T pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return elements[--size];
	}
	 /**
     * Returnerar det element som senast placerade i stacken. Elementet är kvar i stacken.
     * @return det element som senast placerades i stacken
     */
	public T peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return elements[size - 1];
	}
	 /**
     * Returnerar true om stacken inte innehåller några element och false om det finns element i stacken.
     * @return
     */
	public boolean isEmpty() {
		return (size == 0);
	}
	 
    /**
     * Returnerar antalet element som finns i stacken.
     * @return antalet element som finns i stacken
     */
	public int size() {
		return size;
	}

	public static void main(String[] args) {
//		ArrayStack<Integer> stack = new ArrayStack<Integer>(20);
//		Integer elem;
//		for (int i = 0; i < 10; i++) {
//			stack.push(new Integer(i));
//		}
//		 stack.push("HEJ"); // kompileringsfel
//		while (!stack.isEmpty()) {
//			elem = stack.pop();
//			System.out.print(elem + " ");
		}
	}

