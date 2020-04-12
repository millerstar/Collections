package com.coursera.caesarcipher;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    CaesarCipher cc;

    // constructor
    public CaesarCipherTwo(int key1, int key2) {
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        this.shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
        this.cc = new CaesarCipher();

    }

    public String encrypt(String input) {
        StringBuilder encryptText = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int tempCounter = i;
            tempCounter++;
            if (input.charAt(i) == ' ') {
                encryptText.append(' ');
            } else if (input.charAt(i) == '!') {
                encryptText.append('!');
            } else if (input.charAt(i) == '.') {
                encryptText.append('.');
            } else if (input.charAt(i) == ',') {
                encryptText.append(',');
            } else {
                int temp = 0;
                if (Character.isUpperCase(input.charAt(i))) {
                    temp = alphabet.indexOf(input.charAt(i));
                    if (cc.isOddNumber(tempCounter)) {
                        encryptText.append(shiftedAlphabet2.charAt(temp));
                    } else {
                        encryptText.append(shiftedAlphabet1.charAt(temp));
                    }
                } else {
                    temp = alphabet.indexOf(Character.toUpperCase(input.charAt(i)));
                    if (cc.isOddNumber(tempCounter)) {
                        encryptText.append(Character.toLowerCase(shiftedAlphabet2.charAt(temp)));
                    } else {
                        encryptText.append(Character.toLowerCase(shiftedAlphabet1.charAt(temp)));
                    }
                }
            }
        }
        return encryptText.toString();
    }

    public String decrypt(String input) {
        this.mainKey1 = 26 - mainKey1;
        this.mainKey2 = 26 - mainKey2;
        return cc.encryptTwoKeys(input, mainKey1, mainKey2);
    }
}
