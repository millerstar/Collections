package com.coursera.caesarcipher;

import com.coursera.utils.FileManager;

import java.io.IOException;

public class CaesarCipher {
    public static final String FILE_PATH = "src/main/resources/caesar_cipher_file.txt";

    public void testCaesar() throws IOException {
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

    public String encrypt(String input, int key) {
        String alphaBeta = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder encryptText = new StringBuilder();
        String encryptedString = getShiftedString(key);
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
                    temp = alphaBeta.indexOf(input.charAt(i));
                    encryptText.append(encryptedString.charAt(temp));
                } else {
                    temp = alphaBeta.indexOf(Character.toUpperCase(input.charAt(i)));
                    encryptText.append(Character.toLowerCase(encryptedString.charAt(temp)));
                }
            }
        }
        return encryptText.toString();
    }

    public static String encryptTwoKeys(String input, int key1, int key2) {
        String alphaBeta = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder encryptText = new StringBuilder();
        String encryptedStringKey1 = getShiftedString(key1);
        String encryptedStringKey2 = getShiftedString(key2);

        for (int i = 0; i < input.length(); i++) {
            int tempCounter = i;
            tempCounter++;
            if (input.charAt(i) == ' ') {
                encryptText.append(' ');
            } else if (input.charAt(i) == '!') {
                encryptText.append('!');
            } else if (input.charAt(i) == '.') {
                encryptText.append('.');
            } else {
                int temp = 0;
                if (Character.isUpperCase(input.charAt(i))) {
                    temp = alphaBeta.indexOf(input.charAt(i));
                    if (isOddNumber(tempCounter)) {
                        encryptText.append(encryptedStringKey2.charAt(temp));
                    } else {
                        encryptText.append(encryptedStringKey1.charAt(temp));
                    }
                } else {
                    temp = alphaBeta.indexOf(Character.toUpperCase(input.charAt(i)));
                    if (isOddNumber(tempCounter)) {
                        encryptText.append(Character.toLowerCase(encryptedStringKey2.charAt(temp)));
                    } else {
                        encryptText.append(Character.toLowerCase(encryptedStringKey1.charAt(temp)));
                    }
                }
            }
        }

        return encryptText.toString();
    }

    private static boolean isOddNumber(int number) {
        return number % 2 == 0;
    }


   /* public static void main(String[] args) throws IOException {
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
        testCaesar();

    }*/

}
