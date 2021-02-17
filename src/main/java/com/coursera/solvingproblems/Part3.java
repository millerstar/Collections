package com.coursera.solvingproblems;

public class Part3 {

    public boolean twoOccurrences(String stringA, String stringB) {
        int counter = 0;
        int index = -1;
        index = stringB.indexOf(stringA);
        if (index < 0) {
            System.out.println("string a '" + stringA + "' is not appears in string b '" + stringB + "'.");
            return false;
        } else {
            counter++;
            index = stringB.indexOf(stringA, index + 1);
            if (index > -1 ){
                counter++;
            }
        }
        if (counter >=2 ){
            return true;
        } else {
            return false;
        }
    }

    public String lastPart(String stringA, String stringB) {
        String finalString = null;
        int index = stringB.indexOf(stringA);
        if (index == -1) {
            return stringB;
        }
        return stringB.substring(index + stringA.length(), stringB.length());
    }

    public void testing() {
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));

        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("nar", "leonardo"));
        System.out.println(lastPart("zoo", "forest"));

    }

    public static void main(String[] args) {
        Part3 part3 = new Part3();
        part3.testing();
    }
}
