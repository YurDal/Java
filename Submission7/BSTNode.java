package project;









/**
 * Class representing a binary search tree node
 *
 * 
 * 
 * @author Grupp3
 * 
 */
class BSTNode<K,V> {
	K key;
	V value;
	BSTNode<K,V> left;
	BSTNode<K,V> right;




	/**
	 * Connstructor creating a binary search tree node
	 *
	 * 
	 * 
	 * @param K key, V value, BSTNode<K,V> left, BSTNode<K,V> right
	 *
	 */
	public BSTNode( K key, V value, BSTNode<K,V> left, BSTNode<K,V> right ) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}





	/**
	 * Gets the hight of the node
	 * 
	 * @return int
	 * 
	 */
	public int height() {
		int leftH = -1, rightH = -1;
		if( left != null )
			leftH = left.height();
		if( right != null )
			rightH = right.height();
		return 1 + Math.max( leftH, rightH );
	}





	/**
	 * Gets the size of the node
	 * 
	 * @return int
	 * 
	 */
	public int size() {
		int leftS = 0, rightS = 0;
		if( left != null )
			leftS = left.size();
		if( right != null )
			rightS = right.size();
		return 1 + leftS + rightS;
	}





	/**
	 * Prints out on console the keys and their values
	 * 
	 * @return void
	 * 
	 */
	public void print() {
		if( left != null)
			left.print();
		System.out.println(key + ": " + value);
		if( right != null )
			right.print();
	}


}
