package com.coursera.randomstory;

import com.coursera.edu.duke.FileResource;

import java.util.ArrayList;
import java.util.List;

public class WordFrequencies {
    private List<String> myWords;
    private List<Integer> myFreq;
    public static final String FILE_PATH = "src/main/resources/errors.txt";

    public WordFrequencies() {
        this.myWords = new ArrayList<>();
        this.myFreq = new ArrayList<>();
    }

    public void findUnique(String stringFile) {
        FileResource resource = new FileResource(stringFile);
        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1) {
                myWords.add(s);
                myFreq.add(1);
            } else {
                int freq = myFreq.get(index);
                myFreq.set(index, freq + 1);
            }
        }
    }

    public int findMax() {
        int max = myFreq.get(0);
        int maxIndex = 0;
        for (int i = 0; i < myFreq.size(); i++) {
            if (myFreq.get(i) > max) {
                max = myFreq.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void tester(String fileString) {
        findUnique(fileString);
        System.out.println("Unique words: " + myWords.size());
        int index = findMax();
        System.out.println("Max words/frequency: " + myFreq.get(index));
    }

    public static void main(String[] args) {
        WordFrequencies wordFrequencies = new WordFrequencies();
        wordFrequencies.tester(FILE_PATH);
    }
}
