package com.coursera.caesarcipher;

import java.io.FileInputStream;
import java.io.IOException;

public class WorldPlay {

    public static boolean isVowel(char ch) {
        boolean isVowel;
        switch (Character.toLowerCase(ch)) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                isVowel = true;
                break;
            default:
                isVowel = false;
        }
        return isVowel;
    }

    public static String replaceVowels(String phrase, char ch) {
        StringBuilder newPhrase = new StringBuilder();

        return newPhrase.toString();
    }

    public static String getFileString() throws IOException {
        FileInputStream fileInputStream;
        fileInputStream = new FileInputStream("src/main/resources/world_play_file.txt");

        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((i = fileInputStream.read()) != -1) {
            char currentChar = (char) i;
            stringBuilder.append(currentChar);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(getFileString());
        System.out.println(isVowel('o'));

    }
}
