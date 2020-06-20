package com.coursera.vigenerecipher;

import com.coursera.edu.duke.FileResource;

public class Tester {
    //        public static final String FILE_PATH = "src/main/resources/titus-small_key5.txt";
//    public static final String FILE_PATH = "src/main/resources/titus-small.txt";
//    public static final String FILE_PATH = "src/main/resources/oslusiadas_key17.txt";
    public static final String FILE_PATH = "src/main/resources/athens_keyflute.txt";

    public String testEncryption(int encryptionKey) {
        FileResource fileResource = new FileResource(FILE_PATH);
        StringBuilder textFromFile = new StringBuilder();
        for (String line : fileResource.lines()) {
            textFromFile.append(line);
        }

        CaesarCipher caesarCipher = new CaesarCipher(encryptionKey);
        String encryptedString = caesarCipher.encrypt(textFromFile.toString());
        return encryptedString;
    }

    public String testDecryption(String encryptedTest, int encryptionKey) {
        CaesarCipher caesarCipher = new CaesarCipher(encryptionKey);
        return caesarCipher.decrypt(encryptedTest);
    }

    public char testEncryptLetter(char letter) {
        CaesarCipher caesarCipher = new CaesarCipher(5);
        char encryptedChar = caesarCipher.encryptLetter(letter);
        return encryptedChar;
    }

    public char testDecryptLetter(char letter) {
        CaesarCipher caesarCipher = new CaesarCipher(5);
        char decryptedChar = caesarCipher.decryptLetter(letter);
        return decryptedChar;
    }

    public int testGetKey(String encryptedText) {
//        CaesarCracker caesarCracker = new CaesarCracker('a');
        CaesarCracker caesarCracker = new CaesarCracker();
        return caesarCracker.getKey(encryptedText);
    }

    public String testVeneerCipherEncrypt(String stringText) {
        int[] encKey = new int[]{17, 14, 12, 4};
        VigenereCipher vigenereCipher = new VigenereCipher(encKey);
        return vigenereCipher.encrypt(stringText);

    }

    public String testSliceString(String message, int whichSlice, int totalSlices) {
        VigenereBreaker vigenereBreaker = new VigenereBreaker();
        String sliceString = vigenereBreaker.sliceString(message, whichSlice, totalSlices);
        return sliceString;
    }

    public void testTryKeyLength(String encrypted, int klength, char mostCommon) {
        VigenereBreaker vigenereBreaker = new VigenereBreaker();
        vigenereBreaker.tryKeyLength(encrypted, klength, mostCommon);
    }

    public void testBreakVigenere(String filePath) {
        VigenereBreaker vigenereBreaker = new VigenereBreaker();
        vigenereBreaker.breakVigenere(filePath);
    }

    public static void main(String[] args) {
       /* FileResource fileResource = new FileResource(FILE_PATH);
        StringBuilder textFromFile = new StringBuilder();
        for (String line : fileResource.lines()) {
            textFromFile.append(line);
        }*/

        FileResource fileResource = new FileResource(FILE_PATH);
        String textFromFile = fileResource.asString();


        Tester tester = new Tester();

        // Encrypt & Decrypt file text
//        String encText = tester.testEncryption(5);
//        String decText = tester.testDecryption(encText, 5);
//        System.out.println(encText);
//        System.out.println("----------------------");
//        System.out.println(decText);

        // Encrypt & Decrypt chars
//        System.out.println(tester.testEncryptLetter('B'));
//        System.out.println(tester.testDecryptLetter('G'));

        // get encryption key
//        System.out.println(tester.testGetKey(textFromFile));

        //  Encrypt & Decrypt file text - VigenereCipher
//        System.out.println(tester.testVeneerCipherEncrypt(textFromFile));


        // test VigenereBreaker
        // SliceString
//        System.out.println(tester.testSliceString("abcdefghijklm", 0, 5));

        // test VigenereBreaker
        // tryKeyLength
        tester.testTryKeyLength(textFromFile, 5, 'e');

        // test VigenereBreaker
        // breakVigenere
        tester.testBreakVigenere(FILE_PATH);


    }

}
