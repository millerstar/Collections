package com.coursera.solvingproblems.stringssecondassignment;

public class Part3 {

    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return -1;
    }

    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int taaStopIndex = findStopCodon(dna, startIndex, "TAA");
        int tagStopIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaStopIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex = 0;
        if (taaStopIndex == -1 || (tgaStopIndex != -1 && tgaStopIndex < taaStopIndex)) {
            minIndex = tgaStopIndex;
        } else {
            minIndex = taaStopIndex;
        }
        if (minIndex == -1 || (tagStopIndex != -1 && tagStopIndex < minIndex)) {
            minIndex = tagStopIndex;
        }
        if (minIndex == -1) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }

    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna);
            if (currentGene == null) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
}
