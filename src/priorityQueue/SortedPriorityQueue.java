package priorityQueue;

import positionalList.LinkedPositionalList;
import positionalList.Position;
import positionalList.PositionalList;

import java.util.Comparator;

public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    // Primary Collection of priority queue entries
    private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    // Creates an empty priority queue based on the natural ordering of its keys
    public SortedPriorityQueue() {
        super();
    }

    // Creates an empty priority queue using the given comparator to order keys
    public SortedPriorityQueue(Comparator<K> comp) {
        super();
    }

    // Inserts a key-value pair and returns the entry created
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);      // auxiliary key-checking method (could throw exception)
        Entry<K, V> newest = new PQEntry<>(key, value);
        Position<Entry<K, V>> walk = list.last();
        // Walk backward, looking for smaller key
        while (walk != null && compare(newest, walk.getElement()) < 0) {
            walk = list.before(walk);
        }
        if (walk == null) {
            list.addFirst(newest);          // New key is smallest
        } else {
            list.addAfter(walk, newest);    // Newest goes after walk
        }
        return newest;
    }

    // Returns (but does not remove) an entry with minimal key
    @Override
    public Entry<K, V> min() {
        if (list.isEmpty()) {
            return null;
        }
        return list.first().getElement();
    }

    @Override
    public Entry<K, V> removeMin() {
        if (list.isEmpty()) {
            return null;
        }
        return list.remove(list.first());
    }

    @Override
    public int size() {
        return list.size();
    }
}
