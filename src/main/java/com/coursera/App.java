package com.coursera;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ArrayList<String> listOne = new ArrayList<>(Arrays.asList("Ilan", "Orit", "Yaron"));
        ArrayList<String> listTwo = new ArrayList<>(Arrays.asList("Iris", "Rotem", "Eithan", "Saar"));

        System.out.println(listOne);
        listOne.addAll(listTwo);
        System.out.println("================");
        System.out.println(listOne);

    }
}
