package com.coursera.caesarcipher;

public class CaesarCipherOne {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipherOne(int key) {
        this.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        this.shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }

    // class methods
    public String encrypt(String input) {
        StringBuilder encryptText = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                encryptText.append(' ');
            } else if (input.charAt(i) == '!') {
                encryptText.append('!');
            } else if (input.charAt(i) == '.') {
                encryptText.append('.');
            } else {
                int temp;
                if (Character.isUpperCase(input.charAt(i))) {
                    temp = alphabet.indexOf(input.charAt(i));
                    encryptText.append(shiftedAlphabet.charAt(temp));
                } else {
                    temp = alphabet.indexOf(Character.toUpperCase(input.charAt(i)));
                    encryptText.append(Character.toLowerCase(shiftedAlphabet.charAt(temp)));
                }
            }
        }
        return encryptText.toString();
    }

    public String decrypt (String encryptedText) {
        /*CaesarBreaker caesarBreaker = new CaesarBreaker();
        int[] freqLetters = caesarBreaker.lettersCounter(encryptedText);
        int maxDex = caesarBreaker.maxIndex(freqLetters);
        int dkey = caesarBreaker.getDecryptionKey(maxDex);*/
        CaesarCipher cc = new CaesarCipher();
        this.mainKey = 26 - mainKey;
        return cc.encrypt(encryptedText, mainKey);
    }
}
