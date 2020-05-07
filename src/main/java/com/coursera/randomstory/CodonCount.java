package com.coursera.randomstory;


import com.coursera.edu.duke.FileResource;

import java.util.HashMap;

public class CodonCount {
//    public static final String FILE_PATH = "src/main/resources/smalldna.txt";
    public static final String FILE_PATH = "src/main/resources/dnaMystery2.txt";
    private HashMap<String, Integer> dnaCodonsMap;
    private int maxVal = 0;

    public CodonCount() {
        this.dnaCodonsMap = new HashMap<>();
    }

    public void buildCodonMap(int start, String dna) {
        dnaCodonsMap.clear();
        for (int i = start; i < dna.length() - 2; i += 3) {
            String codon = dna.substring(i, i + 3).toUpperCase();
            if (dnaCodonsMap.containsKey(codon)) {
                dnaCodonsMap.put(codon, dnaCodonsMap.get(codon) + 1);
            } else {
                dnaCodonsMap.put(codon, 1);
            }
        }
    }

    public String getMostCommonCodon() {
        StringBuilder mostCommon = new StringBuilder();
        maxVal = 0;
//        int maximum = 0;
        for (String s : dnaCodonsMap.keySet()) {
            if (dnaCodonsMap.get(s) > maxVal) {
                maxVal = dnaCodonsMap.get(s);
            }
        }

        for (String s : dnaCodonsMap.keySet()) {
            if (dnaCodonsMap.get(s) == maxVal) {
                mostCommon.append(s + " ");
            }
        }
        return mostCommon.toString().trim();
    }

    public void printCodonCounts(int start, int end) {
        for (String s : dnaCodonsMap.keySet()) {
            if (dnaCodonsMap.get(s) >= start && dnaCodonsMap.get(s) <= end) {
                System.out.println(s + "\t" + dnaCodonsMap.get(s));
            }
        }
    }

    public void test(String dnaFile) {
        FileResource fileResource = new FileResource(dnaFile);
        for (String s : fileResource.words()) {
            buildCodonMap(0, s);
        }
        System.out.println("Reading frame starting with 0, results in " + dnaCodonsMap.size() + " unique codons.");
        System.out.println("and most common codon is " + getMostCommonCodon() + " with count " + maxVal);
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts(1, 300);

        System.out.println("-----------------------------------------------------------------");

        for (String s : fileResource.words()) {
            buildCodonMap(1, s);
        }
        System.out.println("Reading frame starting with 1, results in " + dnaCodonsMap.size() + " unique codons.");
        System.out.println("and most common codon is " + getMostCommonCodon() + " with count " + maxVal);
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts(1, 300);

        System.out.println("-----------------------------------------------------------------");
        for (String s : fileResource.words()) {
            buildCodonMap(2, s);
        }
        System.out.println("Reading frame starting with 2, results in " + dnaCodonsMap.size() + " unique codons.");
        System.out.println("and most common codon is " + getMostCommonCodon() + " with count " + maxVal);
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts(1, 300);
        System.out.println("-----------------------------------------------------------------");

    }


    public static void main(String[] args) {
        CodonCount codonCount = new CodonCount();
        codonCount.test(FILE_PATH);
    }


}
