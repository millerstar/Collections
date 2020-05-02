package com.coursera.randomstory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WordsInFiles {
    public static final String FILE_1 = "src/main/resources/brief1.txt";
    public static final String FILE_2 = "src/main/resources/brief2.txt";
    public static final String FILE_3 = "src/main/resources/brief3.txt";
    public static final String FILES_SOURCE_DIR = "src/main/resources/briefs";
    private HashMap<String, ArrayList<String>> wordsInFilesMap;

    public WordsInFiles() {
        wordsInFilesMap = new HashMap<>();
    }

    private void addWordsFromFile(File file) {
        try {
            File myFile = new File(String.valueOf(file));
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNext() == true) {
                String word = scanner.next();
                if (!wordsInFilesMap.containsKey(word)) {
                    ArrayList<String> fileNameList = new ArrayList<>();
                    fileNameList.add(file.getName());
                    wordsInFilesMap.put(word, fileNameList);
                } else {
                    if (!wordsInFilesMap.get(word).contains(file.getName())) {
                        wordsInFilesMap.get(word).add(file.getName());
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("file was not found!");
        }
    }

    public void buildWordFileMap() {
        wordsInFilesMap.clear();
        ArrayList<String> filesList = getFilesFromFolderList(new File(FILES_SOURCE_DIR));
        for (String filePath : filesList) {
            addWordsFromFile(new File(filePath));
        }
        System.out.println("MAx value is: " + maxNumber());
    }

    private ArrayList<String> getFilesFromFolderList(final File folder) {
        ArrayList<String> fileEntryList = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                getFilesFromFolderList(fileEntry);
            } else {
                fileEntryList.add(fileEntry.getPath());
            }
        }
        return fileEntryList;
    }

    private int maxNumber() {
        int maxVal = 0;
        for (ArrayList<String> files : wordsInFilesMap.values()) {
            if (maxVal < files.size()) {
                maxVal = files.size();
            }
        }
        return maxVal;
    }

    public ArrayList<String> wordsInNumFiles(int numberOfFiles) {
        ArrayList<String> wordsFromFiles = new ArrayList<>();
        buildWordFileMap();

        for (String word : wordsInFilesMap.keySet()) {
            if (wordsInFilesMap.get(word).size() == numberOfFiles) {
                wordsFromFiles.add(word);
            }
        }
        return wordsFromFiles;
    }

    public void printFilesIn(String word) {
        for (String wordKey : wordsInFilesMap.keySet()) {
            if (wordKey.equalsIgnoreCase(word)) {
                ArrayList<String> filesNameList;
                filesNameList = wordsInFilesMap.get(wordKey);
                for (String fileName : filesNameList) {
                    System.out.println(fileName);
                }
            }
        }
    }

    public void tester() {
//        buildWordFileMap();
        System.out.println("maximum repeated words");
        System.out.println("-----------------------");
        for (String word : wordsInNumFiles(3)) {
            System.out.println(word);
        }
        System.out.println("The word 'cats'");
        System.out.println("---------------");
        printFilesIn("cats");

        System.out.println("The word 'and'");
        System.out.println("---------------");
        printFilesIn("and");

    }

    public static void main(String[] args) {
        WordsInFiles wordsInFiles = new WordsInFiles();
        wordsInFiles.tester();
//        wordsInFiles.wordsInNumFiles(2);
//        wordsInFiles.printFilesIn("cats");
    }
}
