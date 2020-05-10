package com.coursera.weblog;
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.text.ParseException;
import java.util.*;

public class Tester {
//    public static final String FILE_PATH = "src/main/resources/weblog-short_log.txt";
    public static final String FILE_PATH = "src/main/resources/weblog1_log.txt";

    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(FILE_PATH);
        logAnalyzer.printAll();
    }

    public void testUniqueIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(FILE_PATH);
        System.out.println("There are: " + logAnalyzer.countUniqueIPs() + " unique IP's");
    }

    public void testRecordNumberByErrorCode(int errorNumber) {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(FILE_PATH);
        logAnalyzer.printAllHigherThanNum(errorNumber);
    }

    public void testUniqueIPVisitsOnDay(String selectedDate) {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(FILE_PATH);
        logAnalyzer.uniqueIPVisitsOnDay(selectedDate);
    }

    public void testCountUniqueIPsInRange (int low, int high) {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(FILE_PATH);
        logAnalyzer.countUniqueIPsInRange(low, high);
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
//        tester.testLogAnalyzer();
//        tester.testUniqueIP();
//        tester.testRecordNumberByErrorCode(400);
//        tester.testUniqueIPVisitsOnDay("Mar 17");
        tester.testCountUniqueIPsInRange(300, 399);
    }
}
