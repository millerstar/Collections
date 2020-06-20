package com.coursera.vigenerecipher;

import com.coursera.edu.duke.FileResource;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder builder = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            builder.append(message.charAt(i));
        }
        return builder.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker caesarCracker = new CaesarCracker();
        for (int i = 0; i < klength; i++) {
            String slicedEncryptedString = sliceString(encrypted, i, klength);
            key[i] = caesarCracker.getKey(slicedEncryptedString);
        }
        return key;
    }

    public void breakVigenere() {
        //WRITE YOUR CODE HERE
        FileResource fileResource = new FileResource();
        String testToStr = fileResource.asString();

        int[] applyKey = tryKeyLength(testToStr,5,'e');
        VigenereCipher vigenereCipher = new VigenereCipher(applyKey);
        System.out.println(vigenereCipher.decrypt(testToStr));
    }

    public void breakVigenere(String filePath) {
        //WRITE YOUR CODE HERE
        FileResource fileResource = new FileResource(filePath);
        String testToStr = fileResource.asString();

        int[] applyKey = tryKeyLength(testToStr,5,'e');
        VigenereCipher vigenereCipher = new VigenereCipher(applyKey);
        System.out.println(vigenereCipher.decrypt(testToStr));
    }


}
