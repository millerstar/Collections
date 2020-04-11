package com.coursera.caesarcipher;

import com.coursera.utils.FileManager;

import java.io.IOException;

public class CaesarBreaker {
    public static final String WORDS_LOTS_OF_E_FILE_PATH = "src/main/resources/wordsLotsOfEs.txt";
    public static final String WORDS_LOTS_OF_E_ENCRYPTED_FILE_PATH = "src/main/resources/wordsLotsOfEsEncrypted.txt";
    public static final String ROMEO_FILE_PATH = "src/main/resources/romeo.txt";
    public static final String MYSTERY_FILE_PATH = "src/main/resources/mysteryTwoKeysPractice.txt";

    public static int maxIndex(int[] frequencyArray) {
        int maxIndex = 0;
        for (int i = 0; i < frequencyArray.length; i++) {
            if (frequencyArray[i] > frequencyArray[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public int[] lettersCounter(String myString) {
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

    public int getDecryptionKey(int maxIndex) {
        int dkey = maxIndex - 4;
        if (maxIndex < 4) {
            dkey = 26 - (4 - maxIndex);
        }
        return dkey;
    }

    public String decrypt(String encryptedText) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqLetters = lettersCounter(encryptedText);
        int maxDex = maxIndex(freqLetters);
        int dkey = getDecryptionKey(maxDex);
        return cc.encrypt(encryptedText, 26 - dkey);
    }

    public  void testDecrypt() throws IOException {
        CaesarCipher caesarCipher = new CaesarCipher();
        String fileString = FileManager.getFileString(WORDS_LOTS_OF_E_FILE_PATH);
        String encrytedText = caesarCipher.encrypt(fileString, 17);
        System.out.println("Encrypted text: " + encrytedText);
        String decryptedText = decrypt(encrytedText);
        System.out.println("--------------------------------");
        System.out.println("Decrypted text: " + decryptedText);

    }

    public static String halfOfString(String message, int start) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = start; i < message.length(); i += 2) {
            stringBuilder.append(message.charAt(i));
        }
        return stringBuilder.toString();
    }

    public  int getKey(String s) {
        int[] lettersCounter = lettersCounter(s);
        return getDecryptionKey(maxIndex(lettersCounter));
    }

    public String decryptTwoKeys(String encrypted) throws IOException {
        CaesarCipher caesarCipher = new CaesarCipher();
        String firstString = halfOfString(encrypted, 0);
        String secoundString = halfOfString(encrypted, 1);

        int firstEncryptedKey = getKey(firstString);
        int secondEncryptedKey = getKey(secoundString);

        System.out.println("The encryption keys of the first string: " + firstEncryptedKey + " and the second string: " + secondEncryptedKey + " was found!");
        String decryptedText = caesarCipher.encryptTwoKeys(encrypted, 26 - firstEncryptedKey, 26 -secondEncryptedKey);
        return decryptedText;
    }


    public static void main(String[] args) throws IOException {

        /*CaesarCipher cc = new CaesarCipher();
        System.out.println(cc.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 2, 20));*/


//        String fileString = FileManager.getFileString(MYSTERY_FILE_PATH);
//        String encreyptedMessage = decryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx");
//        System.out.println(encreyptedMessage);



//        System.out.println(getKey("Just a test string with lots of"));
//        System.out.println(halfOfString("Qbkm Zgis", 1));

//        testDecrypt();
    }


}
