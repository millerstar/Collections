package com.coursera.randomstory;

import com.coursera.edu.duke.FileResource;
import com.coursera.utils.FileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordFrequencies2 {
    private List<String> myWords;
    private List<Integer> myFreqs;
    public static final String FILE_PATH = "src/main/resources/testwordfreqs.txt";

    public WordFrequencies2() {
        this.myWords = new ArrayList<>();
        this.myFreqs = new ArrayList<>();
    }

    public void findUnique(String stringFile) {
        myWords.clear();
        myFreqs.clear();

        FileResource fileResource = new FileResource(stringFile);
        for (String s : fileResource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                int freq = myFreqs.get(index);
                myFreqs.set(index, freq + 1);

            }
        }
    }

    public void tester(String fileStringData) {
        findUnique(fileStringData);
        System.out.println("Number of unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        }
        int index = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + myWords.get(index) + " " + myFreqs.get(index));

    }

    private int findIndexOfMax() {
        int maxValue = 0;
        int index = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > maxValue) {
                maxValue = myFreqs.get(i);
                index = i;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        WordFrequencies2 wordFrequencies2 = new WordFrequencies2();
        wordFrequencies2.tester(FILE_PATH);
    }
}
