package com.udemy;

public class Utils {

    public static int[] getArray() {
        int[] vec;
        vec = new int[6];
        for (int i = 0; i < vec.length; i++) {
            vec[i] = 10 * (i + 1);
        }
        return vec;
    }

    public static int[] getArray2() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    public static void modifyArray(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = 10 * numbers[i];
        }
    }

    public static int total(int[] numbers) {
        int total = 0;
        for (int i = 0; i < numbers.length; i++) {
            total += numbers[i];
        }
        return total;
    }

    public static int findTheBiggestNumber(int[] numbers) {
        int result = 0;
        for (int num : numbers) {
            if (num > result) {
                result = num;
            }
        }
        return result;
    }

    public static int findTheSmallestNumber(int[] numbers) {
        int results = numbers[0];
        for (int num : numbers) {
            if (results > num) {
                results = num;
            }
        }
        return results;
    }

    public static int multipleNumbers(int[] numbers) {
        int result = 1;
        for (int num : numbers) {
            result *= num;
        }
        return result;
    }

    public static String getTheLongest(String[] textArray) {
        String results = textArray[0];
        int maxIndex = 0;
        for (String str : textArray) {
            if (str.length() > results.length()) {
                results = str;
            }
        }
        return results;
    }

    public static String getTheShortest(String[] textArray) {
        String results = textArray[0];
        int maxIndex = 0;
        for (String str : textArray) {
            if (str.length() > results.length()) {
                results = str;
            }
        }
        return results;
    }


}
