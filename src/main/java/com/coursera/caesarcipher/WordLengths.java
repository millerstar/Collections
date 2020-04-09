package com.coursera.caesarcipher;

import com.coursera.utils.FileManager;

import java.io.IOException;
import java.util.Random;

public class WordLengths {
    public static final String FILE_PATH = "src/main/resources/common.txt";
    public static final String CAESAR_FILE_PATH = "src/main/resources/caesar.txt";
    public static final String ROMEO_FILE_PATH = "src/main/resources/romeo.txt";
    public static final String HAMLET_FILE_PATH = "src/main/resources/hamlet.txt";
    public static final String MACBETH_FILE_PATH = "src/main/resources/macbeth.txt";
    public static final String LIKEIT_FILE_PATH = "src/main/resources/likeit.txt";
    public static final String ERRORS_FILE_PATH = "src/main/resources/errors.txt";
    public static final String TEST_FILE_PATH = "src/main/resources/test_words_file.txt";
    public static final String SMALL_HAMLET_FILE_PATH = "src/main/resources/smallHamlet.txt";

    public static int[] lettersCounter(String myString) {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] counters = new int[26];
        for (int k = 0; k < myString.length(); k++) {
            char ch = myString.charAt(k);
            int chIndex = alpha.indexOf(Character.toUpperCase(ch));
            if (chIndex != -1) {
                counters[chIndex] += 1;
            } else {
                System.out.println("Counter char was not found in the array");
            }
        }
        /*for (int k = 0; k < counters.length; k++) {
            System.out.println(alpha.charAt(k) + "\t" + counters[k]);
        }*/
        return counters;
    }

    public static int maxIndex(int[] frequencyArray) {
        int maxIndex = 0;
        for (int i = 0; i < frequencyArray.length; i++) {
            if (frequencyArray[i] > frequencyArray[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }


    // get all the common words from a file
    public static String[] getCommonWords() throws IOException {
        String commonWords = FileManager.getFileString(FILE_PATH);
        return commonWords.split("\n");
    }

    // returns the word index in the array
    public static int indexOf(String[] list, String word) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].trim().equals(word.trim())) {
                return i;
            }
        }
        return -1;
    }

    public static void countWords(String play, String[] commonWords, int[] counter) {
        String[] arrayOfWordsInPlay = play.split(" ");
        for (String word : arrayOfWordsInPlay) {
            word = word.toLowerCase();
            int index = indexOf(commonWords, word);
            if (index != -1) {
                counter[index] += 1;
            }
        }
    }

    public static void countShakespeare() throws IOException {
        String[] plays = {CAESAR_FILE_PATH, ROMEO_FILE_PATH, HAMLET_FILE_PATH, MACBETH_FILE_PATH, LIKEIT_FILE_PATH, ERRORS_FILE_PATH};
        String[] common = getCommonWords();
        int[] counts = new int[common.length];

        for (String play : plays) {
            String resourcePlay = FileManager.getFileString(play);
            countWords(resourcePlay, common, counts);
            System.out.println("done with " + play);
        }

        for (int i = 0; i < common.length; i++) {
            System.out.println(common[i].trim() + "\t\t" + counts[i]);
        }
    }

    public static String decrypt(String encryptedText) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqLetters = lettersCounter(encryptedText);
        int maxDex = maxIndex(freqLetters);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - dkey);
        }
        return cc.encrypt(encryptedText, 26 - dkey);
    }

    private static int wordCounter(String[] arrayOfWords) {
        int counter = 0;
        for (String word : arrayOfWords) {
            if (word.length() > 0) {
                counter += 1;
            }
        }
        return counter;
    }

    public static void countWordLengths(String resource, int[] counts) throws IOException {
        String resourceString = FileManager.getFileString(resource);
        String[] resourceStringArray = resourceString.split(" |\n|\r");

        int numOfWords = wordCounter(resourceStringArray);
        for (int i = 0; i < resourceStringArray.length; i++) {
            int wordLength = 0;
            if (resourceStringArray[i].equals("")) {
                continue;
            }
            if (resourceStringArray[i].contains(".") || resourceStringArray[i].contains(",")) {
                if (resourceStringArray[i].endsWith(".") ||resourceStringArray[i].endsWith(",")) {
                    resourceStringArray[i] = resourceStringArray[i].substring(0, resourceStringArray[i].length() - 1);
                }
                wordLength = resourceStringArray[i].length() - 1;
            } else {
                wordLength = resourceStringArray[i].length();
            }
            System.out.println(resourceStringArray[i].trim() + " " + wordLength);
        }
    }


    public static void main(String[] args) throws IOException {
        int[] counters = new int[13];

        countWordLengths(SMALL_HAMLET_FILE_PATH, counters);


        /*String commonWords = FileManager.getFileString(FILE_PATH);
        String[] commonWordsArray = commonWords.split("\n");

        String[] common = new String[20];
        for (int i = 0; i < commonWordsArray.length; i++) {
            common[i] = commonWordsArray[i].trim();
        }*/

//        lettersCounter("Think about how you approached solving this question and the algorithm you used.");

//        simulate(100000);

//        countShakespeare();


    }
}
