package project;

/**
 * Class used in LinkedList
 *
 * 
 * 
 * @author Grupp3
 * 
 */
public class ListNode<E> {
	private E data;
	private ListNode<E> next;

	/**
	 * Constructor creating a list node with 2 parameters
	 *
	 * 
	 * @param data
	 * @param next
	 */
	public ListNode(E data, ListNode<E> next) {
		this.data = data;
		this.next = next;
	}

	/**
	 * Returns the data
	 * 
	 * @return E data
	 */
	public E getData() {
		return this.data;
	}

	/**
	 * Sets the data
	 * 
	 * 
	 * @param data
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Returns the next list node
	 * 
	 * @return ListNode<E>
	 */
	public ListNode<E> getNext() {
		return this.next;
	}

	/**
	 * Sets next data
	 * @param next
	 */
	public void setNext(ListNode<E> next) {
		this.next = next;
	}

	/**Returns node in a String form
	 * @param String 
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