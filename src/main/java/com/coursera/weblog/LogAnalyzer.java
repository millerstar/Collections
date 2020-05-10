package com.coursera.weblog;
/**
 * Write a description of class LogAnalyzer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

import com.coursera.edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    ArrayList<String> uniqueIPs;

    public LogAnalyzer() {
        // complete constructor
        records = new ArrayList<>();
        uniqueIPs = new ArrayList<>();
        records.clear();
        uniqueIPs.clear();
    }

    public void readFile(String filename) {
        // complete method
        FileResource fileResource = new FileResource(filename);
        for (String line : fileResource.lines()) {
            LogEntry logEntry = WebLogParser.parseEntry(line);
            records.add(logEntry);
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        for (LogEntry logEntry : records) {
            String ip = logEntry.getIpAddress();
            if (!uniqueIPs.contains(ip)) {
                uniqueIPs.add(ip);
            }
        }
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int errorNum) {
        for (LogEntry logEntry : records) {
            int statusCode = logEntry.getStatusCode();
            if (statusCode > errorNum) {
                System.out.println(logEntry);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for (LogEntry logEntry : records) {
            String entryTime = logEntry.getAccessTime().toString();
            if (entryTime.contains(someday) && !uniqueIPs.contains(logEntry.getIpAddress())) {
                uniqueIPs.add(logEntry.getIpAddress());
            }
        }
        return uniqueIPs;
    }

    public int countUniqueIPsInRange(int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for (LogEntry logEntry : records) {
            int statusCode = logEntry.getStatusCode();
            if (statusCode >= low && statusCode <= high && !uniqueIPs.contains(logEntry.getIpAddress())) {
                uniqueIPs.add(logEntry.getIpAddress());
            }
        }
        return uniqueIPs.size();
    }


}
