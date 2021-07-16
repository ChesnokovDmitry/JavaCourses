package com.javarush.task.task35.task3509;

import java.util.*;

/* 
Collections & Generics
*/

public class Solution {

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        //напишите тут ваш код
        ArrayList<T> arrayList = new ArrayList<>();

        for (Object obj : elements) {
            arrayList.add((T) obj);
        }

        return arrayList;
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        HashSet<T> hashSet = new HashSet<>();

        for (Object obj : elements) {
            hashSet.add((T) obj);
        }

        return hashSet;
    }

    public static <K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        HashMap<K, V> hashMap = new HashMap<>();

        if (keys.size() != values.size())
            throw new IllegalArgumentException();

        for (int i = 0; i < keys.size(); i++) {
            hashMap.put(keys.get(i), values.get(i));
        }

        return hashMap;
    }
}
