package com.coursera.caesarcipher;

import com.coursera.utils.FileManager;

import java.io.IOException;

public class CaesarCipherOneTest {
    private static final String FILE_PATH = "src/main/resources/caesar_cipher_file.txt";

    public static void main(String[] args) throws IOException {

        String fileData = FileManager.getFileString(FILE_PATH);
        CaesarCipherOne cc1 = new CaesarCipherOne(17);

        String enText = cc1.encrypt(fileData);


        System.out.println(enText);
        System.out.println(" --------------------------------- ");
        System.out.println(cc1.decrypt(enText));
    }
}
