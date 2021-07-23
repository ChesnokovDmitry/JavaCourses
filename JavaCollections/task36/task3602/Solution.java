package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/

public class Solution {
    public static void main(String[] args) throws InstantiationException, NoSuchMethodException, IllegalAccessException {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() throws InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class[] classes = Collections.class.getDeclaredClasses();
        Class clazz = null;

        for (Class c : classes) {
            if (List.class.isAssignableFrom(c)) {
                if (Modifier.isPrivate(c.getModifiers())) {
                    if (Modifier.isStatic(c.getModifiers())) {
                        Method method = c.getMethod("get", int.class);
                        Constructor constructor;
                        try {
                            constructor = c.getDeclaredConstructor();
                        } catch (NoSuchMethodException e) {
                            continue;
                        }
                        try {
                            method.setAccessible(true);
                            constructor.setAccessible(true);
                            method.invoke(constructor.newInstance(),1);
                        } catch (InvocationTargetException e) {
                            if (e.getCause().toString().contains("IndexOutOfBoundsException")) {
                                clazz = c;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return clazz;
    }
}
