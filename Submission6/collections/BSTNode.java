package collections;

/**
 * Klassen representerar en node i bibär träd.Varje node har en nyckel (key) och
 * en värde (value). Varje nod i trädet har exakt två referenser till andra
 * noder, left respektive right .
 * 
 * @author Yurdaer Dalkic
 *
 * @param <K>
 *            key objekt
 * @param <V>
 *            value objekt
 */
class BSTNode<K, V> {
	K key;
	V value;
	BSTNode<K, V> left;
	BSTNode<K, V> right;

	/**
	 * Konstruktor som skapar en BSTNode objekt
	 * 
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */
	public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	/**
	 * Metoden returnerar höjden av trädet.
	 * 
	 * @return höjd.
	 */
	public int height() {
		int leftH = -1, rightH = -1;
		if (left != null)
			leftH = left.height();
		if (right != null)
			rightH = right.height();
		return 1 + Math.max(leftH, rightH);
	}

	/**
	 * Returnerar antalet element i trädet.
	 * @return antal element.
	 */
	public int size() {
		int leftS = 0, rightS = 0;
		if (left != null)
			leftS = left.size();
		if (right != null)
			rightS = right.size();
		return 1 + leftS + rightS;
	}
/**
 * Metoden skriver ut alla element med sin nyckel som lagras i trä.
 */
	public void print() {
		if (left != null)
			left.print();
		System.out.println(key + ": " + value);
		if (right != null)
			right.print();
	}

//	public void showTree() {
//		javax.swing.JOptionPane.showMessageDialog(null, new ShowBST<K, V>(this, 800, 600), "Show tree",
//				javax.swing.JOptionPane.PLAIN_MESSAGE);
//	}
}
