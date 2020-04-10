package com.coursera.caesarcipher;

import com.coursera.utils.FileManager;

import java.io.IOException;

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


    // get all the common words from a file
    public static String[] getCommonWords() throws IOException {
        String commonWords = FileManager.getFileString(FILE_PATH);
        return commonWords.split("\n|\r");
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

        for (int i = 0; i < resourceStringArray.length; i++) {
            int wordLength = 0;
            if (resourceStringArray[i].equals("")) {
                continue;
            }
            if (resourceStringArray[i].contains(".") || resourceStringArray[i].contains(",")) {
                if (resourceStringArray[i].endsWith(".") || resourceStringArray[i].endsWith(",")) {
                    resourceStringArray[i] = resourceStringArray[i].substring(0, resourceStringArray[i].length() - 1);
                }
            }
            wordLength = resourceStringArray[i].length();
            // update counts counter
            counts[wordLength] += 1;
//            System.out.println(resourceStringArray[i].trim() + " " + wordLength);
        }
    }

    public static void testCountWordLengths() throws IOException {
        String fileContent = SMALL_HAMLET_FILE_PATH;
        int[] counts = new int[31];
        countWordLengths(fileContent, counts);

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 1) {
                System.out.println(counts[i] + " word of length: " + i);
            } else if (counts[i] > 1) {
                System.out.println(counts[i] + " words of length: " + i);
            }
        }
        int maxIndex = indexOfMax(counts);
        System.out.println("The index with the maximum words is: " + maxIndex);
    }

    private static int indexOfMax(int[] counters) {
        int maxIndex = -1;
        int currentValue = 0;
        for (int i = 0; i < counters.length; i++) {
            if (counters[i] > currentValue) {
                currentValue = counters[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }


    public static void main(String[] args) throws IOException {

        testCountWordLengths();

        /*int[] counters = new int[13];

        countWordLengths(SMALL_HAMLET_FILE_PATH, counters);*/

//        lettersCounter("Think about how you approached solving this question and the algorithm you used.");

//        simulate(100000);

//        countShakespeare();


    }
}
