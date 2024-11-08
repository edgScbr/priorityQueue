package positionalList;

public interface Position<E> {

    // Regresa el elemento guardado en esta posicion
    E getElement() throws IllegalStateException;
}
