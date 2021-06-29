package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        if (cacheMap.containsKey(key)) {
            SoftReference<AnyObject> softReference = cacheMap.get(key);

            //напишите тут ваш код
            return softReference.get();
        } else return null;
    }

    public AnyObject put(Long key, AnyObject value) {
        if (cacheMap.get(key) != null) {
            SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));

            //напишите тут ваш код
            AnyObject anyObject = softReference.get();
            softReference.clear();
            return anyObject;
        } else return null;
    }

    public AnyObject remove(Long key) {
        if (cacheMap.get(key) != null) {
            SoftReference<AnyObject> softReference = cacheMap.remove(key);

            //напишите тут ваш код
            AnyObject anyObject = softReference.get();
            softReference.clear();
            cacheMap.remove(key);

            return anyObject;
        } else return null;
    }
}