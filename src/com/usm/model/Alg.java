package com.usm.model;

import java.util.Scanner;

import static java.lang.Math.pow;

/**
 * Created by Валентина on 17.01.2017.
 */
public class Alg {
    private String inString;
    private int m = 26;
    private char[] cipher;
    private int a, b;
    private int[] x;
    private char[] alph = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private int[] prime = {1453, 1481, 1483, 1487, 1499, 1531, 1543, 1559, 1583, 1609, 1613, 1621,
            1637, 1663, 1669, 1699, 1721, 1733, 1741, 1747, 1759, 1777, 1811, 1847, 1871, 1873,
            1877, 1889, 1907, 1933, 1951, 1973, 1993, 1999, 2003, 2011, 2027, 2029, 2053, 2063};

    public Alg() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name: ");
        inString = sc.next();
        x = new int[inString.length()];
        cipher = new char[inString.length()];

        a = prime[(int)(Math.random() * prime.length)];
        b = 3;
    }

    public Alg(String name) {
        inString = name;
        x = new int[inString.length()];
        cipher = new char[inString.length()];

        a = prime[(int)(Math.random() * (prime.length-1))];
        b = 3;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    public String getName() {
        return inString;
    }

    public char[] encrypt() {
        char[] name = inString.toUpperCase().toCharArray();

        //записываем позиции букв
        for (int i = 0; i < name.length; i++) {
            for (int j = 0; j < alph.length; j++) {
                if (name[i] == alph[j])
                    x[i] = j;
            }
        }

        //криптование
        int calc;
        for (int i = 0; i < name.length; i++) {
            calc = (a * x[i] + b) % m;
            for (int j = 0; j < alph.length; j++)
                if (calc == j)
                    cipher[i] = alph[j];
        }

        return cipher;
    }

    public char[] decrypt() {
        char[] outString = new char[cipher.length];

        for (int i = 0; i < x.length; i++)
            x[i] = 0;

        //записываем позиции букв
        for (int i = 0; i < cipher.length; i++) {
            for (int j = 0; j < alph.length; j++) {
                if (cipher[i] == alph[j])
                    x[i] = j;
            }
        }

        //декриптование
        int symbPos;
        int a_1 = getMinusMod(a);
        for (int i = 0; i < outString.length; i++) {
            symbPos = (a_1 * (x[i] - b)) % m;
            for (int j = 0; j < alph.length; j++)
                if (symbPos == j)
                    outString[i] = alph[j];
        }

        return outString;
    }

    public int getMinusMod(int b) {
        int result = 0;
        int q, r;

        int n0 = m;
        int b0 = b;
        int t0 = 0;
        int t = 1;
        q = (int)(n0/b0);
        r = n0 - q * b0;
        while (b0 != 1 && r != 0) {
            result = t0 - q * t;
            if (result > 0)
                result %= m;
            else if (result < 0)
                result = m - (-result) % m;
            n0 = b0;
            b0 = r;
            q = (int)(n0/b0);
            r = n0 - q * b0;
            t0 = t;
            t = result;
        }

        return result;
    }
}
