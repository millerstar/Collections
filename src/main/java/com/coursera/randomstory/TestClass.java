package com.coursera.randomstory;

import com.coursera.gladlib.GladLib;

public class TestClass {
    public static final String FILE_PATH = "src/main/resources/likeit.txt";

    public static void main(String[] args) {
        GladLib storyGenerator = new GladLib();

        storyGenerator.makeStory();
    }
}
