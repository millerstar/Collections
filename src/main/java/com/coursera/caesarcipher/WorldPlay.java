package com.coursera.caesarcipher;

import java.io.FileInputStream;
import java.io.IOException;

public class WorldPlay {

    public static String emphasize(String phrase, char ch) {
        String finalString = null;
        finalString = replaceVowels(phrase, ch);
        return finalString;
    }

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
        for (int j = 0; j < phrase.length(); j++) {
            if (!isVowel(phrase.charAt(j))) {
                newPhrase.append(phrase.charAt(j));
            } else {
                // newPhrase.append(ch);
                if (j % 2 == 0) {
                    newPhrase.append('*');
                } else {
                    newPhrase.append('+');
                }
            }
        }
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
}
