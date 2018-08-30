package project;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* BinarySearchTree<K,V> is an implementation of the interface SearchTree<K,V>
* Using BSTNode<K,V> 
* @author Grupp3
*/
public class BinarySearchTree<K,V> implements SearchTree<K,V> {
	private Comparator<K> comparator;
	private BSTNode<K,V> tree;
	private int size;

	
	
	

	/**
	* First empty constructor
	* 
	* 
	*/
	public BinarySearchTree() {
		comparator = new Comp();
	}

	
	
	
	
	/**
	* Second constructor with a Comparator as a parameter
	* @param comp
	* 
	*/
	public BinarySearchTree( Comparator<K> comp ) {
		comparator = comp;
	}

	
	
	
	
	/**
	* Getting the root of the tree
	* @return BSTNode<K,V>
	* 
	*/
	public BSTNode<K,V> root() {
		return tree;
	}

	
	
	
	
	/**
	* Getting the value of a wanted key
	* if key is not in the tree it will return null
	* @return value
	* 
	*/

	public V get(K key) {
		BSTNode<K,V> node = find( key );
		if(node!=null) {
			return node.value;
		}
		return null;
	}

	
	
	
	
	/**
	* Putting a wanted value in a wanted key
	* increasing size
	*/
	public void put(K key, V value) {
		tree = put(tree,key,value);
		size++;
	} 

	
	
	
	
	
	/**
	* Removes the value in a wanted key
	* Returnes the value deleted
	* decreasing size
	* @return value
	*/
	public V remove(K key) {
		V value = get( key );
		if(value!=null) {
			tree = remove(tree,key);
			size--;
		}

		return value;
	}

	
	
	
	
	
	/**
	* Checking false or true of the tree contains a wanted key
	* @return boolean
	*/
	public boolean contains( K key ) {
		return find( key ) != null;
	}

	
	
	
	
	
	/**
	* Checking the hight of the tree
	* @return Integer
	*/
	public int height() {
		return height( tree );
	}

	
	
	
	
	
	/**
	* Creating an Iterator of V
	* @return Iterator
	*/
	public Iterator<V> iterator() {
		return new Iter();
	}

	
	
	
	
	private BSTNode<K,V> find(K key) {
		int res;
		BSTNode<K,V> node=tree;
		while( ( node != null ) && ( ( res = comparator.compare( key, node.key ) ) != 0 ) ) {
			if( res < 0 )
				node = node.left;
			else
				node = node.right;
		}
		return node;
	}

	
	
	private BSTNode<K,V> put(BSTNode<K,V> node, K key, V value) {
		if( node == null ) {
			node = new BSTNode<K,V>( key, value, null, null );
		} else {
			if(comparator.compare(key,node.key)<0) {
				node.left = put(node.left,key,value);
				node = balanceLeft(node);
			} else if(comparator.compare(key,node.key)>0) {
				node.right = put(node.right,key,value);
				node = balanceRight(node);
			}
		}
		return node;
	}

	
	
	
	
	private BSTNode<K,V> remove(BSTNode<K,V> node, K key) {
		int compare = comparator.compare(key,node.key);
		if(compare==0) {
			if(node.left==null && node.right==null)
				node = null;
			else if(node.left!=null && node.right==null)
				node = node.left;
			else if(node.left==null && node.right!=null)
				node = node.right;
			else {
				BSTNode<K,V> min = getMin(node.right);
				min.right = remove(node.right,min.key);
				min.left = node.left;
				node = min;
				node = balanceRight(node);
				
			}
		} else if(compare<0) {
			node.left = remove(node.left,key);
			node = balanceLeft(node);
		} else {
			node.right = remove(node.right,key);
			node = balanceRight(node);
		}
		return node;
	}

	
	
	
	
	private BSTNode<K,V> getMin(BSTNode<K,V> node) {
		while(node.left!=null)
			node = node.left;
		return node;
	}


	

	private int height( BSTNode<K,V> node ) {
		if( node == null ) {
			return -1;
		}
		return 1 + Math.max( height( node.left ), height( node.right ));
	}


	
	
	
	
	/**
	* Returnes the amount of elements inserted in the tree
	* @return Integer
	*/
	public int size1() {
		if(tree==null) {
			return 0;
		}
		return tree.size();
	}

	
	
	
	

	/**
	* Returnes the amount of elements inserted in the tree
	* @return Integer
	*/
	public int size2() {
		return size2(tree);
	}
	public int size2( BSTNode<K,V> tree ) {
		if ( tree == null ) {
			return 0;
		}
		return 1 + size2(tree.left) + size2(tree.right);
	}

	
	
	
	
	
	/**
	* Rotating the tree to the right
	* @return BSTNode<K,V>
	*/
	public <K,V> BSTNode<K,V> rotateRight(BSTNode<K,V> node) {
		BSTNode<K,V> rootNode = node.left;
		node.left = rootNode.right;
		rootNode.right = node;
		return rootNode;

	}

	
	
	
	
	
	
