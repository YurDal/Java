package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 ** Resizable-object implementation of the List interface. An ordered sequence
 * where the user has precise control over where in the list each element is
 * inserted. The user can access elements by their integer index (position in
 * the list), and search for elements in the list.
 * 
 * @author Yurdaer Dalkic
 *
 * @param <E>
 */
public class LinkedList<E> implements List<E>, Iterable<E> {
	private ListNode<E> list = null;

	/**
	 * 
	 * @param index
	 *            index at which the specified element is to be inserted
	 * @return the object the element at the specified position in this list
	 */
	private ListNode<E> locate(int index) {
		ListNode<E> node = list;
		for (int i = 0; i < index; i++)
			node = node.getNext();
		return node;
	}

	/**
	 * Returns the number of element in this list.
	 * 
	 * @return the number of element in this list
	 */
	public int size() {
		int n = 0;
		ListNode<E> node = list;
		while (node != null) {
			node = node.getNext();
			n++;
		}
		return n;
	}

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index
	 *            index of the element to return
	 * @return the element at the specified position in this list
	 */
	public E get(int index) {
		if ((index < 0) || (index >= size()))
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + index);

		ListNode<E> node = locate(index);
		return node.getData();
	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element
	 * 
	 * @param index
	 *            index of the element to replace
	 * @param element
	 *            element to be stored at the specified position
	 * @return the element previously at the specified position
	 */
	public E set(int index, E data) {
		if ((index < 0) || (index >= size()))
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + index);
		ListNode<E> node = locate(index);
		E prevData = node.getData();
		node.setData(data);
		return prevData;
	}

	/**
	 * Appends the specified element to the end of this list
	 * 
	 * @param element
	 *            element to be appended to this list
	 */
	public void add(E data) {
		add(size(), data);
	}

	/**
	 * Appends the specified element to the first position of this list
	 * 
	 * @param element
	 *            element to be appended to this list
	 */
	public void addFirst(E data) {
		add(0, data);
	}

	/**
	 * Appends the specified element to the end of this list
	 * 
	 * @param element
	 *            element to be appended to this list
	 */
	public void addLast(E data) {
		add(data);
	}

	/**
	 * Inserts the specified element at the specified position in this list.
	 * Shifts the element currently at that position (if any) and any subsequent
	 * elements to the right (adds one to their indices).
	 * 
	 * @param index
	 *            index at which the specified element is to be inserted
	 * @param element
	 *            element to be inserted
	 */
	public void add(int index, E data) {
		if ((index < 0) || (index > size()))
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + index);
		if (index == 0)
			list = new ListNode<E>(data, list);
		else {
			ListNode<E> node = locate(index - 1);
			ListNode<E> newNode = new ListNode<E>(data, node.getNext());
			node.setNext(newNode);
		}
	}

	/**
	 * 
	 * @return the element previously at the specified position
	 */

	public E removeFirst() {
		return remove(0);
	}

	/**
	 * 
	 * @return the element previously at the specified position
	 */
	public E removeLast() {
		return remove(size() - 1);
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 * Returns the element that was removed from the list.
	 * 
	 * @param index
	 *            the index of the element to be removed
	 * @return the element previously at the specified position
	 */
	public E remove(int index) {
		if ((index < 0) || (index >= size()))
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + index);

		E res;
		if (index == 0) {
			res = list.getData();
			list = setNull(list);
			// list = list.getNext();
		} else {
			ListNode<E> node = locate(index - 1);
			res = node.getNext().getData();
			node.setNext(setNull(node.getNext()));
			// node.setNext( node.getNext().getNext() );
		}
		return res;
	}

	/**
	 * Replaces null at the an Object
	 * 
	 * @param toNull
	 *            Object to be stored at null
	 * @return object previously at the specified position
	 */
	private ListNode<E> setNull(ListNode<E> toNull) {
		ListNode<E> res = toNull.getNext();
		toNull.setData(null);
		toNull.setNext(null);
		return res;
	}

	/**
	 * Removes all of the elements from this list. The list will be empty after
	 * this call returns.
	 */
	public void clear() {
		// while (list.getNext() != null) {
		// removeFirst();
		// }
		// removeFirst();
		list = null;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element.
	 * 
	 * @param element
	 *            element to search for
	 * @return the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element
	 */
	public int indexOf(E data) {
		return indexOf(0, data);
	}

	/**
	 * 
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element. The search
	 * begins at startIndex in the list.
	 * 
	 * @param startIndex
	 *            the search starts at position startIndex in the list
	 * @param data
	 *            element to search for
	 * @return the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element
	 */
	public int indexOf(int startIndex, E data) {
		if ((startIndex < 0) || (startIndex >= size()))
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + startIndex);
		ListNode<E> node = list;
		if (startIndex > 0) {
			for (int i = 0; i < startIndex; i++) {
				node = node.getNext();
			}
		}
		for (int i = startIndex; i < size(); i++) {
			if (node.getData().equals(data)) {
				return i;
			} else {
				node = node.getNext();
			}
		}

		return -1;
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * 
	 * @return an iterator over the elements in this list in proper sequence
	 */
	public Iterator<E> iterator() {
		ArrayList<E> iterList = new ArrayList<E>();
		iterList.add(list.getData());
		for (int i = 0; i < size(); i++) {
			iterList.add(list.getNext().getData());
		}
		return iterList.iterator();
	}

	/*
	 * Returns a String of elements in this list
	 * 
	 * @return the String of elements in this list
	 */
	public String toString() {
		if (list != null)
			return list.toString();
		else
			return "[]";
	}

	// private class Iter implements Iterator<E> {
	// ArrayList<E> iterList = new ArrayList<E>();

	// private ListNode<E> list2 = list;
	// int index =0;
	//
	// public boolean hasNext() {
	// if (list2.getNext() != null)
	// return true;
	// else {
	// return false;
	// }
	// }
	//
	// public E next() {
	//
	// if (hasNext()) {
	// return list2.getNext().getData();
	// }
	// else if((list2.getNext()==null)&&(list2.getData()!=null)){
	// return list2.getData();
	// }
	// else {
	// throw new NoSuchElementException();
	// }
	// }
	//
	// public void remove() {
	// throw new UnsupportedOperationException();
	// }
	// }

	public static void main(String[] args) {
		// LinkedList<Integer> list = new LinkedList<Integer>();
		// for (int i = 100; i < 111; i++) {
		// list.add(new Integer(i));
		// }
		// System.out.print(list);
		// System.out.print(list.locate(5));
		// System.out.print(list.size());
		// System.out.print(list.get(10));
		// System.out.print(list.set(0, 55));
		// list.add(1, 55);
		// list.addFirst(55);
		// list.addLast(55);
		// list.remove(5);
		// list.removeFirst();
		// list.removeLast();
		// list.clear();
		// for (int i =100; i<111;i++){
		// list.add(i);
		// }
		// System.out.print(list.indexOf(new Integer(109)));
		// System.out.print(list.iterator().next());

	}
}
