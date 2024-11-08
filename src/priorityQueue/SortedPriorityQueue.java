package priorityQueue;

import positionalList.LinkedPositionalList;
import positionalList.Position;
import positionalList.PositionalList;

import java.util.Comparator;

public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    // Colección principal de entradas de la cola de prioridad
    private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    // Crea una cola de prioridad vacía basada en el orden natural de sus llaves
    public SortedPriorityQueue() {
        super();
    }

    // Crea una cola de prioridad vacía usando el comparador dado para ordenar las llaves
    public SortedPriorityQueue(Comparator<K> comp) {
        super();
    }

    // Inserta un par clave-valor y devuelve la entrada creada
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);      // método auxiliar para verificar la clave (podría lanzar una excepción)
        Entry<K, V> newest = new PQEntry<>(key, value);
        Position<Entry<K, V>> walk = list.last();
        // Retrocede buscando una clave menor
        while (walk != null && compare(newest, walk.getElement()) < 0) {
            walk = list.before(walk);
        }
        if (walk == null) {
            list.addFirst(newest);          // La nueva clave es la más pequeña
        } else {
            list.addAfter(walk, newest);    // La nueva entrada va después de walk
        }
        return newest;
    }

    // Devuelve (pero no elimina) una entrada con la clave mínima
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
