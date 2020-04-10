package com.coursera.caesarcipher;

public class CaesarBreaker {

    public static int maxIndex(int[] frequencyArray) {
        int maxIndex = 0;
        for (int i = 0; i < frequencyArray.length; i++) {
            if (frequencyArray[i] > frequencyArray[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static int[] lettersCounter(String myString) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] counters = new int[26];
        for (int k = 0; k < myString.length(); k++) {
            char ch = myString.charAt(k);
            int chIndex = alpha.indexOf(Character.toUpperCase(ch));
            if (chIndex != -1) {
                counters[chIndex] += 1;
            } /*else {
                System.out.println("Counter char was not found in the array");
            }*/
        }
        return counters;
    }

    public static String decrypt(String encryptedText) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqLetters = lettersCounter(encryptedText);
        int maxDex = maxIndex(freqLetters);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encryptedText, 26 - dkey);
    }

    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
//        System.out.println(cc.encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees", 24));
        System.out.println(decrypt("Hsqr y rcqr qrpgle ugrf jmrq md cccccccccccccccccq"));
    }


}
