package com.algo;

import java.util.Arrays;

public class Riddles {

    public int singleNonDuplicate(int[] numArray) {
        int arrLen = numArray.length;
        int low = 0;
        int high = arrLen / 2;

        while (low < high) {
            int mid = (low + high) / 2;
            if (numArray[2 * mid] != numArray[2 * mid + 1]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return numArray[2 * low];
    }

    public int singleNonDuplicate2(int[] numArray) {
        int arrLength = numArray.length;
        int singleNum = 0;
        if (arrLength % 2 == 0) {
            System.out.println("This is an odd array, so there is no single non duplicate value");
            return singleNum;
        } else {
            for (int i = 0; i < arrLength - 1; i++) {
                if (i == 0 || i % 2 == 0) {
//                    System.out.println(numArray[i] + " " + numArray[i + 1]);
                    if (numArray[i] != numArray[i + 1]) {
                        singleNum = numArray[i];
                        break;
                    }
                }
            }
        }
        return singleNum;
    }

    public int[] sortThreeNumbers(int[] myArray) {
        int temp = 0;
        if (myArray[0] > myArray[1]) {
            temp = myArray[0];
            myArray[0] = myArray[1];
            myArray[1] = temp;
        }
        if (myArray[1] > myArray[2]) {
            temp = myArray[1];
            myArray[1] = myArray[2];
            myArray[2] = temp;
            if (myArray[0] > myArray[1]) {
                temp = myArray[0];
                myArray[0] = myArray[1];
                myArray[1] = temp;
            }
        }
        return myArray;
    }

    public void printArrayValues(int[] numArray) {
        for (int val : numArray) {
            System.out.println(val);
        }
    }

    public void isPalindromeNumber(int number) {
        int remain = 0;
        int temp = 0;
        int sum = 0;

        temp = number;
        while (temp > 0) {
            remain = temp % 10;
            sum = (sum * 10) + remain;
            temp = temp / 10;
        }
        System.out.println(sum);
    }

    public boolean isSubString(String subString, String mainString) {
        boolean isExisted = false;
        boolean isInnerCharFound = true;
        if (mainString.length() < subString.length())
            return false;
        for (int i = 0; i < mainString.length(); i++) {
            if (subString.charAt(0) == mainString.charAt(i)) {
                System.out.println("first char was found");
                if (mainString.length() - i < subString.length()) {
                    System.out.println("substring was not found");
                    return false;
                }
                for (int j = 1; j < subString.length(); j++) {
                    if (subString.charAt(j) != mainString.charAt(i + j)) {
                        isInnerCharFound = false;
                        break;
                    }
                    isInnerCharFound = true;
                }
                if (!isInnerCharFound) {
                    continue;
                }
                isExisted = true;
            }
            if (isExisted) {
                System.out.println("The substring '" + subString + "' was found in '" + mainString + "'");
                break;
            }
        }
        return isExisted;
    }

    public void multiplicationTable(int rows, int columns) {
        int currentRes;
        for (int i = 1; i <= columns; i++) {
            for (int j = 1; j <= rows; j++) {
                System.out.print(i * j + "\t");
            }
            System.out.print("\n");
        }
    }

    public void arrayProduct(int[] numArray) {
        int product = 1;
        for (int i = 0; i < numArray.length; i++) {
            product = product * numArray[i];
        }
        System.out.println("The array product is: " + product);
    }

    public int[] arrayProduct2(int[] numArray) {
        int[] left = new int[numArray.length];
        int[] right = new int[numArray.length];
        int[] res = new int[numArray.length];

        left[0] = 1;
        for (int i = 1; i < numArray.length; i++) {
            left[i] = left[i - 1] * numArray[i - 1];
        }

        right[numArray.length - 1] = 1;
        for (int i = numArray.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * numArray[i + 1];
        }

        for (int i = 0; i < numArray.length; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
    }

    public int[] arrayProduct3(int[] numArray) {
        int[] res = new int[numArray.length];
        for (int i = 0; i < numArray.length; i++) {
            int currentProd = 1;
            for (int j = 0; j < numArray.length; j++) {
                if (j == i) {
                    continue;
                } else {
                    currentProd = currentProd * numArray[j];
                }
            }
            res[i] = currentProd;
        }
        return res;
    }

    public static void printArr(int[] arr) {
        for (int e : arr) {
            System.out.printf(e + " ");
        }
    }

    public int stringLength(String myStr) {
        if (myStr.equals(""))
            return 0;
        else
            return stringLength(myStr.substring(1)) + 1;
    }

    public int getMax(int[] myArray) {
        int maxNum = myArray[0];
        for (int i = 1; i < myArray.length; i++) {
            maxNum = Math.max(maxNum, myArray[i]);
        }
        return maxNum;
    }

    public int getMin(int[] array, int index, int arrLength) {
        if (arrLength == 1) {
            return array[index];
        } else {
            return Math.min(array[index], getMin(array, index + 1, arrLength - 1));
        }
    }

    public void printTriangle(int[] numArray) {
        if (numArray.length < 1) {
            return;
        }
        int[] tempArray = new int[numArray.length - 1];
        for (int i = 0; i < numArray.length - 1; i++) {
            int tempVal = numArray[i] + numArray[i + 1];
            tempArray[i] = tempVal;
        }
        printTriangle(tempArray);
        System.out.println(Arrays.toString(numArray));
    }

    public static void main(String[] args) {
//        int[] a = {2, 2, 3, 3, 4, 5, 5, 6, 6, 7, 7};
//        int[] b = {2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 10, 10, 11, 14, 14};
//        int[] c = {20, 15, 19};
//        int[] exFive = {3, 5, 4, 2};
        Riddles riddles = new Riddles();
//        System.out.println(riddles.singleNonDuplicate(a));
//        System.out.println(riddles.singleNonDuplicate2(b));

//        riddles.printArrayValues(riddles.sortThreeNumbers(c));
//        riddles.isPalindromeNumber(52125);
//        riddles.isSubString("tn", "dont cry for me argentina");
//        riddles.multiplicationTable(5,5);
//        printArr(riddles.arrayProduct2(exFive));
//        printArr(riddles.arrayProduct3(exFive));
//        System.out.println(riddles.stringLength("Hello"));
//
//        int arr[] = {12, 1234, 45, 5, 67};
//        int arrayLength = arr.length;
//        System.out.println(riddles.getMin(arr, 0, arrayLength));
//        System.out.println(riddles.getMax(arr));

        int[] myArray = {1, 2, 3, 4, 5};
        riddles.printTriangle(myArray);


    }
}
