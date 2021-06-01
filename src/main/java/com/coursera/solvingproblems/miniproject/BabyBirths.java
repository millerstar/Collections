package com.coursera.solvingproblems.miniproject;

import com.coursera.edu.duke.DirectoryResource;
import com.coursera.edu.duke.FileResource;
import com.coursera.org.apache.commons.csv.CSVRecord;

import java.io.File;

public class BabyBirths {
    public void printNames() {
        FileResource fileResource = new FileResource();
        for (CSVRecord rec : fileResource.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name: " + rec.get(0) +
                        "Gender: " + rec.get(1) + " " +
                        "Num Born: " + rec.get(2));
            }
        }
    }

    public void totalBirths(FileResource fileResource) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fileResource.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equalsIgnoreCase("M")) {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Total girls = " + totalGirls);
        System.out.println("Total boys = " + totalBoys);
    }

    public int getRank(int year, String name, String gender) {
        int rank = -1;
        int totalBirths = 9999999;
        int counter = 0;
        String fileName = "yob" + String.valueOf(year) + "short.csv";
        FileResource fileResource = new FileResource(fileName);
        for (CSVRecord rec : fileResource.getCSVParser(false)) {
            if (rec.get(1).equalsIgnoreCase(gender)) {
                if (Integer.parseInt(rec.get(2)) <= totalBirths) {
                    totalBirths = Integer.parseInt(rec.get(2));
                    counter++;
                }
                if (rec.get(0).equalsIgnoreCase(name)) {
                    rank = counter;
                    break;
                }
            }
        }
        return rank;
    }

    public int getRank(FileResource fileResource, String name, String gender) {
        int rank = -1;
        int totalBirths = 9999999;
        int counter = 0;
//        String fileName = "yob" + String.valueOf(year) + "short.csv";
//        FileResource fileResource = new FileResource(fileName);
        for (CSVRecord rec : fileResource.getCSVParser(false)) {
            if (rec.get(1).equalsIgnoreCase(gender)) {
                if (Integer.parseInt(rec.get(2)) <= totalBirths) {
                    totalBirths = Integer.parseInt(rec.get(2));
                    counter++;
                }
                if (rec.get(0).equalsIgnoreCase(name)) {
                    rank = counter;
                    break;
                }
            }
        }
        return rank;
    }

    public String getName(int rank, int year, String gender) {
        String name = "NO NAME";
        int counter = 0;
        int totalBirths = 9999999;
        String fileName = "yob" + String.valueOf(year) + "short.csv";
        FileResource fileResource = new FileResource(fileName);
//        FileResource fileResource = new FileResource();
        for (CSVRecord rec : fileResource.getCSVParser(false)) {
            if (rec.get(1).equalsIgnoreCase(gender)) {
                if (Integer.parseInt(rec.get(2)) <= totalBirths) {
                    totalBirths = Integer.parseInt(rec.get(2));
                    ++counter;
                    if (counter == rank) {
                        name = rec.get(0);
                        break;
                    }
                }
            }
        }
        return name;
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(rank, newYear, gender);
        System.out.println(name + " born in " + year + ", would be " + newName + " if she was born in " + newYear);
    }

    public int yearOfHighestRank(String name, String gender) {
        int year = -1;
        int rank = 0;
        int bestRank = 9999999;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fileResource = new FileResource(f);
            rank = getRank(fileResource, name, gender);
            if (rank < bestRank) {
                bestRank = rank;
                String fileYear = f.getName().substring(3, 7);
                year = Integer.parseInt(fileYear);
            }
        }
        return year;
    }

    public double getAverageRank(String name, String gender) {
        double avgRank = -1.0;
        int counter = 0;
        double summary = 0;
        int rank = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fileResource = new FileResource(f);
            rank = getRank(fileResource, name, gender);
            if (rank > 0) {
                summary += rank;
                counter++;
            }
        }
        if (summary > 0) {
            avgRank = summary / counter;
        }
        return avgRank;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int total = 0;
        int counter = 1;
        String fileName = "yob" + String.valueOf(year) + "short.csv";
        FileResource fileResource = new FileResource(fileName);
        int rank = getRank(fileResource, name, gender);
        for (CSVRecord rec : fileResource.getCSVParser(false)) {
            if (rec.get(1).equalsIgnoreCase(gender)) {
                if (counter < rank) {
                    total += Integer.parseInt(rec.get(2));
                    counter++;
                }
            }
        }
        return total;
    }


    public void testTotalBirths() {
        FileResource fileResource = new FileResource();
        totalBirths(fileResource);
    }

    public void testGetRank() {
        System.out.println("The ranks is: " + getRank(1971, "Frank", "M"));
    }

    public void testGetName() {
        System.out.println("The name is: " + getName(450, 1982, "M"));
    }

    public void testYearOfHighestRank() {
        System.out.println("Year: " + yearOfHighestRank("Mich", "M"));
    }

    public void testGetAverageRank() {
        System.out.println("The average rank is: " + getAverageRank("Robert", "M"));
    }

    public void testGetTotalBirthsRankedHigher() {
        System.out.println("The total births ranked higher is: " + getTotalBirthsRankedHigher(1990, "Drew", "M"));
    }

    public static void main(String[] args) {
        BabyBirths babyBirths = new BabyBirths();
        babyBirths.testTotalBirths();
//        babyBirths.testGetRank();
//        babyBirths.testGetName();
//        babyBirths.whatIsNameInYear("Owen", 1974, 2014, "M");
//        babyBirths.testYearOfHighestRank();
//        babyBirths.testGetAverageRank();
//        babyBirths.testGetTotalBirthsRankedHigher();

    }
}
