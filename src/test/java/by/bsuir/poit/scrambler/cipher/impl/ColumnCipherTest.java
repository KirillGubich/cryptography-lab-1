package by.bsuir.poit.scrambler.cipher.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColumnCipherTest {

    @Test
    public void encrypt() {
        String expected = "asnlwopitete";
        String actual = ColumnCipher.INSTANCE.encrypt("iwanttosleep", "night");
        assertEquals(expected, actual);
    }

    @Test
    public void encryptSecond() {
        String expected = "itlaoenspwte";
        String actual = ColumnCipher.INSTANCE.encrypt("iwanttosleep", "bubl");
        assertEquals(expected, actual);
    }

    @Test
    public void encryptThird() {
        String expected = "wteaoenspitl";
        String actual = ColumnCipher.INSTANCE.encrypt("iwanttosleep", "baaa");
        assertEquals(expected, actual);
    }

    @Test
    public void decrypt() {
        String expected = "iwanttosleep";
        String actual = ColumnCipher.INSTANCE.decrypt("asnlwopitete", "night");
        assertEquals(expected, actual);
    }

    @Test
    public void decryptSecond() {
        String expected = "iwanttosleep";
        String actual = ColumnCipher.INSTANCE.decrypt("itlaoenspwte", "bubl");
        assertEquals(expected, actual);
    }
}