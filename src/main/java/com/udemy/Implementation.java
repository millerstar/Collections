package com.udemy;

public class Implementation {
    public static void main(String[] args) {
        int[] myIntArray = {3, 7, 8, 1, 2, 4, 3, 1};
        String[] myStringArray = {"Holland", "UK", "Japan", "Israel", "Narnia", "Gulu King of Julu"};

//        int[] ex1 = Utils.getArray();

//        int[] ex2 = Utils.getArray2();
//        printArray(ex2);

//        Utils.modifyArray(myIntArray);
//        printArray(myIntArray);

//        System.out.println(Utils.total(myIntArray));

//        System.out.println(Utils.findTheBiggestNumber(myIntArray));

//        System.out.println(Utils.findTheSmallestNumber(myIntArray));

//        System.out.println(Utils.multipleNumbers(myIntArray));

        System.out.println(Utils.getTheLongest(myStringArray));


    }

    private static void printArray(int[] intArray) {
        for (int val : intArray) {
            System.out.println(val);
        }
    }
}
