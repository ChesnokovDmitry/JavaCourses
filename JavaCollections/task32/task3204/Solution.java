package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;

/* 
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        int[] byteToDec = new int[62];
        int count = 0;

        for (int i = 47; i < 123; i++) {
            if ((i > 47 && i < 58) || (i > 64 && i < 91) || (i > 96)) {
                byteToDec[count] = i;
                count++;
            }
        }

        int[] pass = new int[8];
        ByteArrayOutputStream aos = validPassword(pass, byteToDec);
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}";

        while (true) {
            if (aos.toString().matches(pattern)) {
                break;
            } else aos = validPassword(pass, byteToDec);
        }

        return aos;
    }

    private static ByteArrayOutputStream validPassword(int[] pass, int[] byteToDec) {
        SecureRandom random = new SecureRandom();
        ByteArrayOutputStream aos = new ByteArrayOutputStream();

        for (int i = 0; i < pass.length; i++) {
            pass[i] = byteToDec[random.nextInt(byteToDec.length)];
        }

        for (int j : pass) {
            aos.write(j);
        }

        return aos;
    }
}
