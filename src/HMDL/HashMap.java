package HMDL;

public class HashMap<K, V> implements HashMapInterface<K, V>
{
    private Node<K, V>[] arr;
    private final int CAPACITY = 1000;
    private int capacity;
    private int totalItems = 0;

    /**
     * Constructor creates a new HashMap with default capacity.
     */
    public HashMap()
    {
        arr = new Node[CAPACITY];
        capacity = CAPACITY;
    }

    /**
     * Constructor creates a new HashMap with user-defined capacity.
     * @param c The new capacity for the HashMap.
     */
    public HashMap(int c)
    {
        if(c < 0)
        {
            throw new IndexOutOfBoundsException("Array cannot have negative capacity.");
        }
        arr = new Node[c];
        capacity = c;
    }

    /**
     * put Inserts a new key-value pair into the HashMap.
     * @param k The key of the new element.
     * @param v The value of the new element.
     * @return True if insertion was successful, false otherwise.
     */
    public boolean put(K k, V v)
    {
        if(k == null)
        {
            return false;
        }
        Node<K, V> insert = new Node<>(k, v);
        int index = Math.abs(k.hashCode()) % capacity;
        if(arr[index] == null)
        {
            arr[index] = insert;
            totalItems++;
            return true;
        }
        Node<K, V> parser = arr[index];
        if(parser != null)
        {
            while(true)
            {
                if(parser.getForward() == null)
                {
                    parser.setForward(insert);
                    insert.setBackward(parser);
                    totalItems++;
                    return true;
                }
                parser = parser.getForward();
            }
        }
        return false;
    }

    /**
     * get Returns the value in a key-value pair from a user-defined key.
     * @param k The key to search for.
     * @return The value corresponding to the key.
     */
    public V get(K k)
    {
        if(k == null)
        {
            return null;
        }
        int index = Math.abs(k.hashCode()) % capacity;
        if(arr[index] == null)
        {
            return null;
        }
        Node<K, V> parser = arr[index];
        while(true)
        {
            if(parser.getKey().equals(k))
            {
                return parser.getValue();
            }
            else if(parser.getForward() != null)
            {
                parser = parser.getForward();
            }
            else
            {
                return null;
            }
        }
    }

    /**
     * remove Removes a key-value pair from the HashMap.
     * @param k The key to search for.
     * @return True if the key-value pair is removed, false otherwise.
     */
    public boolean remove(K k)
    {
        if(k == null)
        {
            return false;
        }
        int index = Math.abs(k.hashCode()) % capacity;
        if(arr[index] == null)
        {
            return false;
        }
        if(arr[index].getKey().equals(k))
        {
            if(arr[index].getForward() == null)
            {
                arr[index] = null;
            }
            else
            {
                Node<K, V> temp = arr[index].getForward();
                arr[index] = temp;
            }
            totalItems--;
            return true;
        }
        Node<K, V> parser = arr[index];
        while(true)
        {
            if(parser.getForward().getKey().equals(k))
            {
                if(parser.getForward().getForward() != null)
                {
                    parser.setForward(parser.getForward());
                }
                else if(parser.getForward().getForward() == null)
                {
                    parser.setForward(null);
                }
                totalItems--;
                return true;
            }
            else if(parser.getForward() != null)
            {
                parser = parser.getForward();
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * contains Searches the HashMap for a user-defined key.
     * @param k The key to search for.
     * @return True if the key is present, false otherwise.
     */
    public boolean contains(K k)
    {
        if(k == null)
        {
            return false;
        }
        int index = Math.abs(k.hashCode()) % capacity;
        if(arr[index] == null)
        {
            return false;
        }
        Node<K, V> parser = arr[index];
        while(true)
        {
            if(parser.getKey().equals(k))
            {
                return true;
            }
            else if(parser.getForward() != null)
            {
                parser = parser.getForward();
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * isEmpty Reports if the HashMap is empty.
     * @return True if the HashMap is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return totalItems == 0;
    }

    /**
     * isFull Reports if the HashMap is full.
     * @return False; a HashMap is never full.
     */
    public boolean isFull()
    {
        return false;
    }

    /**
     * size Returns the number of key-value pairs contained in the HashMap.
     * @return The number of key-value pairs contained in the HashMap.
     */
    public int size()
    {
        return totalItems;
    }
}