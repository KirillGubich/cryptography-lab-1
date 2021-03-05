package by.bsuir.poit.scrambler.cipher.impl;

import by.bsuir.poit.scrambler.cipher.Cipher;

public enum CaesarCipher implements Cipher {
    INSTANCE;

    private static final int NUMBER_OF_LETTERS_IN_ALPHABET = 26;

    @Override
    public String encrypt(String text, Object key) {
        text = text.replace("\\s+", "").toUpperCase();
        char[] chars = text.toCharArray();
        shiftRight(chars, (Integer) key);
        return new String(chars);
    }

    @Override
    public String decrypt(String text, Object key) {
        text = text.replace("\\s+", "").toUpperCase();
        char[] chars = text.toCharArray();
        shiftLeft(chars, (Integer) key);
        return new String(chars);
    }

    private void shiftLeft(char[] chars, int shift) {
        shift = shift % NUMBER_OF_LETTERS_IN_ALPHABET;
        for (int i = 0; i < chars.length; i++) {
            int resultValue;
            resultValue = (chars[i] - 'A' + NUMBER_OF_LETTERS_IN_ALPHABET - shift)
                    % NUMBER_OF_LETTERS_IN_ALPHABET;
            chars[i] = (char) ('A' + resultValue);
        }
    }

    private void shiftRight(char[] chars, int shift) {
        shift = shift % NUMBER_OF_LETTERS_IN_ALPHABET;
        for (int i = 0; i < chars.length; i++) {
            int resultValue;
            resultValue = ((chars[i] - 'A') + shift) % NUMBER_OF_LETTERS_IN_ALPHABET;
            chars[i] = (char) ('A' + resultValue);
        }
    }
}
