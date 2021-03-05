package by.bsuir.poit.scrambler.cipher.impl;

import by.bsuir.poit.scrambler.cipher.Cipher;

import java.util.Arrays;

public enum ColumnCipher implements Cipher {
    INSTANCE;

    @Override
    public String encrypt(String text, Object key) {
        text = text.replace("\\s+", "");
        String cryptoKey = ((String) key).replace("\\s+", "");
        int columnsCount = cryptoKey.length();
        int rowsCount = (int) Math.ceil(((double) text.length()) / columnsCount);
        char[][] cryptoMatrix = new char[rowsCount + 1][columnsCount];
        int currentCharacter = 0;
        cryptoMatrix[0] = cryptoKey.toCharArray();
        for (int i = 1; i < rowsCount + 1; i++) {
            for (int j = 0; j < columnsCount; j++) {
                if (currentCharacter < text.length()) {
                    cryptoMatrix[i][j] = text.charAt(currentCharacter++);
                }
            }
        }
        sort(cryptoMatrix, 0, cryptoMatrix.length - 1);
        return readMatrixByColumns(columnsCount, rowsCount, cryptoMatrix);
    }

    private String readMatrixByColumns(int columnsCount, int rowsCount, char[][] cryptoMatrix) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < columnsCount; j++) {
            for (int i = 1; i < rowsCount + 1; i++) {
                if (cryptoMatrix[i][j] != 0) {
                    sb.append(cryptoMatrix[i][j]);
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String decrypt(String text, Object key) {
        String cryptoKey = ((String) key).replace("\\s+", "");
        char[][] plainMatrix = fillPlainMatrix(text, cryptoKey);
        StringBuilder plainText = new StringBuilder();
        int row = 0;
        int column = 0;
        for (int index = 0; index < text.length(); index++) {
            plainText.append(plainMatrix[row][column]);
            column++;
            if (column > cryptoKey.length() - 1) {
                column = 0;
                row++;
            }
        }
        return plainText.toString();
    }

    private char[][] fillPlainMatrix(String cipherText, String key) {
        byte[] cipherPositions = FillCipherPositionsArray(key);
        int rowsAmount = CountRowsAmount(cipherText, key);
        char[][] plainMatrix = new char[rowsAmount][key.length()];
        int columnToWrite = 1;
        int amountOfEmptyCells = rowsAmount * key.length() - cipherText.length();
        int indexOfCipherChar = 0;
        while (columnToWrite < key.length() + 1) {
            for (int i = 0; i < cipherPositions.length; i++) {
                if (cipherPositions[i] == columnToWrite) {
                    if (key.length() - i - 1 < amountOfEmptyCells) {
                        for (int j = 0; j < rowsAmount - 1; j++) {
                            plainMatrix[j][i] = cipherText.charAt(indexOfCipherChar);
                            indexOfCipherChar++;
                        }
                    } else {
                        for (int j = 0; j < rowsAmount; j++) {
                            plainMatrix[j][i] = cipherText.charAt(indexOfCipherChar);
                            indexOfCipherChar++;
                        }
                    }
                    columnToWrite++;
                }
            }
        }
        return plainMatrix;
    }

    private void sort(char[][] arrayIn, int start, int end) {
        char[] array = arrayIn[0];
        if (start >= end) return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (((int) array[i] <= (int) array[cur]) && i < cur) {
                i++;
            }
            while (((int) array[j] >= (int) array[cur]) && j > cur) {
                j--;
            }
            if (i < j) {
                swap(arrayIn, i, j);
                array = arrayIn[0];
                if (i == cur) {
                    cur = j;
                } else if (j == cur) {
                    cur = i;
                }
            }
        }
        sort(arrayIn, start, cur);
        sort(arrayIn, cur + 1, end);
    }

    private void swap(char[][] array, int i, int j) {
        for (int k = 0; k < array.length; k++) {
            char buff = array[k][i];
            array[k][i] = array[k][j];
            array[k][j] = buff;
        }
    }

    private static byte[] FillCipherPositionsArray(String key) {
        byte[] cipherPositions = new byte[key.length()];
        char[] keyChars = key.toCharArray();
        Arrays.sort(keyChars);
        byte position = 1;
        for (char keyChar : keyChars) {
            int keyIndex = 0;
            boolean isNotFound = true;
            while (isNotFound) {
                if (keyChar == key.charAt(keyIndex)) {
                    if (cipherPositions[keyIndex] > 0) {
                        keyIndex++;
                        continue;
                    } else {
                        cipherPositions[keyIndex] = position;
                        position++;
                        isNotFound = false;
                    }
                }
                keyIndex++;
            }
        }
        return cipherPositions;
    }

    private static int CountRowsAmount(String text, String key) {
        if (text.length() % key.length() == 0) {
            return text.length() / key.length();
        } else {
            return text.length() / key.length() + 1;
        }
    }
}
