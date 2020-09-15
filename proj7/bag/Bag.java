/*
 * Seth Amballa
 * samballa
 * 115884939
 * 0206
 * I pledge on my honor that I have not given or received any unauthorized  
 * assistance on this assignment
 */

package bag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Bag {

	private HashMap<Integer, Integer> map = new HashMap<>();

	public void add(int elt) {

		if (map.containsKey(elt)) {
			int val = map.get(elt);
			map.put(elt, ++val);
		} else {
			map.put(elt, 1);
		}
	}

	public boolean contains(int elt) {
		return map.containsKey(elt);
	}

	public int getCount(int elt) {
		if (map.get(elt) == null) {
			return 0;
		}

		return map.get(elt);

	}

	public int size() {
		int size = 0;

		for (Integer elt : map.keySet()) {
			size += map.get(elt);
		}

		return size;

	}

	public Set<Integer> uniqueElements() {
		Set<Integer> elts = new HashSet<Integer>();

		for (Integer elt : map.keySet()) {
			elts.add(elt);
		}
		return elts;
	}

	public void remove(int elt) {

		if (this.contains(elt)) {

			if (map.get(elt) > 1) {

				int val = map.get(elt);
				map.put(elt, --val);

			} else if (map.get(elt) == 1)
				map.remove(elt);
		}
	}

}
