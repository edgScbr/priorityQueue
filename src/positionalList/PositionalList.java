package positionalList;

public interface PositionalList<E> {

    // Devuelve el número de elementos en la lista
    int size();

    // Verifica si la lista está vacía
    boolean isEmpty();

    // Devuelve la primera posición en la lista (o null si está vacía)
    Position<E> first();

    // Devuelve la última posición en la lista (o null si está vacía)
    Position<E> last();

    // Devuelve la posición inmediatamente anterior a la posición p (o null si p es la primera)
    Position<E> before(Position<E> p) throws IllegalArgumentException;

    // Devuelve la posición inmediatamente después de la posición p (o null si p es la última)
    Position<E> after(Position<E> p) throws IllegalArgumentException;

    // Inserta el elemento e al frente de la lista y devuelve su nueva posición
    Position<E> addFirst(E e);

    // Inserta el elemento e al final de la lista y devuelve su nueva posición
    Position<E> addLast(E e);

    // Inserta el elemento e inmediatamente antes de la posición p y devuelve su nueva posición
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

    // Inserta el elemento e inmediatamente después de la posición p y devuelve su nueva posición
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

    // Reemplaza el elemento almacenado en la posición p y devuelve el elemento reemplazado
    E set(Position<E> p, E e) throws IllegalArgumentException;

    // Elimina el elemento almacenado en la posición p y lo devuelve (invalidando p)
    E remove(Position<E> p) throws IllegalArgumentException;
}
