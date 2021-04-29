package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true
    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        byte[] digest = md.digest(toByteArray);

        //Вариант 1
        StringBuilder md5Hex = new StringBuilder();

        for (byte b : digest) {
            md5Hex.append(String.format("%02x", b));

            //Вариант 2
            //md5Hex.append(Integer.toHexString(b & 0xFF));
        }

        //Вариант 3
        /*BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);*/

        return md5.equals(md5Hex.toString());
    }
}
