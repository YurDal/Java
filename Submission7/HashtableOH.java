package project;

import java.util.Iterator;

/**
 * HashtableOH using an open Hashtable implementing the interface Map<K, V>
 * 
 * @author Louay Khalil
 */
public class HashtableOH<K, V> implements Map<K, V> {
	private LinkedList<Entry<K, V>>[] table;
	private int size = 0;

	/**
	 * Creates a Hashtable with a specified size
	 * 
	 * @param size
	 */
	public HashtableOH(int size) {
		table = (LinkedList<Entry<K, V>>[]) new LinkedList[size];
		for (int i = 0; i < size; i++) {
			table[i] = new LinkedList<Entry<K, V>>();
		}
	}

	private int hashIndex(K key) {
		int hashCode = key.hashCode();
		hashCode = hashCode % table.length;
		return (hashCode < 0) ? -hashCode : hashCode;
	}

	
	/**
	* Putting a wanted value in a wanted key
	* increasing size
	*/
	public V put(K key, V value) {
		V res = null;
		int hashIndex = hashIndex(key);
		Entry<K, V> entry = new Entry<K, V>(key, value);
		int index = table[hashIndex].indexOf(entry);
		if (index == -1) {
			table[hashIndex].addFirst(entry);
			size++;
		} else {
			res = table[hashIndex].set(index, entry).value;
			size++;
		}
		return res;
	}

	/**
	* Printing out the contents of the Hashtable
	* 
	*/
	public void list() {
		Entry<K, V> entry;
		for (int i = 0; i < table.length; i++) {
			System.out.print(i + ":");
			for (int j = 0; j < table[i].size(); j++) {
				entry = table[i].get(j);
				System.out.print(" <" + entry.key + "," + entry.value + ">");
			}
			System.out.println();
		}
	}

	
	/**
	* Getting the value of a wanted key
	* if key is not in the tree it will return null
	* @return value
	* 
	*/
	public V get(K key) {
		V res = null;
		int hashIndex = hashIndex(key);
		Entry<K, V> entry = new Entry<K, V>(key, null);
		int index = table[hashIndex].indexOf(entry);
		if (index != -1) {
			res = table[hashIndex].get(index).value;
		}
		return res;
	}

	
	/**
	* Removes the value in a wanted key
	* Returnes the value deleted
	* decreasing size
	* @return value
	*/
	public V remove(K key) {
		V res = null;
		int hashIndex = hashIndex(key);
		Entry<K, V> entry = new Entry<K, V>(key, null);
		int index = table[hashIndex].indexOf(entry);
		if (index != -1) {
			res = table[hashIndex].get(index).value;
			table[hashIndex].remove(index);
			size--;
			return res;
		}
		return null;
	}

	
	/**
	* Getting the size of the tree
	* @return Integer
	*/
	public int size() {
		return size;
	}

	
	
	/**
	* Checking true or false if the Hashtable is empty
	* @return boolean
	*/
	public boolean isEmpty() {
		return (size == 0);
	}

	
	/**
	* Checking false or true of the Hashtable contains a wanted key
	* @return boolean
	*/
	public boolean containsKey(K key) {
		return get(key) != null;
	}

	
	/**
	* Clearing the whole hashtable
	* 
	*/
	public void clear() {
		for (int i = 0; i < table.length; i++) {
			table[i].clear();
		}
		size = 0;
	}

	
	/**
	* Creates an Iterator av the keys in the Hashtable
	* @return Iterator<K>
	*/
	public Iterator<K> keys() {
		ArrayList<K> list = new ArrayList<K>();
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].size(); i++) {
				if (table[i].size() != 0) {
					list.add(table[i].get(j).key);
				}
			}
		}
		return list.iterator();
	}

	
	
	/**
	* Creates an Iterator av the values in the Hashtable
	* @return Iterator<V>
	*/
	public Iterator<V> values() {
		ArrayList<V> list = new ArrayList<V>();
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].size(); i++) {
				if (table[i].size() != 0) {
					list.add(table[i].get(j).value);
				}
			}
		}
		return list.iterator();
	}

}

