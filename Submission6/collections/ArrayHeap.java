package collections;

import java.util.Random;
import java.util.Comparator;

/**
 * Klassen representerar en binär heap som implementeras i en array. Elementer lagras i en arraylist.
 * 
 * @author Yurdaer Dalkic
 *
 * @param <E>
 */
public class ArrayHeap<E> {
	private E[] list;
	private int size;
	private Comparator<E> comp;
/**
 * Konstruktor som skapar ArrayHeap objekt med listen kapacitet 20 och naturliga sortrordningen.
 * @param initialCapacity är kapaciteten av objekten.
 */
	public ArrayHeap(int initialCapacity) {
		initialCapacity = Math.max(initialCapacity, 20);
		list = (E[]) (new Object[initialCapacity]);
		comp = new Comp(); // Den naturliga sorteringsordningen
	}
/**
 * Konstruktor som skapar ArrayHeap tar emot två parameter. initialCapacity är listen längth comparator används för sortordingen.
 * @param initialCapacity
 * @param comparator
 */
	public ArrayHeap(int initialCapacity, Comparator<E> comparator) {
		initialCapacity = Math.max(initialCapacity, 20);
		list = (E[]) (new Object[initialCapacity]);
		comp = comparator; // ordning enligt argumentet comparator
	}
/**
 * Konstruktor som tar emot en array sorterar elementen i listan.
 * @param elements
 */
	public ArrayHeap(E[] elements) {
		this.list = elements;
		size = list.length;
		comp = new Comp();
		heapify();
	}
/**
 * Dubblar kapaciteten av listen som element lagras.
 */
	private void grow() {
		E[] newList = (E[]) (new Object[list.length * 2]);
		System.arraycopy(list, 0, newList, 0, list.length);
		list = newList;
	}
/**
 * Metoden flyttar elementen vänster i arrayen.
 * @param value
 */
	private void siftUp(E value) {
		int position = size;
		int parent = (position - 1) / 2;
		while (position > 0 && comp.compare(value, list[parent]) < 0) {
			list[position] = list[parent];
			position = parent;
			parent = (position - 1) / 2;
		}
		list[position] = value;
	}
/**
 * Metoden flyttar elementen till höger i arrayen.
 * @param parent
 */
	private void siftDown(int parent) {
		E value = list[parent];
		int child = parent * 2 + 1; // first child
		while (child < size) { // at least one child
			if ((child + 1 < size) && (comp.compare(list[child], list[child + 1]) > 0)) {
				child++;
			}
			if (comp.compare(value, list[child]) > 0) {
				list[parent] = list[child];
				parent = child;
				child = parent * 2 + 1;
			} else
				break;
		}
		list[parent] = value;
	}
/**
 * Lägger till en element i listan.
 * @param value
 */
	public void insert(E value) {
		if (size == list.length)
			grow();
		siftUp(value);
		size++;
	}
/**
 * Metoden tar bort sista elementen i arreyen.
 * @return
 */
	public E delete() {
		E value = null;
		if (size > 0) {
			value = list[0];
			list[0] = list[--size];
			siftDown(0);
		}
		return value;
	}
/**
 * Returnerar firsta elementen i arreyen.
 * @return
 */
	public E peek() {
		return (size > 0) ? list[0] : null;
	}
/**
 * Returnerar en int som representerar antalet av elementen som lagras i arreyen.
 * @return
 */
	public int size() {
		return size;
	}
/**
 * Returnerar sista föräder i arreyen.
 */
	public void heapify() {
		int parent = (size - 2) / 2;

	}

	private class Comp implements Comparator<E> {
		public int compare(E elem1, E elem2) {
			return ((Comparable<E>) elem1).compareTo(elem2);
		}
	}

	public static void main(String[] args) {
		Random random = new Random();
		Integer[] values = new Integer[200];
		for (int i = 0; i < values.length; i++)
			values[i] = random.nextInt(900) + 100;
		ArrayHeap<Integer> heap = new ArrayHeap<Integer>(10);
		// ArrayHeap<Integer> heap = new ArrayHeap<Integer>(10, new
		// ReverseComparable<Integer>());
		for (Integer i : values)
			heap.insert(i);
		while (heap.size() > 0) {
			System.out.print(heap.delete() + " ");
		}
		System.out.println();

	}
}
