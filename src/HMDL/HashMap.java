package HMDL;

public class HashMap<K, V> implements HashMapInterface<K, V> {
    private Node<K, V>[] arr;
    private Node<K, V> front = null, rear = null;
    private final int CAPACITY = 1000;
    private int capacity;
    private int totalItems = 0;
    private final double LOAD_THRESHOLD = .75;

    /**
     * Constructor creates a new HashMap with default capacity.
     */
    public HashMap() {
        arr = new Node[capacity = CAPACITY];
    }

    /**
     * Constructor creates a new HashMap with user-defined capacity.
     * @param cap The new capacity for the HashMap.
     */
    public HashMap(int cap) {
        if(cap < 0) {
            throw new IndexOutOfBoundsException("Array cannot have negative capacity.");
        }
        arr = new Node[capacity = cap];
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
            if((double) (totalItems / capacity) >= LOAD_THRESHOLD) {
                resize();
            }
            if(front == null) {
                front = rear = insert;
            } else {
                rear.setForward(insert);
                rear = rear.getForward();
            }
            return true;
        }
        putInCollision(index, insert);
        return true;
    }

    /**
     * putinCollision Puts an element in the HashMap where there is a collision at a specified index.
     * @param index The index for the element.
     * @param insert The new element to insert.
     */
    private void putInCollision(int index, Node<K, V> insert) {
        Node<K, V> parser = arr[index];
        while(true) {
            if(parser.getBackward() == null) {
                parser.setBackward(insert);
                totalItems++;
                if((double)(totalItems / capacity) >= LOAD_THRESHOLD) {
                    resize();
                }
                rear.setForward(insert);
                rear = rear.getForward();
                return;
            }
            parser = parser.getBackward();
        }
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
            } else if(parser.getBackward() != null) {
                parser = parser.getBackward();
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
        if(arr[index] != null && front == rear) {
            arr[index] = null;
            front = rear = null;
            totalItems--;
            return true;
        }
        if(arr[index].getKey().equals(k) && arr[index].getBackward() == null) {
            removeEntryAtIndex(index);
            return true;
        }
        return removeEntryInCollision(index, k);
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
            } else if(parser.getBackward() != null) {
                parser = parser.getBackward();
            } else {
                return false;
            }
        }
    }

    /**
     * removeEntryAtIndex Removes an element from the HashMap where there are no collisions.
     * @param index The index of the target element.
     */
    private void removeEntryAtIndex(int index) {
        if(arr[index] == front) {
            front = front.getForward();
        } else if(arr[index] == rear) {
            Node<K, V> predecessor = findPredecessor(arr[index]);
            predecessor.setForward(null);
        } else {
            Node<K, V> predecessor = findPredecessor(arr[index]);
            predecessor.setForward(predecessor.getForward().getForward());
        }
        arr[index] = null;
        totalItems--;
    }

    /**
     * removeEntryInCollision Removes an element from the HashMap where there are collisions.
     * @param index The index of the target element.
     * @param k The key of the target element.
     * @return True if the element was found and removed, false otherwise.
     */
    private boolean removeEntryInCollision(int index, K k) {
        Node<K, V> parser = arr[index];
        while(true) {
            if(parser.getBackward().getKey().equals(k)) {
                if(parser.getBackward().getBackward() != null) {
                    parser.setBackward(parser.getBackward().getBackward());
                } else if(parser.getBackward().getBackward() == null) {
                    parser.setBackward(null);
                }
                totalItems--;
                parser = findPredecessor(parser);
                parser.setForward(parser.getForward().getForward());
                return true;
            } else if(parser.getBackward() != null) {
                parser = parser.getBackward();
            } else {
                return false;
            }
        }
    }

    /**
     * findPredecessor Traverses the Linked List of insertion-ordered elements to find the predecessor of a particular
     * node. Necessary for deletion of an element.
     * @param node The node of which to find the predecessor.
     * @return The predecessor node.
     */
    private Node<K, V> findPredecessor(Node<K, V> node) {
        Node<K, V> parser = front;
        while(true) {
            if(parser.getForward() == node) {
                return parser;
            }
            parser = parser.getForward();
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
     * resize Resizes the internal array to double capacity if the original array's load threshold is met or exceeded.
     */
    private void resize() {
        Node<K, V>[] resizedArray = new Node[capacity *= 2];
        Node<K, V> parser = front;
        while(parser != null) {
            putForResizing(parser, resizedArray);
            parser = parser.getForward();
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
            while(parser.getBackward() != null) {
                parser = parser.getBackward();
            }
            parser.setBackward(node);
        }
    }

    /**
     * getInsertionOrder Returns an array of the Keys in the HashMap by their insertion order.
     * @return An array of keys in the HashMap ordered by insertion.
     */
    public K[] getInsertionOrder() {
        Node<K, V> parser = front;
        K[] insertionArr = (K[])new Object[totalItems];
        for(int i = 0; i < totalItems; i++) {
            insertionArr[i] = parser.getKey();
            parser = parser.getForward();
        }
        return insertionArr;
    }
}
