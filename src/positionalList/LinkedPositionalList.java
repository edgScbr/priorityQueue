package positionalList;

public class LinkedPositionalList<E> implements PositionalList<E> {

    private static class Node<E> implements Position<E> {
        private E element;      // Referencia del elemento almacenado en este nodo
        private Node<E> prev;   // Referencia al nodo anterior
        private Node<E> next;   // Referencia al nodo siguiente

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public E getElement() throws IllegalStateException {
            if (next == null) {
                throw new IllegalStateException("La posición ya no es válida");
            }
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setElement(E element) {
            this.element = element;
        }
    } // Fin de la clase anidada Node

    // Variables de instancia de LinkedPositionalList
    private Node<E> header;     // Centinela de inicio
    private Node<E> trailer;    // Centinela de fin
    private int size = 0;       // Número de elementos en la lista

    // Construye una nueva lista vacía
    public LinkedPositionalList() {
        header = new Node<>(null, null, null);          // crea el centinela de inicio
        trailer = new Node<>(null, header, null);       // el centinela de fin sigue al de inicio
        header.setNext(trailer);                        // el centinela de inicio es seguido por el de fin
    }

    // Valida la posición y la devuelve como nodo
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node<E>)) throw new IllegalArgumentException("Posición no válida");
        Node<E> node = (Node<E>) p; // Conversión segura
        if (node.getNext() == null)
            throw new IllegalArgumentException("p ya no está en la lista");
        return node;
    }

    // Devuelve el nodo dado como una posición (o null si es un centinela)
    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer) {
            return null;    // No exponer los centinelas al usuario
        }
        return node;
    }

    // Agrega el elemento e a la lista enlazada entre los nodos dados
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ); // Crea y enlaza el nuevo nodo
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> first() {
        return position(header.getNext());
    }

    @Override
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    @Override
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    @Override
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    @Override
    public Position<E> addFirst(E e) {
        return addBetween(e, header, header.getNext());
    }

    @Override
    public Position<E> addLast(E e) {
        return addBetween(e, trailer.getPrev(), trailer);
    }

    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node);
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    @Override
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        E answer = node.getElement();
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return answer;
    }
}
