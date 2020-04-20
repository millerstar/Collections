package com.coursera.randomstory;

import com.coursera.edu.duke.FileResource;

import java.util.ArrayList;
import java.util.List;

public class CharactersInPlay {
    private List<String> charactersNames;
    private List<Integer> charactersCounts;
//    public static final String FILE_PATH = "src/main/resources/macbethSmall.txt";
    public static final String FILE_PATH = "src/main/resources/likeit.txt";

    public CharactersInPlay() {
        this.charactersNames = new ArrayList<>();
        this.charactersCounts = new ArrayList<>();
    }

    private void update(String person) {
        int index = charactersNames.indexOf(person);
        if (index == -1) {
            charactersNames.add(person);
            charactersCounts.add(1);
        } else {
            int characterCounter = charactersCounts.get(index);
            charactersCounts.set(index, characterCounter + 1);
        }
    }

    private void findAllCharacters(String stringFile) {
        FileResource fileResource = new FileResource(stringFile);
        for (String line : fileResource.lines()) {
            if (line.contains(".") && line.charAt(3) != ' ') {
                String speaker = line.substring(0, line.indexOf('.')).trim();
                speaker = speaker.toUpperCase();
                update(speaker);
            }
        }
    }

    public void tester(String stringFile) {
        findAllCharacters(stringFile);

        charactersWithNumParts(10,15);

    }

    private void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < charactersCounts.size(); i++) {
//            System.out.println(charactersNames.get(i) + " " + charactersCounts.get(i));
            if (charactersCounts.get(i) >= num1 && charactersCounts.get(i) <= num2) {
                System.out.println(charactersNames.get(i) + " " + charactersCounts.get(i));
            }
        }
    }

    public static void main(String[] args) {
        CharactersInPlay charactersInPlay = new CharactersInPlay();
        charactersInPlay.tester(FILE_PATH);
    }
}
