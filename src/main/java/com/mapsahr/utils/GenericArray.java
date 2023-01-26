package com.mapsahr.utils;

public interface GenericArray<E> {
    void add(E e);
    void add(E[] list);
    void update(int index, E e);
    E get(int index);
    boolean remove(int index);
    boolean remove(E e);
    void removeAll(E e);
    void clear();
    int findFirst(E e);

}
