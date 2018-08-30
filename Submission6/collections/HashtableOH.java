package collections;

import java.util.*;



/**
 * Hashtabellen använder öppen hashing
 * 
 * @author Yurdaer Dalkic
 */
public class HashtableOH<K, V> implements Map<K, V> {
	private LinkedList<Entry<K, V>>[] table;
	private int size;

	/** Creates a new instance of HashtableOH */
	public HashtableOH(int size) {
		table = (LinkedList<Entry<K, V>>[]) new LinkedList[size];
		for (int i = 0; i < size; i++) {
			table[i] = new LinkedList<Entry<K, V>>();
		}
	}
/**
 * Returnerar positionen för key värde
 * @param key
 * @return  index av valur
 */
	private int hashIndex(K key) {
		int hashCode = key.hashCode();
		hashCode = hashCode % table.length;
		return (hashCode < 0) ? -hashCode : hashCode;
	}
/**
 * Lägger till element i tabellen
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
		}
		return res;
	}

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
 * Returnerar elementen eligt given key värde
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
 * Tar boer elementen från listan
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
 * Returnerar  storleken på JFrame
 */
	public int size() {
		return size;
	}
/**
 * Kontrollerar om finns flera element i tabellen
 */
	public boolean isEmpty() {
		return size == 0;
	}
/**
 * Kontrollerar  om finns en element sim har sama n                                                                          
 */
	public boolean containsKey(K key) {
		return get(key) != null;
	}
/**
 * Tömmer tabellen
 */
	public void clear() {
		for (int i = 0; i < table.length; i++) {
			table[i].clear();
		}
		size = 0;
	}

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

	public static void main(String[] args) {
		HashtableOH<String, String> table = new HashtableOH<String, String>(4);
		table.put("hej", "hello");
		table.put("röd", "red");
		table.put("vit", "white");
		table.put("säng", "bed");
		table.put("svart", "black");
		table.put("gul", "yellow");
		table.put("dator", "computer");
		table.put("snö", "snow");
		table.put("blå", "blue");
		table.put("grön", "green");
		table.put("hus", "house");
		table.list();
		System.out.println(table.get("gul"));
		System.out.println(table.get("nej"));
		table.remove("snö");
		table.list();
		System.out.println(table.size());
		System.out.println(table.isEmpty());
		System.out.println(table.containsKey("vit"));
		System.out.println(table.containsKey("yasak"));
		table.clear();
		System.out.println(table.size());
		System.out.println(table.isEmpty());







	}
}
