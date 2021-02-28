package com.coursera.solvingproblems;

import com.coursera.edu.duke.StorageResource;

public class AllCodones {

    public int fineStopCodon(String dnaStr, int startIndex, String stopCodon) {
        int curIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        while (curIndex != -1) {
            int diff = curIndex - startIndex;
            if (diff % 3 == 0) {
                return curIndex;
            } else {
                curIndex = dnaStr.indexOf(stopCodon, curIndex + 1);
            }
        }
        return -1;
    }

    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return null;
        }
        int taaIndex = fineStopCodon(dna, startIndex, "TAA");
        int tagIndex = fineStopCodon(dna, startIndex, "TAG");
        int tgaIndex = fineStopCodon(dna,startIndex,"TGA");

//        int temp = Math.min(taaIndex, tagIndex);
//        int minIndex = Math.min(taaIndex, Math.min(tagIndex,tgaIndex));
        int minIndex = 0;
        if (taaIndex == -1 || (tgaIndex != -1  && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
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
        StorageResource geneList = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene == null) {
                break;
            }
            geneList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return geneList;
    }

    public void testFindStop() {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = fineStopCodon(dna, 0, "TAA");
        if (dex != 9) {
            System.out.println("error on index 9");
        }
    }

    public void TestFindGene() {
        String dna = "ATGCCCGGGAAATAACCC";
        String gene = findGene(dna, 0);
        if (! gene.equals("ATGCCCGGGAAATAA")) {
            System.out.println("Error!");
        }else {
            System.out.println("Pass");
        }
    }

    public void testOn(String dna) {
        System.out.println("Testing printAllGenes on '" + dna + "'");
        StorageResource genes = getAllGenes(dna);
        for (String g:genes.data()) {
            System.out.println(g);
        }
    }

    public void test() {
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
    }


    public static void main(String[] args) {
        AllCodones allCodones = new AllCodones();
//        allCodones.testFindStop();
//        allCodones.TestFindGene();
        allCodones.test();
    }
}
