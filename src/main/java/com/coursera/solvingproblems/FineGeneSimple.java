package com.coursera.solvingproblems;

import com.coursera.edu.duke.DirectoryResource;
import com.coursera.edu.duke.FileResource;

import java.io.File;

public class FineGeneSimple {
    private final String START_CODON = "ATG";
    private final String END_CODON = "TAA";

    public String findProtein(String dna) {
        String result = null;
        int startIndex = dna.indexOf(START_CODON);
        int endIndex = dna.indexOf(END_CODON, startIndex + 3);
        if (startIndex == -1 || endIndex == -1 ) {
            System.out.println("No gene was found!");
            return null;
        }
        result = dna.substring(startIndex, endIndex + 3);
        return result;
    }


    public void testing() {
//        String a = "cccatggggtttaaataataataggagagagagagagagttt";
//        String ap = "atggggtttaaataataatag";
        //String a = "atgcctag";
        //String ap = "";
        String a = "ATGCCCTAG";
        String ap = "ATGCCCTAG";
        String result = findProtein(a);
        if (ap.equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }

    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length() + " characters");
            String result = findProtein(s);
            System.out.println("found " + result);
        }
    }

    public static void main(String[] args) {
        FineGeneSimple fineGeneSimple = new FineGeneSimple();
        fineGeneSimple.testing();
    }

}
