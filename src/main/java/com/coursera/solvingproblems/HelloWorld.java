package com.coursera.solvingproblems;

import com.coursera.edu.duke.FileResource;

public class HelloWorld {
    public static void helloWorld() {
        FileResource fileResource = new FileResource("hello_unicode.txt");
        for (String line : fileResource.lines()) {
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        helloWorld();
    }
}
