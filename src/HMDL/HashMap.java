package HMDL;

public class HashMap<K, V> implements HashMapInterface<K, V> {
    private Node<K, V>[] arr;
    private final int CAPACITY = 1000;
    private int capacity;
    private int totalItems = 0;
    private final double LOAD_THRESHOLD = .75;

    /**
     * Constructor creates a new HashMap with default capacity.
     */
    public HashMap() {
        arr = new Node[CAPACITY];
        capacity = CAPACITY;
    }

    /**
     * Constructor creates a new HashMap with user-defined capacity.
     * @param cap The new capacity for the HashMap.
     */
    public HashMap(int cap) {
        if(cap < 0) {
            throw new IndexOutOfBoundsException("Array cannot have negative capacity.");
        }
        arr = new Node[cap];
        capacity = cap;
    }

    /**
     * put Inserts a new key-value pair into the HashMap.
     * @param k The key of the new element.
     * @param v The value of the new element.
     * @return True if insertion was successful, false otherwise.
     */
    public boolean put(K k, V v) {
        if(k == null) {
            return false;
        }
        Node<K, V> insert = new Node<>(k, v);
        int index = Math.abs(k.hashCode()) % capacity;
        if(arr[index] == null) {
            arr[index] = insert;
            totalItems++;
            if((double)(totalItems / capacity) >= LOAD_THRESHOLD) {
                resize();
            }
            return true;
        }
        Node<K, V> parser = arr[index];
        if(parser != null) {
            while(true) {
                if(parser.getForward() == null) {
                    parser.setForward(insert);
                    insert.setBackward(parser);
                    totalItems++;
                    if((double)(totalItems / capacity) >= LOAD_THRESHOLD) {
                        resize();
                    }
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
    public V get(K k) {
        if(k == null) {
            return null;
        }
        int index = Math.abs(k.hashCode()) % capacity;
        if(arr[index] == null) {
            return null;
        }
        Node<K, V> parser = arr[index];
        while(true) {
            if(parser.getKey().equals(k)) {
                return parser.getValue();
            } else if(parser.getForward() != null) {
                parser = parser.getForward();
            } else {
                return null;
            }
        }
    }

    /**
     * remove Removes a key-value pair from the HashMap.
     * @param k The key to search for.
     * @return True if the key-value pair is removed, false otherwise.
     */
    public boolean remove(K k) {
        if(k == null) {
            return false;
        }
        int index = Math.abs(k.hashCode()) % capacity;
        if(arr[index] == null) {
            return false;
        }
        if(arr[index].getKey().equals(k)) {
            if(arr[index].getForward() == null) {
                arr[index] = null;
            } else {
                Node<K, V> temp = arr[index].getForward();
                arr[index] = temp;
            }
            totalItems--;
            return true;
        }
        Node<K, V> parser = arr[index];
        while(true) {
            if(parser.getForward().getKey().equals(k)) {
                if(parser.getForward().getForward() != null) {
                    parser.setForward(parser.getForward());
                } else if(parser.getForward().getForward() == null) {
                    parser.setForward(null);
                }
                totalItems--;
                return true;
            } else if(parser.getForward() != null) {
                parser = parser.getForward();
            } else {
                return false;
            }
        }
    }

    /**
     * contains Searches the HashMap for a user-defined key.
     * @param k The key to search for.
     * @return True if the key is present, false otherwise.
     */
    public boolean contains(K k) {
        if(k == null) {
            return false;
        }
        int index = Math.abs(k.hashCode()) % capacity;
        if(arr[index] == null) {
            return false;
        }
        Node<K, V> parser = arr[index];
        while(true) {
            if(parser.getKey().equals(k)) {
                return true;
            } else if(parser.getForward() != null) {
                parser = parser.getForward();
            } else {
                return false;
            }
        }
    }

    /**
     * isEmpty Reports if the HashMap is empty.
     * @return True if the HashMap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return totalItems == 0;
    }

    /**
     * isFull Reports if the HashMap is full.
     * @return False; a HashMap with resizing is never full.
     */
    public boolean isFull() {
        return false;
    }

    /**
     * size Returns the number of key-value pairs contained in the HashMap.
     * @return The number of key-value pairs contained in the HashMap.
     */
    public int size() {
        return totalItems;
    }

    /**
     * resize Resizes the internal array to double capacity if the original array's load threshold is 75% filled.
     */
    private void resize() {
        capacity *= 2;
        Node<K, V>[] resizedArray = new Node[capacity];
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != null) {
                Node<K, V> node = arr[i];
                putForResizing(node, resizedArray);
                node = arr[i].getForward();
                while(node != null) {
                    putForResizing(node, resizedArray);
                    node = node.getForward();
                }
            }
        }
        arr = resizedArray;
    }

    /**
     * putForResizing A Put method for the newly resized internal elements array.
     * @param node The Node to add.
     * @param resizedArray The newly resized elements array.
     */
    private void putForResizing(Node<K, V> node, Node<K, V>[] resizedArray) {
        int index = Math.abs(node.getKey().hashCode()) % capacity;
        if(resizedArray[index] == null) {
            resizedArray[index] = node;
        } else {
            Node<K, V> parser = resizedArray[index];
            while(parser.getForward() != null) {
                parser = parser.getForward();
            }
            parser.setForward(node);
            node.setBackward(parser);
        }
    }
}
