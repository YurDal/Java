package project;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;






/**
 * Denna klassen kommer att bestå av en länkad lista som implementerar
 * interfaceet List och dess metoder,
 * 
 * @author 
 *
 * @param <E>
 */
public class LinkedList<E> implements List<E> {
	private ListNode<E> list = null;

	private ListNode<E> locate(int index) {
		ListNode<E> node = list;
		for (int i = 0; i < index; i++)
			node = node.getNext();
		return node;
	}





	/**
	 * Metoden kommer att retunera hur stor den länkade listan är
	 * 
	 * @return storleken på listan
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
	 * Metoden kommer att retunera ett objekt på den platsen i listan man anger
	 * och skriva ut felmedelande om index inte finnns i listan
	 * 
	 * @param index
	 *            platsen på det värdet man vill ha
	 * @return värdet på objektet som finns på den angivna platsen i listan
	 */
	public E get(int index) {
		if ((index < 0) || (index > size()))
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + index);

		ListNode<E> node = locate(index);
		return node.getData();
	}






	/**
	 * Metoden kommer att sätta ett nytt värde på ett angivet obejkt i listan,
	 * genom att vi skicka in ett index
	 * 
	 * @param index
	 *            vilken position som vi vill byta värde
	 * @param data
	 *            det vi vill att objektet ska innehålla
	 * @return E set
	 */
	public E set(int index, E data) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		ListNode<E> node = locate(index);
		E res = node.getData();
		node.setData(data);
		return res;
	}







	/**
	 * Metoden kommer att addera ett objekt i listan
	 * 
	 * @param objektet
	 *            som vi vill lägga till
	 */
	public void add(E data) {
		addLast(data);
	}





	/**
	 * Metoden kommer att lägga till ett objekt på den första positionen i
	 * listan
	 * 
	 * @param objeketet
	 *            som vi vill lägga till
	 */
	public void addFirst(E data) {
		add(0, data);
	}






	/**
	 * Metoden kommer att lägga till ett objekt på den sista positionen i listan
	 * 
	 * @param objeketet
	 *            som vi vill lägga till
	 */
	public void addLast(E data) {
		add(size(), data);
	}






	/**
	 * Metoden kommer att lägga till ett objekt i listan på en angiven position
	 * 
	 * @param index
	 *            positionen som värdet ska läggas in i
	 * @param data
	 *            objektets informatiom
	 *
	 */
	public void add(int index, E data) {
		if ((index < 0) || (index > size()))
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + index);

		if (index == 0) {
			list = new ListNode<E>(data, list);
		} else {
			ListNode<E> node = list;
			node = locate(index - 1);
			ListNode<E> newNode = new ListNode<E>(data, node.getNext());
			node.setNext(newNode);
		}
	}







	/**
	 * Metoden kommer att ta bort det först värdet i listan
	 */
	public E removeFirst() {
		return remove(0);
	}






	/**
	 * Metoden kommer att ta bort det sista värdet i listan
	 */
	public E removeLast() {
		return remove(size() - 1);
	}






	/**
	 * Metoden kommer att to bort ett objekt i listan på en angiven position
	 * 
	 * @param index
	 *            den angivna positionen
	 */
	public E remove(int index) {
		if ((index < 0) || (index > size()))
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + index);

		E res;
		if (index == 0) {
			res = list.getData();
			list = setNull(list);
		} else {
			ListNode<E> node = locate(index - 1);
			res = node.getNext().getData();
			node.setNext(setNull(node.getNext()));
		}
		return res;
	}






	/**
	 * Metoden kommer att sätta ett objekt till null så att det blir borttaget
	 * 
	 * @param toNull
	 *            objektet som ska bli null
	 * @return retunerar det gamla värdet som är borttaget
	 */
	private ListNode<E> setNull(ListNode<E> toNull) {
		ListNode<E> res = toNull.getNext();
		toNull.setData(null);
		toNull.setNext(null);
		return res;
	}






	/**
	 * Metoden kommer att rensa listan så att det inte finns några värden i den
	 */
	public void clear() {
		for (int i = size(); i > 0; i--) {
			removeLast();
		}
	}





	/**
	 * Metoden kommer att leta igenom listan efter ett angivan värdet som vi
	 * skickar in om det finns reutneras platsen annars -1
	 * 
	 * @param data
	 *            objektets värde som ska sökas efter
	 * @return positionen eller -1
	 */
	public int indexOf(E data) {
		return indexOf(0, data);
	}





	/**
	 * Metoden kommer att leta igenom listan efter ett angivan värdet som vi
	 * skickar in om det finns reutneras platsen annars -1
	 * 
	 * @param data
	 *            objektets värde som ska sökas efter
	 * @param startIndex
	 *            var i listan sökningen ska börja
	 * @return positionen eller -1
	 */
	public int indexOf(int startIndex, E data) {
		if ((startIndex < 0) || (startIndex > size()))
			throw new IndexOutOfBoundsException("size=" + size() + ", index=" + startIndex);

		for (int i = startIndex; i < size(); i++) {
			if (locate(i).getData().equals(data)) {
				return i;
			}
		}
		return -1;
	}






	/**
	 * Metoden kommer att retunera en ny iter
	 */
	public Iterator<E> iterator() {
		return new Iter();
	}






	/**
	 * Metoden kommer att skriva in listan i en string och sedan retunera
	 * värdena som finns i listan
	 * 
	 * @return en string med värdena i listan
	 */
	public String toString() {
		if (list != null)
			return list.toString();
		else
			return "[]";
	}






	/**
	 * En inre klass för att kontrollera om listan har en nästa, vad det nästa
	 * är och en remove
	 * 
	 * @author Jonatan Fridsten
	 *
	 */
	private class Iter implements Iterator<E> {
		private int index;



		/**
		 * Metoden kommer att retunera om listan har en nästa
		 * 
		 * @return boolean true om den har en nästa och false om det inte finns
		 *         ngt mer
		 */
		public boolean hasNext() {
			return index < size();
		}



		/**
		 * Metoden kommer att retunera det nästa värdet i listan
		 * 
		 * @return nästa elementet i listan
		 */
		public E next() {
			if (index == size()) {
				throw new NoSuchElementException();
			}
			return locate(index++).getData();
		}


		/**
		 * Metoden kommer att ta bort elemeneten i listan
		 */
		public void remove() {
			if (size() > 0) {
				throw new EmptyStackException();
			}
			for (int i = size(); i > 0; i--) {
				locate(i).setData(null);
			}
			index = 0;
		}
	}
}
