/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized   
 * assistance on this assignment
 */

package bst;

@SuppressWarnings("unchecked")

/*
 * this class is a Polymorphic BST that is NonEmpty. The generic
 * type K extends Comparable so that we are able to use compareTo on it.
 * Also, the class implements the interface PolyTree so all the methods
 * of the interface must exist here.
 */
public class NonemptyPolyTree<K extends Comparable<K>, V> 
								implements PolyTree<K, V> {

	// field variables
	PolyTree<K, V> left, right;
	K key;
	V value;

	// constructor
	public NonemptyPolyTree(PolyTree<K, V> left, PolyTree<K, V> right, 
							K key, V value) {
		this.left = left;
		this.right = right;
		this.key = key;
		this.value = value;
	}

	// adds a new Non empty tree in the correct place
	public NonemptyPolyTree<K, V> addKeyValuePair(K aKey, V aValue) {
		
		/* 
		 * replaces the original value with one from the parameter 
		 * if keys are the same
		 */
		if (key.compareTo(aKey) == 0) {
			value = aValue;

		} else {
			/* 
			 * moves to the current tree depending of whether 
			 * the key is greater than/less than the current tree obj
			 * through a recursive call on the correct polytree obj
			 */
			if (key.compareTo(aKey) > 0) {
				left = left.addKeyValuePair(aKey, aValue);
			} else {
				right = right.addKeyValuePair(aKey, aValue);
			}
		}
		
		//returns the added polytree
		return this;
	}

	// traverses the list recursively to find the amount of trees in it
	public int treeSize() {
		/* 
		 * calls right and left until the EmptyPolyTree version is 
		 * called and all trees are added together and returned
		 */
		return 1 + right.treeSize() + left.treeSize();
	}

	/* traverses the BST through its binary feature to find
	 * the value associated with the given key
	 */
	public V lookupValueByKey(K aKey) {
		
		// value is returned when the curr objects key matches the 
		// param
		if (key.compareTo(aKey) == 0) {
			return value;

		} else {

			/*
			 * checks whether the key is less than or equal to param
			 * key and recursively calls the corresponding subtree
			 */
			if (key.compareTo(aKey) > 0) {
				return left.lookupValueByKey(aKey);

			} else if (key.compareTo(aKey) < 0) {
				return right.lookupValueByKey(aKey);

			}
		}
		
		// returns null if the key does not exist
		return null;
	}

	// returns the largest key in the tree
	public K max() throws EmptyPolyTreeException {
		
		/* 
		 * traverses to the right subtree recursively until an 
		 * EmptyPolyTree is found which in turn throws an 
		 * exception. The exception is caught here and handled by 
		 * returning the last key 
		 */
		try {
			return right.max();

		} catch (EmptyPolyTreeException e) {
			return key;

		}
	}

	// returns the smallest key in the tree
	public K min() throws EmptyPolyTreeException {
		/* 
		 * traverses to the left subtree recursively until an 
		 * EmptyPolyTree is found which in turn throws an 
		 * exception. The exception is caught here and handled by 
		 * returning the last key 
		 */
		try {
			return left.min();
			
		} catch (EmptyPolyTreeException e) {
			return key;
		}
	}

	/*
	 * returns the integer value of the distance of the key from 
	 * the root this is also called the depth.
	 */
	public int distanceFromRoot(K aKey) {
		
		// returns 0 when the key is found
		if (aKey.compareTo(key) == 0) {
			return 0;

		} else {

			// checks that call value was not -1, meaning the key DNE
			if (left.distanceFromRoot(aKey) == -1 && 
				right.distanceFromRoot(aKey) == -1) {
				return -1;

			/* 
			 * recursively calls the correct subtree depending on
			 * whether the current object key is less than or greater
			 * than the parameter key
			 */
			} else if (key.compareTo(aKey) > 0 && 
						left.distanceFromRoot(aKey) != -1) {
				// increments by 1 for each call
				return 1 + left.distanceFromRoot(aKey);

			} else {
				return 1 + right.distanceFromRoot(aKey);
			}
		}
	}

	/* 
	 * traverses the tree to find where the key is in order to 
	 * delete its entire object
	 */
	public PolyTree<K, V> deleteKeyValuePair(K aKey) {

		/*
		 * when the parameter key is smaller than current object key,
		 * a recursive call is made to the right subtree
		 */
		if (key.compareTo(aKey) < 0) {
			right = right.deleteKeyValuePair(aKey);

		/*
		* when the parameter key is greater than the current object key,
		* a recursive call is made to the left subtree
		*/
		} else if (key.compareTo(aKey) > 0) {
			left = left.deleteKeyValuePair(aKey);

		} else {

			/*
			 * if the key is found, the current object must be deleted.
			 * A try-catch block is used to handle the exception in min
			 *  and max methods
			 */
			try {
				
				/* 
				 * key and value variables are replaces and a recursive 
				 * call is done to delete the existing key
				 */
				key = left.max();
				value = left.lookupValueByKey(left.max());
				left = left.deleteKeyValuePair(left.max());

			// exception is caught if there is no left subtree
			} catch (EmptyPolyTreeException e) {

				// the min key in the right subtree is then found
				try {
					key = right.min();
					value = right.lookupValueByKey(right.min());
					right = right.deleteKeyValuePair(right.min());

				// runs when both try blocks throw an exception.
				} catch (EmptyPolyTreeException e1) {
					
					// an EmptyPolyTree is returned since we 
					// are deleting a leaf
					return EmptyPolyTree.getInstance();
				}
			}
		}

		return this; //returns the new tree in that place
	}

	// returns all the key and value pairs as one long string in order
	public String toString() {

		String str, leftStr, rightStr;

		leftStr = left.toString();
		str = key + "+" + value + " ";
		rightStr = right.toString();

		return leftStr + str + rightStr;
	}

}
