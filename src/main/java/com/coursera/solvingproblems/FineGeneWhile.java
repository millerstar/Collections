package com.coursera.solvingproblems;

public class FineGeneWhile {
    public String fineGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        int currentIndex = dna.indexOf("TAA", startIndex + 3);

        while (currentIndex != -1) {
            if ((currentIndex - startIndex) % 3 == 0) {
                return dna.substring(startIndex, currentIndex + 3);
            } else {
                currentIndex = dna.indexOf("TAA", currentIndex + 1);
            }
        }
        return null;
    }
}
