package com.coursera.vigenerecipher;

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
            int newKey = caesarCracker.getKey(slicedEncryptedString);
            key[i] = newKey;
        }
        return key;
    }

    public void breakVigenere() {
        //WRITE YOUR CODE HERE

    }


}
