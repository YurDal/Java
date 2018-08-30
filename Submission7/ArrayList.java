package project;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Detta är en klass som gör det möjligt att spara in objekt av vad man vill och
 * sedan lägga det i en lista som man kan göra hur stor man vill och även håller
 * koll på det och implementerar interfaceet List för att veta vilka metoder som
 * ska finnas i klassen
 * 
 * @author Grupp3
 *
 * @param <E>
 *            vilken typ av objekt som listan kommer att inehålla
 */
public class ArrayList<E> implements List<E> {
	private E[] elements;
	private int size;




	/**
	 * Metoden kommer att förstora listan så att den blir dubbelt så stor
	 */
	private void grow() {
		E[] temp = ((E[]) new Object[elements.length * 2]);
		for (int i = 0; i < elements.length; i++) {
			temp[i] = elements[i];
		}
		elements = temp;

	}




	/**
	 * Sätter standard storleken för hur stor listan ska vara
	 */
	public ArrayList() {
		this(20);
	}





	/**
	 * Skapar ett objekt av listan och sätter hur stor den ska vara till en
	 * början
	 * 
	 * @param initialCapacity
	 *            storleken på listan
	 */
	public ArrayList(int initialCapacity) {
		initialCapacity = Math.max(1, initialCapacity);
		elements = (E[]) new Object[initialCapacity];
	}





