package positionalList;

public class LinkedPositionalList<E> implements PositionalList<E>{

    private static class Node<E> implements Position<E> {
        private E element;      // Reference of the element stored at this node
        private Node<E> prev;   // Reference
        private Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public E getElement() throws IllegalStateException {
           if(next == null) {
               throw new IllegalStateException("Position no longer valid");
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
    } // End of nested Node class

    // Instance variables of the LinkedPositionalList
    private Node<E> header;     // Header sentinel
    private Node<E> trailer;    // Trailer sentinel
    private int size = 0;       // Number of elements in the list


    // Constructs a new empty list
    public LinkedPositionalList() {
        header = new Node<>(null, null, null);  // create header
        trailer = new Node<>(null, header, null);    // trailer is preceded by header
        header.setNext(trailer);                                  // header is followed by trailer
    }

    // Validates the position and returns it as node
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if(!(p instanceof Node<E>)) throw new IllegalArgumentException("Invalid position");
        Node<E> node = (Node<E>) p; // Safe cast
        if(node.getNext() == null)
            throw new IllegalArgumentException("p is no longer in the list");
        return node;
    }

    // Returns the given node as a position (or null if it is a sentinel)
    private Position<E> position(Node<E> node) {
        if(node == header || node == trailer) {
            return null;    // Do not expose user to sentinels
        }
        return node;
    }

    // Adds element e to the linked list between the given nodes
    private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> newest = new Node<>(e, pred, succ); // Create and link new node
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
        node.setPrev(null)  ;
        return answer;
    }
}
