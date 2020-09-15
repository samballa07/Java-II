package bst;

// (c) Larry Herman, 2020.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

public interface PolyTree<K extends Comparable<K>, V> {

  public NonemptyPolyTree<K, V> addKeyValuePair(K aKey, V aValue);
  public int treeSize();
  public V lookupValueByKey(K aKey);
  public K max() throws EmptyPolyTreeException;
  public K min() throws EmptyPolyTreeException;
  public int distanceFromRoot(K aKey);
  public PolyTree<K, V> deleteKeyValuePair(K aKey);

}
