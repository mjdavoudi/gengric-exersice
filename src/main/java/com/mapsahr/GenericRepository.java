package com.mapsahr;

import com.mapsahr.entity.Person;
import com.mapsahr.utils.GenericArray;
import com.mapsahr.utils.GenericArrayFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GenericRepository<E extends Person> implements GenericArray<E>, GenericArrayFile<E> {
    private Person[] elements;
    private int emptyIndex;

    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    public GenericRepository() throws IOException {
        File file = new File("generic_array.txt");

        FileWriter fileWriter = new FileWriter(file);
        FileReader fileReader = new FileReader(file);

        this.bufferedWriter = new BufferedWriter(fileWriter);
        this.bufferedReader = new BufferedReader(fileReader);

    }

    public GenericRepository(int size) throws IOException {
        this();
        this.elements = new Person[size];
    }

    public GenericRepository(E[] elements) throws IOException {
        this();
        this.elements = elements;
    }

    @Override
    public void add(E e) {
        this.elements[this.emptyIndex] = e;
    }

    @Override
    public void add(E[] list) {
        for (E e : list) {
            add(e);
        }
    }

    @Override
    public void addToFile(E e) {
        add(e);
        writeToFile();
    }

    @Override
    public void addToFile(E[] e) {
        for (Person person : e) {
            addToFile((E) person);
        }
    }

    @Override
    public void update(int index, E e) {
        this.elements[index] = e;

    }

    @Override
    public void updateToFile(E n, E o) {
        for (int i = 0; i < this.elements.length; i++) {

            if (this.elements[i].equals(o)) {
                this.elements[i] = n;
            }
        }
        writeToFile();
    }

    @Override
    public E get(int index) {
        if (isValidIndex(index)) {
            if (elements[index] == null) {
                return null;
            }
            return (E) elements[index];
        }
        return null;
    }

    @Override
    public boolean remove(int index) {
        if (isValidIndex(index)) {
            this.elements[index] = null;
            setEmptyIndex();
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(E e) {
        int index = findFirst(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public void removeAll(E e) {
        for (Person person: elements) {
            if (person != null && person.equals(e)) {
                remove(e);
            }
        }
    }

    @Override
    public void removeFromFile(E e) {
        remove(e);
        writeToFile();
    }

    @Override
    public void clear() {
        for (Person person: elements) {
            if (person != null) {
                remove((E) person);
            }
        }
    }

    @Override
    public int findFirst(E e) {
        if (e == null) {
            return -1;
        }
        for (int i=0; i< elements.length; i++) {
            if (elements[i] != null && elements[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    private void setEmptyIndex() {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {

                this.emptyIndex = i;
                break;
            } else if (i == elements.length - 1) {
                extendedElements();
            }
        }
    }

    private void extendedElements() {
        int newSize = elements.length / 2 + elements.length;

        Person[] newElements = new Person[newSize];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private boolean isValidIndex(int index) {
        return (index < 0 || index > elements.length);

    }


    private void writeToFile() {

        try {
            for (Person person : elements) {
                this.bufferedWriter.write(person.toString() + "\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
