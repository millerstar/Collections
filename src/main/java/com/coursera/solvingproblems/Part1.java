package com.coursera.solvingproblems;

public class Part1 {
    private final String START_CODON = "atg";
    private final String END_CODON = "taa";

    public String findSimpleGene (String dna) {
        String simpleGene = null;
        int startIndex = dna.indexOf(START_CODON);
        int endIndex = dna.indexOf(END_CODON, startIndex + 3);
        if (startIndex == -1 || endIndex == -1) {
            System.out.println("No start or end codon found for DNA: " + dna);
            return null;
        }
        simpleGene = dna.substring(startIndex, endIndex + 3);
        if (simpleGene.length() % 3 != 0) {
            System.out.println("Invalid number of genes found for DNA: " + dna);
            return null;
        }else {
            System.out.println("DNA was found: " + dna);
        }
        return simpleGene;
    }

    public void testSimpleGene() {
        String dna1 = "cccatggggtttaaataataataggagagagagagagagttt";
        String dna2 = "cccattggggtttaaataataataggagagagagagagagttt";
        String dna3 = "cccatggggtttapaateatayataggagagagagagagagttt";
        String dna4 = "atggggttgtaa";
        String dna5 = "atggggtttaaataataatag";
        String dna6 = "AAATGCCCTAACTAGATTAAGAAACC";
        findSimpleGene(dna6);
    }

    public static void main(String[] args) {
        Part1 part1 = new Part1();

        part1.testSimpleGene();

    }
}
