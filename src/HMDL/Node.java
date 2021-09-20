package HMDL;

public class Node<K, V>
{
    private Node<K, V> forward = null, backward = null;
    private K key;
    private V value;

    /**
     * Constructor creates a new Node and sets its key and value attributes.
     * @param k The input for the Node's kay attribute.
     * @param v The input for the Node's value attribute.
     */
    public Node(K k, V v)
    {
        key = k;
        value = v;
    }

    /**
     * setValue Sets the Node's value attribute.
     * @param v The input for the Node's value attribute.
     */
    public void setValue(V v)
    {
        value = v;
    }

    /**
     * setKay Sets the Node's key attribute.
     * @param k The input for the Node's key attribute.
     */
    public void setKey(K k)
    {
        key = k;
    }

    /**
     * getValue Returns the Node's value attribute.
     * @return The Node's value attribute.
     */
    public V getValue()
    {
        return value;
    }

    /**
     * getKey Returns the Node's key attribute.
     * @return The Node's key attribute.
     */
    public K getKey()
    {
        return key;
    }

    /**
     * setForward Sets the Node's forward link.
     * @param n The input for the Node's forward link.
     */
    public void setForward(Node<K, V> n)
    {
        forward = n;
    }

    /**
     * setBackward Sets the Node's backward link.
     * @param n The input for the Node's backward link.
     */
    public void setBackward(Node<K, V> n)
    {
        backward = n;
    }

    /**
     * getForward Returns the Node's forward link.
     * @return The Node's forward link.
     */
    public Node<K, V> getForward()
    {
        return forward;
    }

    /**
     * getBackward Returns the Node's backward link.
     * @return The Node's backward link.
     */
    public Node<K, V> getBackward()
    {
        return backward;
    }
}
