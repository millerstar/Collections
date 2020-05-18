package com.coursera.weblog;
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.lang.reflect.Array;
import java.util.*;

public class Tester {
    //    public static final String FILE_PATH = "src/main/resources/weblog-short_log.txt";
    public static final String FILE_PATH = "src/main/resources/weblog2_log.txt";

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

    public void testCountUniqueIPsInRange(int low, int high) {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(FILE_PATH);
        logAnalyzer.countUniqueIPsInRange(low, high);
    }

    public void testCountVisitsPerIP() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(FILE_PATH);
        HashMap<String, Integer> count = logAnalyzer.countVisitsPerIP();
        System.out.println(count);

    }

    public HashMap<String, Integer> getVisitsMap() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(FILE_PATH);
        HashMap<String, Integer> count = logAnalyzer.countVisitsPerIP();
        return count;
    }

    public int testMostNumberVisitsByIP(HashMap<String, Integer> visitsMap) {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        return logAnalyzer.mostNumberVisitsByIP(visitsMap);
    }

    public void testIPsMostVisits() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(FILE_PATH);
        HashMap<String, Integer> visitorsMap = logAnalyzer.countVisitsPerIP();
        ArrayList<String> mostVisitedIPs = logAnalyzer.iPsMostVisits(visitorsMap);
        for (String ip : mostVisitedIPs) {
            System.out.println(ip);
        }
    }

    public void testIPsForDays() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(FILE_PATH);
        logAnalyzer.iPsForDays();

    }

    public void testIPsForMultiDays() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(FILE_PATH);
        logAnalyzer.iPsForMultiDays();
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(FILE_PATH);
        HashMap<String, ArrayList<String>> ipsPreDayMap = logAnalyzer.iPsForMultiDays();
        String maxVisitsOnDay = logAnalyzer.dayWithMostIPVisits(ipsPreDayMap);
        System.out.println("The day most visited is: " + maxVisitsOnDay);

    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile(FILE_PATH);
        HashMap<String, ArrayList<String>> ipsPreDayMap = logAnalyzer.iPsForMultiDays();
        String testDate = "Sep 30";

        ArrayList<String> iPsList = logAnalyzer.iPsWithMostVisitsOnDay(ipsPreDayMap,testDate);

    }

    public static void main(String[] args) {
        Tester tester = new Tester();
//        tester.testLogAnalyzer();
//        tester.testUniqueIP();
//        tester.testRecordNumberByErrorCode(400);
//        tester.testUniqueIPVisitsOnDay("Sep 27");
//        tester.testCountUniqueIPsInRange(400, 499);

//        System.out.println("Maximum visits in the site is: " + tester.testMostNumberVisitsByIP(tester.getVisitsMap()));
//        tester.testIPsMostVisits();
//        tester.testIPsForDays();
//        tester.testDayWithMostIPVisits();
        tester.testIPsWithMostVisitsOnDay();
    }
}
