package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse(132);
    }

    public void recurse(int n) {
        int div = 2;

        if (n > 1) {
            if (n % div != 0) {
                while (n % div != 0)
                    div++;

            }
            System.out.print(div + " ");
            recurse(n / div);
        }
    }
}
