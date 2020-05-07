package com.coursera.gladlib;

import com.coursera.edu.duke.FileResource;
import com.coursera.edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "src\\main\\java\\com\\coursera\\gladlib\\data";
    private List<String> storyWords;
    private List<String> labelCategory;

    public GladLibMap() {
        myMap = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        storyWords = new ArrayList<>();
        labelCategory = new ArrayList<>();
        storyWords.clear();
        labelCategory.clear();
    }

    public GladLibMap(String source) {
        myMap = new HashMap<>();
        initializeFromSource(source);
        myRandom = new Random();
        storyWords = new ArrayList<>();
        labelCategory = new ArrayList<>();
        storyWords.clear();
        labelCategory.clear();
    }

    private void initializeFromSource(String source) {
        String[] categoriesArray = {"adjective", "noun", "color", "country",
                "name", "animal", "timeframe", "verb", "fruit"};

        for (String category : categoriesArray) {
            ArrayList<String> categoryDataList = readIt(source + "/" + category + ".txt");
            myMap.put(category, categoryDataList);
        }
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {

        if (label.equals("number")) {
            labelCategory.add(label);
            return "" + myRandom.nextInt(50) + 5;
        } else {
            if (myMap.containsKey(label)) {
                labelCategory.add(label);
                return randomFrom(myMap.get(label));
            } else {
                return "**UNKNOWN**";
            }
        }
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub = getSubstitute(w.substring(first + 1, last));
        if (isWordExist(sub.toLowerCase())) {
            do {
                sub = getSubstitute(w.substring(first + 1, last));
            } while (isWordExist(sub));
        }
        storyWords.add(sub.toLowerCase());
        return prefix + sub + suffix;
    }

    private boolean isWordExist(String word) {
        boolean isExist = false;
        int index = storyWords.indexOf(word);
        if (index >= 0) {
            isExist = true;
        }
        return isExist;
    }

    public int totalWordsInMap() {
        int sum = 0;
        for (String category : myMap.keySet()) {
            ArrayList<String> words = myMap.get(category);
            sum += words.size();
        }
        return sum;
        /*int totalWords = 0;
        for (ArrayList<String> wordsList : myMap.values()) {
            totalWords = totalWords + wordsList.size();

        }
        return totalWords;*/
    }

    public int totalWordsConsidered() {
        int totalWords = 0;

        for (String label : labelCategory) {
            if (myMap.containsKey(label)) {
                totalWords = myMap.get(label).size();
            }
        }
        return totalWords;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory() {
        System.out.println("\n");
        String story = fromTemplate("src\\main\\java\\com\\coursera\\gladlib\\data\\madtemplate2.txt");
        printOut(story, 60);
    }
}
