package priorityQueue;

import java.util.Comparator;

public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {

    protected static class PQEntry<K, V> implements Entry<K, V> {

        private K k;        // Llave
        private V v;        // Valor

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
    } // Fin de la clase interna

    private Comparator<K> comp;

    // Crea una cola de prioridad vacía usando el comparador dado para ordenar las llaves
    protected AbstractPriorityQueue(Comparator<K> comp) {
        this.comp = comp;
    }

    // Crea una cola de prioridad vacía basada en el orden natural de sus llaves
    protected AbstractPriorityQueue() {
        this(new DefaultComparator<K>());
    }

    // Método para comparar dos entradas según la llave
    protected int compare(Entry<K, V> a, Entry<K, V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    // Determina si una llave es válida
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comp.compare(key, key) == 0); // Verifica si la llave puede compararse consigo misma
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Llave incompatible");
        }
    }

    // Verifica si la cola de prioridad está vacía
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
