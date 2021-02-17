package com.coursera.solvingproblems;

import com.coursera.edu.duke.URLResource;

import java.util.Locale;

public class Part4 {

    public void findWebLink(String urlSource) {

        URLResource resource = new URLResource(urlSource);
        int startIndex = -1;
        int endIndex = -1;
        for (String word:resource.words()) {
            String temp = word.toLowerCase(Locale.ROOT);
            if (temp.contains("youtube.com")) {
                startIndex = word.indexOf("\"");
                endIndex = word.indexOf("\"", startIndex + 1);
                System.out.println(word.substring(startIndex + 1, endIndex));
            }
        }
    }

    public static void main(String[] args) {
        Part4 part4 = new Part4();
        part4.findWebLink("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    }
}
