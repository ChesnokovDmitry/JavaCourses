package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;
import java.util.HashSet;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<>(Math.max((int) (collection.size() / .75f) + 1, 16));

        for (E e : collection) {
            add(e);
        }
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.defaultWriteObject();

        outputStream.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        outputStream.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        outputStream.writeInt(map.size());

        for (E key : map.keySet()) {
            outputStream.writeObject(key);
        }
    }

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        inputStream.defaultReadObject();

        map = new HashMap<>(inputStream.readInt(), inputStream.readFloat());
        int size = inputStream.readInt();

        for (int i = 0; i < size; i++) {
            add((E) inputStream.readObject());
        }
    }

    @Override
    public boolean add(E e) {
        if (!map.containsKey(e)) {
            map.put(e, PRESENT);
            return true;
        } else
            return false;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        map.remove(o);

        return map.containsKey(o);
    }

    @Override
    public Object clone() {
        try {
            AmigoSet cloneSet = (AmigoSet) super.clone();
            cloneSet.map = (HashMap) map.clone();
            return cloneSet;
        } catch (Exception ex) {
            throw new InternalError();
        }
    }
}
