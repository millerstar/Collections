package com.coursera.utils;

import java.io.FileInputStream;
import java.io.IOException;

public class FileManager {

    public static String getFileString(String filePath) throws IOException {
        FileInputStream fileInputStream;
        fileInputStream = new FileInputStream(filePath);

        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((i = fileInputStream.read()) != -1) {
            char currentChar = (char) i;
            stringBuilder.append(currentChar);
        }
        return stringBuilder.toString();
    }
}
