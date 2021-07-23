package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String file = args[0];
        BufferedReader reader = new BufferedReader(new FileReader(file));
        TreeSet<Character> set = new TreeSet<>();

        while (reader.ready()) {
            set.add(Character.toLowerCase((char) reader.read()));
        }

        Character[] chars = set.toArray(new Character[0]);
        int count = 0;

        for (Character aChar : chars) {
            if (count == 5) break;
            if (aChar.toString().matches("[a-z]")) {
                System.out.print(aChar);
                count++;
            }
        }

        System.out.println();
    }
}
