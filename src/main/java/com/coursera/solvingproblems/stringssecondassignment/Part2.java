package com.coursera.solvingproblems.stringssecondassignment;

public class Part2 {
    public int howMany(String streingA, String stringB) {
        int startIndex = stringB.indexOf(streingA);
        int counter = 0;
        if (startIndex == -1) {
            return 0;
        } else {
            ++counter;
            while (startIndex != -1) {
                startIndex = stringB.indexOf(streingA, startIndex + streingA.length());
                if (startIndex != -1) {
                    ++counter;
                }
            }
        }
        return counter;
    }

    public void testHowMany() {
        System.out.println(howMany("AA", "ATTAATGYYAOPOI"));
        System.out.println(howMany("AA", "ATQAJTGYYAZPOI"));
        System.out.println(howMany("NETA", "AMORNETAORITIRISNETAROTEMNETAMIANEHAMA"));
    }

    public static void main(String[] args) {
        Part2 part2 = new Part2();
        part2.testHowMany();
    }
}
