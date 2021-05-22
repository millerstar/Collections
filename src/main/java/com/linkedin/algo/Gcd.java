package com.linkedin.algo;

public class Gcd {

    public int getGcd(int firstNum, int secNum) {
        while (secNum != 0) {
            int temp = firstNum;
            firstNum = secNum;
            secNum = temp % secNum;
        }
        return firstNum;
    }

    public static void main(String[] args) {
        Gcd gcd = new Gcd();
        System.out.println(gcd.getGcd(96,60));
    }
}
