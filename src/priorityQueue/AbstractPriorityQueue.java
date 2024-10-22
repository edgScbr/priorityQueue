package priorityQueue;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V>{

    protected static class PQEntry<K, V> implements Entry<K, V> {

        private K k;        // Key
        private V v;        // Value

        public PQEntry(K k, V v) {
            this.v = v;
            this.k = k;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        protected void setKey(K k) {
            this.k = k;
        }

        protected void setValue(V v) {
            this.v = v;
        }
    } // End of nested class

    private Comparator<K> comp;

    // Creates an empty priority queue using the given comparator to order keys
    protected AbstractPriorityQueue(Comparator<K> comp) {
        this.comp = comp;
    }

    // Creates an empty priority queue based on the natural ordering of its keys
    protected AbstractPriorityQueue() {
        this(new DefaultComparator<K>());
    }

    // Method for comparing two entries according to key
    protected int compare(Entry<K, V> a, Entry<K, V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    // Determines whether a key is valid
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comp.compare(key, key) == 0); // See if key can be compared to itself
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    // Test whether the priority queue is empty

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
