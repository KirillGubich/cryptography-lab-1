package by.bsuir.poit.scrambler.cipher.impl;

import by.bsuir.poit.scrambler.cipher.Cipher;
import by.bsuir.poit.scrambler.exception.InvalidCryptTextException;
import by.bsuir.poit.scrambler.exception.KeyTypeException;

public enum RotatingLatticeCipher implements Cipher {
    INSTANCE;

    private int[][] key = {{0, 0}, {3, 1}, {2, 3}, {2, 2}};
    private final int length = 4;

    public void setKey(int[][] key) {
        this.key = key;
    }

    @Override
    public String encrypt(String text, Object key) throws KeyTypeException {
        text = text.toUpperCase();
        int[][] cryptKey = (int[][]) key;
        checkKey(cryptKey);
        setKey(cryptKey);
        return encode(text);
    }

    @Override
    public String decrypt(String text, Object key) throws KeyTypeException {
        text = text.toUpperCase();
        int[][] cryptKey = (int[][]) key;
        checkKey(cryptKey);
        setKey(cryptKey);
        return decode(text);
    }

    private void checkKey(int[][] key) throws KeyTypeException {
        if (key.length != length) {
            throw new KeyTypeException("Incorrect key");
        }
        for (int[] point : key) {
            for (int num : point) {
                if (num >= key.length) {
                    throw new KeyTypeException("Incorrect key");
                }
            }
        }
    }

    public String encode(String textToEncrypt) {
        String regex = "(?<=\\G.{" + length * length + "})";
        textToEncrypt = expandString(textToEncrypt, length * length);
        String[] arr = textToEncrypt.split(regex);
        StringBuilder ciphertext = new StringBuilder();
        for (String element : arr) {
            ciphertext.append(squareEncode(element));
        }
        return ciphertext.toString();
    }

    public String decode(String cipherText) throws InvalidCryptTextException {
        if (cipherText.length() % length * length != 0) {
            throw new InvalidCryptTextException("Invalid cryptotext");
        }
        String regex = "(?<=\\G.{" + length * length + "})";
        String[] arr = cipherText.split(regex);
        StringBuilder plaintext = new StringBuilder();
        for (String element : arr) {
            plaintext.append(squareDecode(element));
        }
        return plaintext.toString();
    }

    private int[][] getIndex(int iteration) {
        int[][] temp = new int[length][2];
        for (int[] elem : key) {
            int x;
            if ((iteration == 0) || (iteration == 3)) {
                x = elem[0];
            } else {
                x = (length - 1) - elem[0];
            }
            int y;
            if (iteration <= 1) {
                y = elem[1];
            } else {
                y = length - 1 - elem[1];
            }
            int[] el = new int[2];
            el[0] = x;
            el[1] = y;
            temp[el[1]] = el;
        }
        return temp;
    }

    private String expandString(String str, int multiplicity) {
        StringBuilder temp = new StringBuilder(str);
        char letter = 'A';
        while (temp.length() % multiplicity != 0) {
            temp.append(letter++);
            if (letter > 'Z') {
                letter = 'A';
            }
        }
        return temp.toString();
    }

    private String squareEncode(String text) {
        char[][] matrix = new char[length][length];
        int currIndex = 0;
        for (int i = 0; i < matrix.length; i++) {
            int[][] newIndices = getIndex(i);
            for (int[] indices : newIndices) {
                matrix[indices[1]][indices[0]] = text.charAt(currIndex++);
            }
        }
        StringBuilder ciphertext = new StringBuilder();
        for (char[] chars : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                ciphertext.append(chars[j]);
            }
        }
        return ciphertext.toString();
    }

    public String squareDecode(String cipherText) {
        char[][] matrix = new char[length][length];
        int currIndex = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = cipherText.charAt(currIndex++);
            }
        }
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            int[][] newIndices = getIndex(i);
            for (int[] indices : newIndices) {
                plaintext.append(matrix[indices[1]][indices[0]]);
            }
        }
        return plaintext.toString();
    }
}

