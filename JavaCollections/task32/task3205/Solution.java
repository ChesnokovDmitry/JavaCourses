package com.javarush.task.task32.task3205;

import java.lang.reflect.Proxy;

/* 
Создание прокси-объекта
*/

public class Solution {
    public static void main(String[] args) {
        SomeInterfaceWithMethods obj = getProxy();
        obj.stringMethodWithoutArgs();
        obj.voidMethodWithIntArg(1);

        /* expected output
        stringMethodWithoutArgs in
        inside stringMethodWithoutArgs
        stringMethodWithoutArgs out
        voidMethodWithIntArg in
        inside voidMethodWithIntArg
        inside voidMethodWithoutArgs
        voidMethodWithIntArg out
        */
    }

    public static SomeInterfaceWithMethods getProxy() {
        SomeInterfaceWithMethods simClass = new SomeInterfaceWithMethodsImpl();
        ClassLoader simClassLoader = simClass.getClass().getClassLoader();
        Class[] interfaces = simClass.getClass().getInterfaces();
        SomeInterfaceWithMethods someInterfaceWithMethods =
                (SomeInterfaceWithMethods) Proxy.newProxyInstance(simClassLoader, interfaces, new CustomInvocationHandler(simClass));

        return someInterfaceWithMethods;
    }
}
