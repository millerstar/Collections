package com.coursera.solvingproblems;

public class Part2 {
    private final String START_CODON = "atg";
    private final String END_CODON = "taa";

    public String findSimpleGene(String dna, String startCodon, String endCodon) {
        String simpleGene = null;
        boolean isUpperCase = isUpperCase(dna.charAt(0));
        int startIndex;
        int endIndex;
        if (isUpperCase) {
            startIndex = dna.indexOf(startCodon.toUpperCase());
            endIndex = dna.indexOf(endCodon.toUpperCase(), startIndex + 3);
        }else {
            startIndex = dna.indexOf(startCodon.toLowerCase());
            endIndex = dna.indexOf(endCodon.toLowerCase(), startIndex + 3);
        }

        if (startIndex == -1 || endIndex == -1) {
            System.out.println("No start or end codon found for DNA: " + dna);
            return null;
        }
        simpleGene = dna.substring(startIndex, endIndex + 3);
        if (simpleGene.length() % 3 != 0) {
            System.out.println("Invalid number of genes found for DNA: " + dna);
            return null;
        } else {
            System.out.println("DNA was found: " + simpleGene);
        }
        return simpleGene;
    }

    private boolean isUpperCase(char c) {
        boolean isUpper = false;
        if (Character.isUpperCase(c)) {
            isUpper = true;
        }
        return  isUpper;
    }

    public void testSimpleGene() {
        String dna1 = "cccatggggtttaaataataataggagagagagagagagttt";
        String dna2 = "cccattggggtttaaataataataggagagagagagagagttt";
        String dna3 = "cccatggggtttapaateatayataggagagagagagagagttt";
        String dna4 = "atggggttgtaa";
        String dna5 = "atggggtttaaataataatag";
        String dna6 = "ATGGGTTAAGTC";
        String dna7 = "gatgctataat";
        findSimpleGene(dna7, "atg", "taa");
    }

    public static void main(String[] args) {
        Part2 part2 = new Part2();
        part2.testSimpleGene();

    }
}
