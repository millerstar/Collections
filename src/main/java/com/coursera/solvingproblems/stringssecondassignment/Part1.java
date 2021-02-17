package com.coursera.solvingproblems.stringssecondassignment;

public class Part1 {
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

    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return null;
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
            return null;
        }
        return dna.substring(startIndex, minIndex + 3);
    }

    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene == null) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }

    public int countGenes(String dna) {
        int numOfGenes = 0;
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene == null) {
                break;
            }
            numOfGenes++;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return numOfGenes;
    }


    public void testFindStopCodon(String dna, String stopCodon) {
        System.out.println(findStopCodon(dna, 0, stopCodon));
    }

    public void testCountGenes() {
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    }

    public void testFindGene() {
        System.out.println(findGene("ATQCCCGGGAAATAACCC", 0)); // no ATG
        System.out.println(findGene("ATGCCCGGGAAATAACCC", 0)); // one valid stop codon
        System.out.println(findGene("ATGCCCCTGAGGGAAATAACCCTAGWEFRTGTGA", 0)); //DNA with “ATG” and multiple valid stop codons
    }


    public static void main(String[] args) {
        Part1 part1 = new Part1();
//        part1.testFindStopCodon("ATGCCCGGGAAATAACCC", "AAA");
//        part1.testFindGene();
        part1.printAllGenes("AATGCTAACTAGCTGACTAAT");
        part1.testCountGenes();
    }

}
