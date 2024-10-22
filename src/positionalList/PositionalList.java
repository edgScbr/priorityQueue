package positionalList;

public interface PositionalList<E> {

    // Returns the number of elements in the list
    int size();

    // Checks whether the list is empty
    boolean isEmpty();

    // Returns the first position in the list (or null if empty)
    Position<E> first();

    // Returns the last position in the list (or null if empty)
    Position<E> last();

    // Returns the position immediately before position p (or null if p is first)
    Position<E> before(Position<E> p) throws IllegalArgumentException;

    // Returns the position immediately after  position p (or null if p is last)
    Position<E> after(Position<E> p) throws IllegalArgumentException;

    // Inserts element e at the front of the list and returns its new position
    Position<E> addFirst(E e);

    // Inserts element e at the back  of the list and returns its new position
    Position<E> addLast(E e);

    // Inserts element e immediately before position p and returns its new position
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

    // Inserts element e immediately after position p and returns its new position
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

    // Replaces the element stored at position p and returns the replaced element.
    E set(Position<E> p, E e) throws IllegalArgumentException;

    // Removes the element stored at position p and returns it (invalidating p)
    E remove(Position<E> p) throws IllegalArgumentException;
}
