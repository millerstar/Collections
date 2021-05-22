package com.coursera.solvingproblems.csvdata;

import com.coursera.edu.duke.FileResource;
import com.coursera.org.apache.commons.csv.CSVParser;
import com.coursera.org.apache.commons.csv.CSVRecord;

public class DataParser {

    public String countryInfo(CSVParser parser, String country) {
        String countryInfo = "NOT FOUND";

        for (CSVRecord record : parser) {
            String countryName = record.get("Country");
            if (countryName.equals(country)) {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                countryInfo = countryName + ": " + exports + ": " + value;
                break;
            }
        }
        return countryInfo;
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                String countryName = record.get("Country");
                System.out.println(countryName);
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int exportersCounter = 0;
        for (CSVRecord record : parser) {
            String item = record.get("Exports");
            if (item.contains(exportItem)) {
                exportersCounter++;
            }
        }
        return exportersCounter;
    }

    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record: parser) {
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                String countryName = record.get("Country");
                System.out.println(countryName + " " + value);
            }
        }
    }

    public void tester() {
        FileResource fileResource = new FileResource("exportdata.csv");
        CSVParser parser = fileResource.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));

        System.out.println("----------------------------------------------------------------- ");
        parser = fileResource.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");

        System.out.println("----------------------------------------------------------------- ");
        parser = fileResource.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));

        System.out.println("----------------------------------------------------------------- ");
        parser = fileResource.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }

    public static void main(String[] args) {
        DataParser dataParser = new DataParser();
        dataParser.tester();

    }
}
