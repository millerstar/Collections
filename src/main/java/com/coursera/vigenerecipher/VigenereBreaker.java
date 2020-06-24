package com.coursera.vigenerecipher;

import com.coursera.edu.duke.FileResource;

import java.util.HashSet;

public class VigenereBreaker {
    private int[] validKey;
    private final String ENGLISH_DICTIONARY_FILE_PATH = "src/main/java/com/coursera/vigenerecipher/dictionaries/English";

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

        int[] applyKey = tryKeyLength(testToStr, 4, 'e');
        VigenereCipher vigenereCipher = new VigenereCipher(applyKey);
        System.out.println(vigenereCipher.decrypt(testToStr));
    }

    public void breakVigenere(String filePath) {
        //WRITE YOUR CODE HERE
        FileResource fileResource = new FileResource(filePath);
        String testToStr = fileResource.asString();
        int[] applyKey = tryKeyLength(testToStr, 4, 'e');
        VigenereCipher vigenereCipher = new VigenereCipher(applyKey);
        System.out.println(vigenereCipher.decrypt(testToStr));
    }

    public void breakVigenere2(String filePath) {
        FileResource fileResourceText = new FileResource(filePath);
        FileResource fileResourceDictionary = new FileResource(ENGLISH_DICTIONARY_FILE_PATH);
        HashSet<String> dictionary = readDictionary(fileResourceDictionary);
        String testingFile = fileResourceText.asString();
        String perfectDecryption = breakForLanguage(testingFile, dictionary);
        System.out.println(perfectDecryption);
        String keyAsWord = getKeyAsWord();
        System.out.println(keyAsWord);
    }

    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> dictionaryList = new HashSet<>();
        for (String line : fr.lines()) {
            String lineLower = line.toLowerCase();
            dictionaryList.add(lineLower);
        }
        return dictionaryList;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        String[] messageSplit = message.split("\\W+");
        int commonWords = 0;
        for (int i = 0; i < messageSplit.length; i++) {
            String word = messageSplit[i].toLowerCase();
            if (dictionary.contains(word)) {
                commonWords++;
            }
        }
        return commonWords;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int max = 0;
        String perfectDecrypted = null;
        for (int i = 1; i <= 100; i++) {
            int[] keys = tryKeyLength(encrypted, i, 'e');
            VigenereCipher vigenereCipher = new VigenereCipher(keys);
            String decrypted = vigenereCipher.decrypt(encrypted);
            int wordsCounted = countWords(decrypted, dictionary);
            if (wordsCounted > max) {
                max = wordsCounted;
                perfectDecrypted = decrypted;
                validKey = keys;
            }
        }
        return perfectDecrypted;
    }

    public String getKeyAsWord() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder keyAsWord = new StringBuilder();
        for (int i = 0; i < validKey.length; i++) {
            keyAsWord.append(alphabet.charAt(validKey[i]));
        }

        System.out.println("This is the valid key as numbers: ");
        for (int i = 0; i < validKey.length; i++) {
            System.out.println(validKey[i]);
        }
        System.out.println("And as a word:");
        return keyAsWord.toString();
    }

}
