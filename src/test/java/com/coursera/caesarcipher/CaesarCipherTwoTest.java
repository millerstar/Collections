package com.coursera.caesarcipher;

import com.coursera.utils.FileManager;

import java.io.IOException;

public class CaesarCipherTwoTest {

    private static final String FILE_PATH = "src/main/resources/world_play_file.txt";

    public static void main(String[] args) throws IOException {

        CaesarCipherTwo cc2 = new CaesarCipherTwo(5, 15);
        String stringFileText = FileManager.getFileString(FILE_PATH);

        System.out.println(stringFileText);
        System.out.println("----------------------------------");
        String encryptedText = cc2.encrypt(stringFileText);
        System.out.println(encryptedText);
        System.out.println("----------------------------------");
        System.out.println(cc2.decrypt(encryptedText));


//        CaesarCipher cc = new CaesarCipher();
//        System.out.println(cc.encryptTwoKeys("Mary Bella Abracadabra", 5, 15));

    }
}
