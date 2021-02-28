package com.coursera.solvingproblems.stringsthirdassignments;

import com.coursera.edu.duke.FileResource;
import com.coursera.edu.duke.StorageResource;

import java.util.Locale;

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

    public StorageResource getAllGenes(String dna) {
        StorageResource geneStorage = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene == null) {
                break;
            }
            geneStorage.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneStorage;
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

    public double cgRatio(String dna) {
        int counter = 0;
        for (char c : dna.toCharArray()) {
            if ((c == 'C') || (c == 'G')) {
                counter++;
            }
        }
        int strLen = dna.length();
        return (double) counter / strLen;
    }

    public int countCTG(String dna) {
        int counter = 0;
        int startIndex = dna.indexOf("CTG");
        if (startIndex == -1) {
            return counter;
        }
        counter++;
        while (startIndex != -1) {
            startIndex = dna.indexOf("CTG", startIndex + 3);
            if (startIndex != -1) {
                counter++;
            }
        }
        return counter;
    }

    public void processGenes(StorageResource storageResource) {
        int longerThenNineCharsCounter = 0;
        int cgRatioCounter = 0;
        int longestGene = 0;
        int sixtyCharLenCounter = 0;
        int geneCounter = 0;
        String longestGeneStr;

        for (String s : storageResource.data()) {
            geneCounter++;
            if (s.length() > 9) {
                System.out.println("string longer then 9 chars");
                System.out.println(s);
                longerThenNineCharsCounter++;
            }
            double getCgRatio = cgRatio(s);
            if (getCgRatio > 0.35) {
                System.out.println("C-G-ratio is higher than 0.35");
                System.out.println(s);
                cgRatioCounter++;
            }
            if (s.length() > longestGene) {
                longestGene = s.length();
                longestGeneStr = s;
            }
            if (s.length() > 60) {
                System.out.println("string longer then 60 chars");
                sixtyCharLenCounter++;
            }
        }
        System.out.println("The length of the longest gene is: " + longestGene);
        System.out.println("The sixty char length string counter is: " + sixtyCharLenCounter);
        System.out.println("Total Genes counter: " + geneCounter);
    }

    public void testProcessGenes() {
        StorageResource st = new StorageResource();
        FileResource fileResource = new FileResource("GRch38dnapart.fa");
        String dna = fileResource.asString().toUpperCase(Locale.ROOT);
//        st.add("ATGCCCCTGAGGGAAATAACCCTAGWEFRTGTGA"); //some genes longer than 9 characters
//        st.add("ATGCTGA"); // one DNA string that has no genes longer than 9 characters
//        st.add("ATGCCATAG"); // whose C-G-ratio is higher than 0.35
//        st.add("ATGPPATAG"); // whose C-G-ratio is lower than 0.35
//        String startIndex = dna.indexOf()
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene == null) {
                break;
            }
            st.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        processGenes(st);
        System.out.println(" ---------------------- ");
        System.out.println(countCTG(dna));
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

    public void testOn(String dna) {
        System.out.println("Testing printAllGenes on '" + dna + "'");
        StorageResource genes = getAllGenes(dna);
        for (String g : genes.data()) {
            System.out.println(g);
        }
    }

    public void test() {
        testOn("ATGTAAGATGCCCTAGTATGATCTAATTTATGCTGCAACGGTGAAGA");
    }


    public static void main(String[] args) {
        Part1 part1 = new Part1();
//       part1.test();
//        System.out.println(part1.cgRatio("ATGCCATAG"));
//        System.out.println(part1.countCTG("DCCTGPPUHCTGNRUDSICTGCTG"));
        part1.testProcessGenes();
    }
}
