package collections;

/**
 * ArrayQueue represents a linked implementation of a array
 * 
 * @author yurdaer
 *
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {
	private E[] elements;
	private int size = 0;

	/**
	 * Konstrukt creats a ArrayQueue with a speficied capacity
	 * 
	 * @param capacity
	 */
	public ArrayQueue(int capacity) {
		elements = (E[]) new Object[capacity];
	}

	/**
	 * Inserts the specified element into this queue.
	 * 
	 * @param data
	 *            the object to add
	 * @throws QueueException
	 *             if the element cannot be added at this time due to capacity
	 *             restrictions
	 */
	public void enqueue(E elem) {
		if (size == elements.length) {
			throw new QueueException("enqueue: Queue is full");
		}
		elements[size++] = elem;
	}

	/**
	 * Retrieves and removes the head of this queue.
	 * 
	 * @return the head of this queue
	 * @throws QueueException
	 *             if this queue is empty
	 */
	public E dequeue() {
		if (size == 0) {
			throw new QueueException("dequeue: Queue is empty");
		}
		E value = elements[0];
		elements[0]=null;
		size--;
		for (int i = 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}
		elements[size] = null;
		return value;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue.
	 * 
	 * @return the head of this queue
	 * @throws QueueException
	 *             if this queue is empty
	 */
	public E peek() {
		if (size == 0) {
			throw new QueueException("peek: Queue is empty");
		}
		return elements[0];
	}

	/**
	 * Returns true if this queue contains no elements.
	 * 
	 * @return true if this queue contains no elements
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Returns the number of elements in this queue.
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}
}
