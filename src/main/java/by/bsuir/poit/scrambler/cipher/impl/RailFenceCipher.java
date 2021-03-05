package by.bsuir.poit.scrambler.cipher.impl;

import by.bsuir.poit.scrambler.cipher.Cipher;
import by.bsuir.poit.scrambler.exception.KeyTypeException;

public enum RailFenceCipher implements Cipher {
    INSTANCE;

    @Override
    public String encrypt(String text, Object key) {
        int cryptoKey = (Integer) key;
        int period = 2 * (cryptoKey - 1);
        text = text.replace("\\s+", "");
        StringBuilder[] rows = new StringBuilder[period];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder();
        }
        for (int i = 0; i < text.length(); i++) {
            int remainder = i % period;
            int rowNum = cryptoKey - 1 - Math.abs(cryptoKey - 1 - remainder);
            rows[rowNum].append(text.charAt(i));
        }
        StringBuilder encryptedText = new StringBuilder();
        for (StringBuilder row : rows) {
            encryptedText.append(row);
        }
        return encryptedText.toString();
    }

    @Override
    public String decrypt(String text, Object key) throws KeyTypeException {
        int cryptoKey = (Integer) key;
        if (cryptoKey < 0) {
            throw new KeyTypeException("Negative key value");
        }
        StringBuilder encryptedMessage = new StringBuilder(text);
        int currentPosition = 0;
        for (int row = 0; row < cryptoKey; row++) {
            int iterationCount = 0;
            for (int i = row; i < text.length(); i += getTerm(iterationCount++, row, cryptoKey)) {
                encryptedMessage.setCharAt(i, text.charAt(currentPosition++));
            }
        }
        return encryptedMessage.toString();
    }

    private int getTerm(int iteration, int row, int size) {
        if ((size == 0) || (size == 1)) {
            return 1;
        }
        if ((row == 0) || (row == size - 1)) {
            return (size - 1) * 2;
        }

        if (iteration % 2 == 0) {
            return (size - 1 - row) * 2;
        }
        return 2 * row;
    }
}
