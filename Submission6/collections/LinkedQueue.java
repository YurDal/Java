package collections;

import collections.Queue;
import collections.QueueException;
import collections1.LinkedList;

/**
 * LinkedQueue represents a linked implementation of a queue
 * 
 * @author Yurdaer Dalkic
 *
 * @param <E>
 */
public class LinkedQueue<E> implements Queue<E> {

	private LinkedList<E> list = new LinkedList<E>();

	/**
	 * Inserts the specified element into this queue.
	 * 
	 * @param data
	 *            the object to add
	 * @throws QueueException
	 *             if the element cannot be added at this time due to capacity
	 *             restrictions
	 */
	public void enqueue(E data) {
		list.add(data);

	}

	/**
	 * Retrieves and removes the head of this queue.
	 * 
	 * @return the head of this queue
	 * @throws QueueException
	 *             if this queue is empty
	 */
	public E dequeue() {
		if (isEmpty())
			throw new QueueException("dequeue: Queue is empty");
		else
			return list.remove(0);
	}

	/**
	 * Retrieves, but does not remove, the head of this queue.
	 * 
	 * @return the head of this queue
	 * @throws QueueException
	 *             if this queue is empty
	 */
	public E peek() {
		if (isEmpty())
			throw new QueueException("dequeue: Queue is empty");

		return list.get(0);
	}

	/**
	 * Returns true if this queue contains no elements.
	 * 
	 * @return true if this queue contains no elements
	 */
	public boolean isEmpty() {
		return list.size() == 0;
	}

	/**
	 * Returns the number of elements in this queue.
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {

		return list.size();
	}

}
