package com.coursera.weblog;
/**
 * Write a description of class LogAnalyzer here.
 *
 * @author (ILan Miller)
 * @version (0.1 10.05)
 */

import java.security.PublicKey;
import java.util.*;

import com.coursera.edu.duke.*;
import org.omg.CORBA.INTERNAL;

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

    public ArrayList<String> iPVisitsOnDay(String someday) {
        ArrayList<String> uniqueIPs = new ArrayList<>();
        for (LogEntry logEntry : records) {
            String entryTime = logEntry.getAccessTime().toString();
            if (entryTime.contains(someday)) {
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

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> countsMap = new HashMap<>();
        for (LogEntry logEntry : records) {
            String ip = logEntry.getIpAddress();
            if (countsMap.containsKey(ip)) {
                countsMap.put(ip, countsMap.get(ip) + 1);
            } else {
                countsMap.put(ip, 1);
            }
        }
        return countsMap;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> countsMap) {
        int maxNumOfVisits = 0;
        for (String ip : countsMap.keySet()) {
            if (countsMap.get(ip) > maxNumOfVisits) {
                maxNumOfVisits = countsMap.get(ip);
            }
        }
        return maxNumOfVisits;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> visitsPerIPMap) {
        ArrayList<String> iPsMostVisitsList = new ArrayList<>();
        int maxOfVisits = mostNumberVisitsByIP(visitsPerIPMap);
        for (String ip : visitsPerIPMap.keySet()) {
            if (visitsPerIPMap.get(ip) == maxOfVisits) {
                iPsMostVisitsList.add(ip);
            }
        }
        return iPsMostVisitsList;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> IPsForDaysMap = new HashMap<>();
        String selectedDate = "Sep 30";
        ArrayList<String> uniqueIPVOnDay = iPVisitsOnDay(selectedDate);
        IPsForDaysMap.put(selectedDate, uniqueIPVOnDay);
        return IPsForDaysMap;
    }

    public HashMap<String, ArrayList<String>> iPsForMultiDays() {
        HashMap<String, ArrayList<String>> IPsForDaysMap = new HashMap<>();
        String selectedDate = "Sep 21";
        ArrayList<String> uniqueIPVOnDay = iPVisitsOnDay(selectedDate);
        IPsForDaysMap.put(selectedDate, uniqueIPVOnDay);

        selectedDate = "Sep 23";
        uniqueIPVOnDay = iPVisitsOnDay(selectedDate);
        IPsForDaysMap.put(selectedDate, uniqueIPVOnDay);

        selectedDate = "Sep 24";
        uniqueIPVOnDay = iPVisitsOnDay(selectedDate);
        IPsForDaysMap.put(selectedDate, uniqueIPVOnDay);

        selectedDate = "Sep 25";
        uniqueIPVOnDay = iPVisitsOnDay(selectedDate);
        IPsForDaysMap.put(selectedDate, uniqueIPVOnDay);

        selectedDate = "Sep 26";
        uniqueIPVOnDay = iPVisitsOnDay(selectedDate);
        IPsForDaysMap.put(selectedDate, uniqueIPVOnDay);

        selectedDate = "Sep 27";
        uniqueIPVOnDay = iPVisitsOnDay(selectedDate);
        IPsForDaysMap.put(selectedDate, uniqueIPVOnDay);

        selectedDate = "Sep 28";
        uniqueIPVOnDay = iPVisitsOnDay(selectedDate);
        IPsForDaysMap.put(selectedDate, uniqueIPVOnDay);

        selectedDate = "Sep 29";
        uniqueIPVOnDay = iPVisitsOnDay(selectedDate);
        IPsForDaysMap.put(selectedDate, uniqueIPVOnDay);

        selectedDate = "Sep 30";
        uniqueIPVOnDay = iPVisitsOnDay(selectedDate);
        IPsForDaysMap.put(selectedDate, uniqueIPVOnDay);

        return IPsForDaysMap;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsByDatesMap) {
        String mostVisitedDate = null;
        int numOfVisits = 0;
        for (String day : iPsByDatesMap.keySet()) {
            if (numOfVisits < iPsByDatesMap.get(day).size()) {
                numOfVisits = iPsByDatesMap.get(day).size();
                mostVisitedDate = day;
            }
        }
        return mostVisitedDate;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsByDatesMap, String selectedDate) {
        ArrayList<String> ipAddressesList = new ArrayList<>();

        HashMap<String, Integer> IPSummeryMap = new HashMap<>();
        for (String day : iPsByDatesMap.keySet()) {
            if (day == selectedDate) {
                int ipsListSize = iPsByDatesMap.get(day).size();
                for (int i = 0; i < ipsListSize; i++) {
                    if (!IPSummeryMap.containsKey(iPsByDatesMap.get(selectedDate).get(i))) {
                        IPSummeryMap.put(iPsByDatesMap.get(selectedDate).get(i), 1);
                    } else {
                        IPSummeryMap.put(iPsByDatesMap.get(selectedDate).get(i), IPSummeryMap.get(iPsByDatesMap.get(selectedDate).get(i)) + 1);
                    }
                }
                // count max
                int mostVisitsOnDay = mostNumberVisitsByIP(IPSummeryMap);
                for (String ip : IPSummeryMap.keySet()) {
                    if (IPSummeryMap.get(ip) == mostVisitsOnDay) {
                        ipAddressesList.add(ip);
                    }
                }
            }
        }
        return ipAddressesList;
    }


}
