public interface Collection<E> {

    void add(E t);

    void remove(E t);

    void clear();

    E getFirst();

    boolean contains(E t);

    int size();

    Object[] toArray();

    E[] toArray(E[] arr);
}
