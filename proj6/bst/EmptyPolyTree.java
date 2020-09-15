/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized  
 * assistance on this assignment
 */

package bst;

/*
 * this class structures a Polymorphic BST that is Empty. The generic
 * type K extends Comparable so that we are able to use compareTo on it.
 * Also, the class implements the interface PolyTree so all the methods
 * of the interface must exist here. The methods in this class of the BST
 * must act differently from NonEmpty objects since were performing actions
 * based on the object being at the ends of the entire tree
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class EmptyPolyTree<K extends Comparable<K>, V>
implements PolyTree<K, V> {

	/* 
	 * this class uses a Singleton implementation so all NonEmpty objects
	 * point to one instance of the EmptyPolyTree class
	 */
	private static EmptyPolyTree single = new EmptyPolyTree();

	/* 
	 * must declare an empty private class since java will declare a 
	 * public one for you
	 */
	private EmptyPolyTree() {
	}

	// returns the single instance of this class
	public static EmptyPolyTree getInstance() {
		return single;
	}

	// initializes and returns a new NonemptyPolyTree object when needed
	public NonemptyPolyTree<K, V> addKeyValuePair(K aKey, V aValue) {
		return new NonemptyPolyTree(single, single, aKey, aValue);
	}

	// returns 0 everytime the Empty tree is hit
	public int treeSize() {
		return 0;
	}

	// returns null when not found
	public V lookupValueByKey(K aKey) {
		return null;
	}

	// this exception is thrown the last tree in right subtree is needed
	public K max() throws EmptyPolyTreeException {
		throw new EmptyPolyTreeException();
	}

	// this exception is thrown the last tree in left subtree is needed
	public K min() throws EmptyPolyTreeException {
		throw new EmptyPolyTreeException();  
	}

	// returns -1 to indicate that the tree is traversed until end 
	// and key wasn't found
	public int distanceFromRoot(K aKey) {
		return -1;  
	}

	// returns an instance of this class on a EmptyPolyTree 
	// version of a call on the delete method
	public PolyTree<K, V> deleteKeyValuePair(K aKey) {
		return single;
	}

	// returns an empty string to indicate the end of the traversal
	public String toString() {
		return "";
	}

}