	/**
	 * Metoden kommer att lägga till ett element i listan och kontrollera så att
	 * man kan lägga in det, genom en check för kontroll av var i listan man
	 * vill lägga till ett värde och om det är lika stort som listan kommer
	 * listan att förstora sig genom metoden grow
	 * 
	 * @param element
	 *            värdet som ska in i listan
	 */
	public void add(int index, E element) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		if (size == elements.length)
			grow();
		for (int i = size; i > index; i--) {
			elements[i] = elements[i - 1];
		}
		elements[index] = element;
		size++;
	}





	/**
	 * Metoden kommer att addera ett tal till genom att kalla på den andra add
	 * metoden, och skicka in size som argument för att det är den första lediga
	 * positionen
	 * 
	 * @param value
	 *            värdet som ska in i listan
	 */
	public void add(E value) {
		add(size, value);

	}





	/**
	 * Metodenen kommer att lägga till elementet på den första positionen genom
	 * att kalla på add metoden
	 * 
	 * @param element
	 *            värdet som ska in i listan
	 */
	public void addFirst(E element) {
		// Laboration, uppgift 1b
		add(0, element);
	}





	/**
	 * Metoden kommer att lägga till ett värde sist i listan genom att kalla på
	 * add funktionen
	 * 
	 * @param element
	 *            värdet som ska in i listan
	 */
	public void addLast(E element) {
		// Laboration, uppgift 1b
		add(size, element);
	}





	/**
	 * Metoden kommer att ta bort ett värde på en vald plats genom index, det
	 * värdet vi skickar in. Metoden kommer även att retunera det bortagna
	 * värdet så att man kan få se vad det var man tog bort
	 * 
	 * @param index
	 *            vilket plats i listan som ska bort
	 * @return retunerar det gamla värdet
	 */
	public E remove(int index) {
		// Laboration, uppgift 1c
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		E element;
		element = elements[index];
		// elements[index] = null;
		size--;
		for (int i = index; i < size; i++) {
			elements[i] = elements[i + 1];
		}
		return element;
	}





	/**
	 * Metoden kommer att ta bort det första värdet i listan
	 * 
	 * @return retunerar det gamla värdet
	 */
	public E removeFirst() {
		// Laboration, uppgift 1d
		E element = remove(0);
		return element;
	}





	/**
	 * Metoden kommer att ta bort det sista värdet i listan
	 * 
	 * @return retunerar det gamla värdet
	 */
	public E removeLast() {
		// Laboration, uppgift 1d
		return remove(size - 1);
	}




	/**
	 * Metoden kommer att ränsa listan och sätta alla gamla värden till nulll
	 */
	public void clear() {
		// Laboration, uppgift 1g
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}





	/**
	 * Metoden kommer att retunera ett angivet värde som finns i listan och
	 * lämnar ett felmedelande om det inmatade talet inte finns i listan
	 * 
	 * @param index
	 *            inmatade talet
	 * @return värdet vi angiver i listan
	 */
	public E get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		return elements[index];
	}





	/**
	 * Metoden kommer att sätta ett värde i listan till ett nytt värde och sedan
	 * retunera det gamla värdet
	 * 
	 * @param index
	 *            var i listan vi vill sätta det nya värdet
	 * @param element
	 *            objecktet som vi vill ersätta med
	 * @return det gamla objektet som har blivit ersatt
	 */
	public E set(int index, E element) {
		// Laboration, uppgift 1f
		if (index > size && index < 0) {
			throw new IndexOutOfBoundsException();
		}
		E oldElement = elements[index];
		elements[index] = element;
		return oldElement;
	}






	/**
	 * Metoden kommer att retunera om det finns ett angivet värde i listan
	 * annars kommer det att retuneras -1
	 * 
	 * @param element
	 *            objektet vi letar efter
	 * @return vilken plats objektet har eller -1
	 */
	public int indexOf(E element) {
		return indexOf(0, element);
	}






	/**
	 * Metoden kommer att retunera om det finns ett angivet värde i listan
	 * annars kommer det att retuneras -1
	 * 
	 * @param element
	 *            objektet vi letar efter
	 * @param startIndex
	 *            ett tal där vi börjar vår sökning på
	 * 
	 * @return objektets position eller -1
	 * 
	 */
	public int indexOf(int startIndex, E element) {
		if (startIndex > size || startIndex < 0) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = startIndex; i < size; i++) {
			if (elements[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}






	/**
	 * Metoden retunerar storleken på listan
	 * 
	 * @return listans storlek
	 */
	public int size() {
		return size;
	}





	/**
	 * Metoden kommer att retunera listan i en String så att man lätt kan få en
	 * utskrift av hur listan ser ut
	 * 
	 * @return String av listan
	 */
	public String toString() {
		StringBuilder res = new StringBuilder("[ ");
		for (int i = 0; i < size; i++) {
			res.append(elements[i]);
			if (i < size - 1)
				res.append("; ");
		}
		res.append(" ]");
		return res.toString();
	}






	/**
	 * Metoden kommer att kontrollera om listan har en nästa, vad det nästa
	 * elementet är och ta bort ett element
	 * 
	 * @return en ny Iterator
	 */
	public Iterator<E> iterator() {

		return new Iterator<E>() {
			private int index = 0;


			/**
			 * Metoden kommer att retunera om den har en nästa eller inte
			 * 
			 * @return true att det inte finns fler värden, false om det finns
			 *         fler
			 */
			public boolean hasNext() {
				return index < size;
			}


			/**
			 * Metoden kommer att retunera det nästa värdet i listan
			 * 
			 * @return nästa element i listan
			 */
			public E next() {
				if (index == size)
					throw new NoSuchElementException();
				return elements[index++];
			}


			/**
			 * Metoden kommer att ta bort det senate värdet i listan
			 * 
			 */
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}






	/**
	 * En inre klass för att kunna kolla om det finns en nästa och vad det nästa
	 * värdet är och ta bort ett värde
	 * 
	 * @author jonatan
	 *
	 */
	private class Iter implements Iterator<E> {
		private int index = 0;


		/**
		 * Metoden kommer att retunera om den har en nästa eller inte
		 * 
		 * @return true att det inte finns fler värden, false om det finns fler
		 */
		public boolean hasNext() {
			return index < size;
		}


		/**
		 * Metoden kommer att retunera det nästa värdet i listan
		 * 
		 * @return nästa element i listan
		 */
		public E next() {
			if (index == size)
				throw new NoSuchElementException();
			return elements[index++];
		}


		/**
		 * Metoden kommer att ta bort det senate värdet i listan
		 * 
		 */
		public void remove() {
			for (int i = 0; i < elements.length; i++) {
				elements[i] = null;
			}
		}
	}

}