	/**
	* Rotating the tree to the left
	* @return BSTNode<K,V>
	*/
	public <K,V> BSTNode<K,V> rotateLeft(BSTNode<K,V> node) {
		BSTNode<K,V> rootNode = node.right;
		node.right = rootNode.left;
		rootNode.left = node;
		return rootNode;
	}

	
	
	
	

	/**
	* Balancing out the tree to the left
	* @return BSTNode<K,V>
	*/
	public  BSTNode<K,V> balanceLeft(BSTNode<K,V> node) {
		if( height( node.left) - height(node.right) == 2 ) {
			if( height(node.left.left) - height(node.left.right) == -1 ) {
				node.left = rotateLeft( node.left );
				node = rotateRight(node);
			}
			else {
				node = rotateRight(node);
			}
		}
		return node;
	}

	
	
	
	
	
	/**
	* Balancing out the tree to the right
	* @return BSTNode<K,V>
	*/
	public BSTNode<K,V> balanceRight(BSTNode<K,V> node) {
		if( height(node.right) - height(node.left) == 2 ) {
			if( height(node.right.right) - height(node.right.left) == -1 ) {
				node.right = rotateRight( node.right );
				node = rotateLeft(node);
			}
			else {
				node = rotateLeft(node);
			}
		}
		return node;
	}

	
	
	
	

	/**
	* Getting the size of the tree
	* @return Integer
	*/
	public int size() {
		return size;
	}

	
	
	
	
	
	/**
	* Returns all the keys in the tree in the form of a list
	* @return ArrayList<K>
	*/
	public List<K> keys(){
		ArrayList<K> list = new ArrayList<K>();
		keys(tree, list);
		return list;
	}
	
	
	
	
	
	private void keys(BSTNode<K,V> node, ArrayList<K> list){
		if (node != null) {
			list.add( node.key );
			if(node.left != null) {
				keys(node.left, list);
			}
			if(node.right != null) {
				keys(node.right, list);
			}
		}

	}

	
	
	
	
	
	
	/**
	* Returns all the values in the tree in the form of a list
	* @return ArrayList<V>
	*/
	public List<V> values(){
		ArrayList<V> list = new ArrayList<V>();
		values(tree, list);
		return list;
	}

	
	
	
	
	
	private void values(BSTNode<K,V> node, ArrayList<V> list){
		if (node != null) {
			list.add( node.value );
			if(node.left != null) {
				values(node.left, list);
			}
			if(node.right != null) {
				values(node.right, list);
			}
		}
	}



	
	
	
	

	/**
	* Returns the value of the first element in the tree
	* @return value
	*/
	public V first(){
		BSTNode<K,V> node = tree;

		if (node == null) {
			return null;
		}

		while (node.left != null) {
			node = node.left;
		}
		return node.value;
	}


	
	
	
	
	

	/**
	* Returns the value of the last element in the tree
	* @return value
	*/
	public V last(){
		BSTNode<K,V> node = tree;

		if (node == null) {
			return null;
		}

		while (node.right != null ) {
			node = node.right;
		}

		return node.value;
	}

	
	
	
	
	
	/**
	* Printing out the content of the tree
	* 
	*/
	public void print() {
		print(tree);
	}

	
	
	private void print(BSTNode<K,V> node) {
		if(node != null) {
			print(node.left);
			System.out.println( node.key + ", " + node.value );
			print(node.right);
		}
	}
	
	
	
	
	
	

	/**
	* Printing out the content of the tree in preorder
	* 
	*/
	public void printPreorder() {
		printPreorder( tree );
	}
	
	
	
	private void printPreorder(BSTNode<K,V> node ) {
		if( node!= null ) {
			System.out.println( node.key + ", " + node.value );
			printPreorder( node.left );
			printPreorder( node.right );
		}
	}
	
	
	

	private class Comp implements Comparator<K> {
		public int compare( K key1, K key2 ) {
			Comparable<K> k1 = ( Comparable<K> )key1;
			return k1.compareTo( key2 );
		}
	}
	
	
	

	private class Iter implements Iterator<V> {
		ArrayList<V> list = new ArrayList<V>();
		int index = -1;

		public Iter() {
			inOrder(tree);
		}

		private void inOrder(BSTNode<K,V> node) {
			if(node!=null) {
				inOrder(node.left);
				list.add(node.value);
				inOrder(node.right);
			}
		}

		public boolean hasNext() {
			return index<list.size()-1;
		}

		public V next() {
			if(!hasNext())
				throw new NoSuchElementException();
			index++;
			return list.get(index);
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}


}
