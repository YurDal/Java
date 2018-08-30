package project;

import java.util.Iterator;


public interface SearchTree<K, V> {
	/**
	 * Appends the specified element to the end of this tree based on elements
	 * code
	 * 
	 * @param key
	 *            elements code
	 * @param value
	 *            elements
	 */
	public void put(K key, V value);

	/**
	 * Elements which has same key removes from the tree.
	 * 
	 * @param key
	 * @return V
	 */
	public V remove(K key);

	/**
	 * Returns specified element of the tree
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key);

	/**
	 * Checks if tree contains the key
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(K key);

	/**
	 * Calculate trees height and returns it
	 * 
	 * @return
	 */
	public int height();

	/**
	 * Returns an iterator over the elements in the tree in proper sequence.
	 * 
	 * @return an iterator over the elements in the tree in proper sequence
	 */
	public Iterator<V> iterator();

	/**
	 * Returns the number of elements in the tree.
	 * 
	 * @return the number of elements in the tree
	 */
	public int size();

	/**
	 * Returns all keys in the tree in a list
	 * 
	 * @return
	 */
	public List<K> keys();

	/**
	 * Returns all elements in the tree in a list.
	 * 
	 * @return
	 */
	public List<V> values();

	/**
	 * Returns first element in the tree
	 * 
	 * @return
	 */
	public V first();

	/**
	 * Returns last element in the tree
	 * 
	 * @return
	 */
	public V last();
}
