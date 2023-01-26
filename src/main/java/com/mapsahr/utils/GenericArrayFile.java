package com.mapsahr.utils;

public interface GenericArrayFile<E> {
    void addToFile(E e);
    void addToFile(E[] e);
    void updateToFile(E n, E o);
    void removeFromFile(E e);
}
