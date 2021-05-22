package com.coursera.solvingproblems.csvdata;

import com.coursera.edu.duke.DirectoryResource;
import com.coursera.edu.duke.FileResource;
import com.coursera.org.apache.commons.csv.CSVFormat;
import com.coursera.org.apache.commons.csv.CSVParser;
import com.coursera.org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVMax {

    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser) {
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        return smallestSoFar;
    }

    public CSVRecord hottestInManyDays() {
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public String fileWithColdestTemperature() {
        String fileName = null;
        CSVRecord lowestSoFar = null;
        CSVRecord tempRecord = null;
        DirectoryResource dr = new DirectoryResource();
        FileResource fr = null;
        for (File f : dr.selectedFiles()) {
            fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            lowestSoFar = getSmallestOfTwo(currentRow, lowestSoFar);
            if (tempRecord != lowestSoFar) {
                tempRecord = lowestSoFar;
                fileName = f.getName();
            }
        }

        return fileName;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestRecordSoFar = null;
        int lowestHumiditySoFar = 100;
        String humidityInRecord;
        for (CSVRecord r : parser) {
            humidityInRecord = r.get("Humidity");
            if (humidityInRecord != "N/A") {
                int currentHumidity = Integer.parseInt(humidityInRecord);
                if (currentHumidity < lowestHumiditySoFar) {
                    lowestHumiditySoFar = currentHumidity;
                    lowestRecordSoFar = r;
                }
            }
        }
        return lowestRecordSoFar;
    }

    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestRecordSoFar = null;
        int lowestHumiditySoFar = 100;
        String humidityInRecord;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            for (CSVRecord r : parser) {
                humidityInRecord = r.get("Humidity");
                if (!humidityInRecord.equalsIgnoreCase("N/A")) {
                    int currentHumidity = Integer.parseInt(humidityInRecord);
                    if (currentHumidity < lowestHumiditySoFar) {
                        lowestHumiditySoFar = currentHumidity;
                        lowestRecordSoFar = r;
                    }
                }
            }
        }
        return lowestRecordSoFar;
    }

    public double averageTemperatureInFile(CSVParser parser) {
        double averageTemp = 0.0;
        int counter = 0;
        for (CSVRecord r : parser) {
            averageTemp += Double.parseDouble(r.get("TemperatureF"));
            counter++;
        }
        return averageTemp / counter;
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double averageTemp = 0.0;
        int counter = 0;
        for (CSVRecord r : parser) {
            String currentHumidity = r.get("Humidity");
            if (currentHumidity != "N/A") {
                int currentHumidityValue = Integer.parseInt(r.get("Humidity"));
                if (currentHumidityValue >= value) {
                    averageTemp += Double.parseDouble(r.get("TemperatureF"));
                    counter++;
                }
            }
        }
        if (averageTemp > 0) {
            averageTemp = averageTemp / counter;
        }
        return averageTemp;
    }

    private CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if (largestSoFar == null) {
            largestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            if (currentTemp > largestTemp) {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    private CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar) {
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        } else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            if (currentTemp > 0) {
                if (currentTemp < smallestTemp) {
                    smallestSoFar = currentRow;
                }
            }
        }
        return smallestSoFar;
    }

    public void testHottestDay() {
        FileResource fileResource = new FileResource("weather-2015-01-01.csv");
        CSVRecord largest = hottestHourInFile(fileResource.getCSVParser());
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
    }

    public void testColdestHourInFile() {
        FileResource fileResource = new FileResource();
        CSVRecord smallest = coldestHourInFile(fileResource.getCSVParser());
        System.out.println("Coldest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("TimeEST"));
    }

    public void testHottestInManyDays() {
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }

    public void testFileWithColdestTemperature() {
        String coldestDayFileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + coldestDayFileName);
        FileResource fileResource = new FileResource();
        CSVRecord smallest = coldestHourInFile(fileResource.getCSVParser());
        System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord record : fileResource.getCSVParser()) {
            System.out.println(record.get("DateUTC") + " " + record.get("TemperatureF"));
        }
    }

    public void testLowestHumidityInFile() {
//        FileResource fileResource = new FileResource("weather-2014-04-01.csv");
        FileResource fileResource = new FileResource();
        CSVRecord lowestHumidity = lowestHumidityInFile(fileResource.getCSVParser());
        System.out.println("Lowest Humidity was " + lowestHumidity.get("Humidity") + " at " + lowestHumidity.get("DateUTC"));
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidityInManyFiles = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowestHumidityInManyFiles.get("Humidity") + " at " + lowestHumidityInManyFiles.get("DateUTC"));
    }

    public void testAverageTemperatureInFile() {
//        FileResource fileResource = new FileResource("weather-2014-01-20.csv");
        FileResource fileResource = new FileResource();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(fileResource.getCSVParser()));
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
//        FileResource fileResource = new FileResource("weather-2014-03-20.csv");
        FileResource fileResource = new FileResource();
        double averageTemp = averageTemperatureWithHighHumidityInFile(fileResource.getCSVParser(), 80);
        if (averageTemp > 0) {
            System.out.println("Average Temp when high Humidity is " + averageTemp);

        }else {
            System.out.println("No temperatures with that humidity");
        }
    }

    public static void main(String[] args) {
        CSVMax csvMax = new CSVMax();
//        csvMax.testHottestDay();
//        csvMax.testHottestInManyDays();
//        csvMax.testColdestHourInFile();
        csvMax.testFileWithColdestTemperature();
//        csvMax.testLowestHumidityInFile();
//        csvMax.testLowestHumidityInManyFiles();
//        csvMax.testAverageTemperatureInFile();
//        csvMax.testAverageTemperatureWithHighHumidityInFile();
    }
}
