package com.coursera.caesarcipher;

import com.coursera.utils.FileManager;

import java.io.IOException;

public class CaesarCipher {
    public static final String FILE_PATH = "src/main/resources/caesar_cipher_file.txt";

    public static void testCaesar() throws IOException {
        String myString = FileManager.getFileString(FILE_PATH);
        String encString = encrypt(myString, 23);
        System.out.println(encString);
    }

    public static String getShiftedString(int key) {
        StringBuilder alphaBeta = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuilder shiftedAlphaBeta = new StringBuilder();

        shiftedAlphaBeta.append(alphaBeta.subSequence(key, alphaBeta.length()));
        shiftedAlphaBeta.append(alphaBeta.subSequence(0, key));
        return shiftedAlphaBeta.toString();
    }

    public static String encrypt(String input, int key) {
        String alphaBeta = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder encryptText = new StringBuilder();
        String encriptedString = getShiftedString(key);
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                encryptText.append(' ');
            } else if (input.charAt(i) == '!') {
                encryptText.append('!');
            } else {
                int temp;
                if (Character.isUpperCase(input.charAt(i))) {
                    temp = alphaBeta.indexOf(input.charAt(i));
                    encryptText.append(encriptedString.charAt(temp));
                } else {
                    temp = alphaBeta.indexOf(Character.toUpperCase(input.charAt(i)));
                    encryptText.append(Character.toLowerCase(encriptedString.charAt(temp)));
                }
            }
        }
        return encryptText.toString();
    }


    public static void main(String[] args) throws IOException {
        System.out.println(encrypt("First Legion", 17));
        testCaesar();
    }

}
