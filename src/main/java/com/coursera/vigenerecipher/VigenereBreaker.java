package com.coursera.vigenerecipher;

import com.coursera.edu.duke.FileResource;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;

public class VigenereBreaker {
    private int[] validKey;
    private final String ENGLISH_DICTIONARY_FILE_PATH = "src/main/java/com/coursera/vigenerecipher/dictionaries/English";
    private final String DANISH_DICTIONARY_FILE_PATH = "src/main/java/com/coursera/vigenerecipher/dictionaries/Danish";
    private final String DUTCH_DICTIONARY_FILE_PATH = "src/main/java/com/coursera/vigenerecipher/dictionaries/Dutch";
    private final String FRENCH_DICTIONARY_FILE_PATH = "src/main/java/com/coursera/vigenerecipher/dictionaries/French";
    private final String GERMAN_DICTIONARY_FILE_PATH = "src/main/java/com/coursera/vigenerecipher/dictionaries/German";
    private final String ITALIAN_DICTIONARY_FILE_PATH = "src/main/java/com/coursera/vigenerecipher/dictionaries/Italian";
    private final String PORTUGUESE_DICTIONARY_FILE_PATH = "src/main/java/com/coursera/vigenerecipher/dictionaries/Portuguese";
    private final String SPANISH_DICTIONARY_FILE_PATH = "src/main/java/com/coursera/vigenerecipher/dictionaries/Spanish";

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

    public void breakVigenere3(String filePath) {
        FileResource fileResourceText = new FileResource(filePath);
        langList();
        String testingFile = fileResourceText.asString();
        breakForAllLangs(testingFile, langList());
        String keyAsWord = getKeyAsWord();
        System.out.println("**************");
//        System.out.println(keyAsWord);
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
            int[] keys = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
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

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> characterMap = new HashMap<>();
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = 0; i < chars.length; i++) {
            characterMap.put(chars[i], 0);
        }
        for (String word : dictionary) {
            for (char s : characterMap.keySet()) {
                if (word.contains(Character.toString(s))) {
                    characterMap.put(s, characterMap.get(s) + 1);
                }
            }
        }
        int maxValue = 0;
        for (char s : characterMap.keySet()) {
            int currentValue = characterMap.get(s);
            if (currentValue > maxValue) {
                maxValue = currentValue;
            }
        }
        for (char s : characterMap.keySet()) {
            if (characterMap.get(s) == maxValue) {
                return s;
            }
        }
        return 'a';
    }

    private HashMap<String, HashSet<String>> langList() {
        HashMap<String, HashSet<String>> languages = new HashMap<>();
        FileResource English = new FileResource(ENGLISH_DICTIONARY_FILE_PATH);
        languages.put("English", readDictionary(English));

        FileResource Danish = new FileResource(DANISH_DICTIONARY_FILE_PATH);
        languages.put("Danish", readDictionary(Danish));

        FileResource Dutch = new FileResource(DUTCH_DICTIONARY_FILE_PATH);
        languages.put("Dutch", readDictionary(Dutch));

        FileResource French = new FileResource(FRENCH_DICTIONARY_FILE_PATH);
        languages.put("French", readDictionary(French));

        FileResource German = new FileResource(GERMAN_DICTIONARY_FILE_PATH);
        languages.put("German", readDictionary(German));

        FileResource Italian = new FileResource(ITALIAN_DICTIONARY_FILE_PATH);
        languages.put("Italian", readDictionary(Italian));

        FileResource Portuguese = new FileResource(PORTUGUESE_DICTIONARY_FILE_PATH);
        languages.put("Portuguese", readDictionary(Portuguese));

        FileResource Spanish = new FileResource(SPANISH_DICTIONARY_FILE_PATH);
        languages.put("Spanish", readDictionary(Spanish));

        return languages;
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        for (String s : languages.keySet()) {
            HashSet lang = languages.get(s);
            String decrypted = breakForLanguage(encrypted, lang);
            int wordCount = countWords(decrypted, lang);
            System.out.println("LANGUAGE CHOSEN = " + s);
            System.out.println("Decrypted message = "+ decrypted);
            System.out.println("Words counted = "+ wordCount);
        }
    }

}
