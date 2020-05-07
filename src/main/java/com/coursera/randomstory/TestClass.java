package com.coursera.randomstory;

import com.coursera.edu.duke.FileResource;
import com.coursera.gladlib.GladLib;
import com.coursera.gladlib.GladLibMap;

import java.util.HashMap;
import java.util.Map;

public class TestClass {
    public static final String FILE_PATH = "src/main/resources/likeit.txt";

    public static void countWords(String myFile) {
        FileResource fileResource = new FileResource(myFile);
        Map<String, Integer> wordsFreq = new HashMap<>();
        int totalWords = 0;
        for (String word : fileResource.words()) {
            word = word.toLowerCase();
            if (wordsFreq.containsKey(word)) {
                wordsFreq.put(word, wordsFreq.get(word) + 1);
            } else {
                wordsFreq.put(word, 1);
            }
            totalWords += 1;
        }
        for (String word : wordsFreq.keySet()) {
            int occurrences = wordsFreq.get(word);
            if (occurrences > 200) {
                System.out.println(word + "\t" + occurrences);
            }
        }
        System.out.println("Total words in the file: " + totalWords);
    }

    public static void main(String[] args) {
//        GladLib storyGenerator = new GladLib();
//        storyGenerator.makeStory();

        // hashMaps
//        countWords(FILE_PATH);

        GladLibMap storyGenerator = new GladLibMap();
        storyGenerator.makeStory();
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("Total words in the map: " + storyGenerator.totalWordsInMap());
        System.out.println("Total words by used label : " + storyGenerator.totalWordsConsidered());
    }
}
