package collections;

import java.util.Comparator;

import collections.Queue;

/**
 * I en prioritetskö ordnas elementen efter deras prioritet. Element med samma
 * prioritet lagras enligt kö-principen.
 * 
 * @author Yurdaer Dalkic
 *
 * @param <E>
 */
public class PriorityQueue<E> implements Queue<E> {
	private ArrayHeap<PriorityQueueElement<E>> heap;
/**
 * Konstruktor som skapar en PriorityQueue obj med kapacitenen 20.
 */
	public PriorityQueue() {
		this(20);
	}
/**
 * Konstruktor som skapar PriorityQueue ob med antal kapacitet.
 * @param initialCapacity är kapaciteten av obj.
 */
	public PriorityQueue(int initialCapacity) {
		heap = new ArrayHeap<PriorityQueueElement<E>>(initialCapacity);
	}
/**
 * Konstruktor som skapar PriorityQueue, tar emot två parameter. initialCpacity för kapaciteten och comparator för prioritet.  
 * @param initialCapacity
 * @param comparator
 */
	public PriorityQueue(int initialCapacity, Comparator<E> comparator) {
		heap = new ArrayHeap<PriorityQueueElement<E>>(initialCapacity, new PriorityQueueComparator<E>(comparator));
	}

	// Lägg till ett PriorityQueueElement i heapen
	public void enqueue(E data) {
		heap.insert(new PriorityQueueElement<E>(data));
	}

	// Returnera elementet som lagras i PriorityQueueElement-objektet. Anropa
	// delete-metoden i ArrayHeap.
	public E dequeue() {
		 return heap.delete().getElement(); 
	}

	// Returnera elementet som lagras i PriorityQueueElement-objektet. Anropa
	// peek-metoden i ArrayHeap.
	public E peek() {
		return heap.peek().getElement();
	}
/**
 * Returnerar true om PriorityQueue obj är tomt annars returnerar false.
 */
	public boolean isEmpty() {
		return size() == 0;
	}
/**
 * Returnerar antalat av element som lagras i PriorityQueue obj.
 */
	public int size() {
		return heap.size();
	}
}

class PriorityQueueElement<E> implements Comparable<PriorityQueueElement<E>> {
	private static int counter = 1;
	private E element;
	private int order;

	public PriorityQueueElement(E element) {
		this.element = element;
		this.order = counter++;
	}

	public E getElement() {
		return element;
	}

	public int getOrder() {
		return order;
	}

	public int compareTo(PriorityQueueElement<E> pqElement) {
		int res = ((Comparable<E>) element).compareTo(pqElement.element);
		if (res == 0)
			res = order - pqElement.order;
		return res;
	}
}

class PriorityQueueComparator<E> implements Comparator<PriorityQueueElement<E>> {
	private Comparator<E> comp;

	public PriorityQueueComparator(Comparator<E> comparator) {
		comp = comparator;
	}

	public int compare(PriorityQueueElement<E> pqElement1, PriorityQueueElement<E> pqElement2) {
		int res = comp.compare(pqElement1.getElement(), pqElement2.getElement());
		if (res == 0)
			res = pqElement1.getOrder() - pqElement2.getOrder();
		return res;
	}
}
