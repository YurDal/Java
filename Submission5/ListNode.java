package collections;

/**
 * 
 * @author yurdaer
 *
 * @param <E>
 */
public class ListNode<E> {
	private E data;
	private ListNode<E> next;

	/**
	 * Constructs an ListNode 
	 * 
	 * @param data
	 * @param next
	 */
	public ListNode(E data, ListNode<E> next) {
		this.data = data;
		this.next = next;
	}

	/**
	 * Returns the element this list.
	 * 
	 * @return
	 */
	public E getData() {
		return this.data;
	}

	/**
	 * Replaces the element at thenext position in this list with the specified
	 * element
	 * 
	 * @param data
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Returns the next object.
	 * 
	 * @return ListNode<E>
	 */
	public ListNode<E> getNext() {
		return this.next;
	}

	/**
	 * Replaces the element next positionen
	 * 
	 * @param next
	 */
	public void setNext(ListNode<E> next) {
		this.next = next;
	}

	/**
	 * Builds a string of ListNode objecten return String
	 */
	public String toString() {
		StringBuilder str = new StringBuilder("[ ");
		str.append(data.toString());
		ListNode<E> node = next;
		while (node != null) {
			str.append("; ");
			str.append(node.getData().toString());
			node = node.getNext();
		}
		str.append(" ]");
		return str.toString();
	}
}