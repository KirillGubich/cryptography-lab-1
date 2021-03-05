package by.bsuir.poit.scrambler.cipher.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class RotatingLatticeCipherTest {

    @Test
    public void encrypt() {
        int[][] key = {{0, 0}, {3, 1}, {2, 2}, {1, 3}};
        String actual = RotatingLatticeCipher.INSTANCE.encrypt("SIFROTEKST", key);
        String expected = "SCSOTTDIAEFEFRKB";
        assertEquals(expected, actual);
    }


    @Test
    public void decrypt() {
        int[][] key = {{0, 0}, {3, 1}, {2, 2}, {1, 3}};
        String actual = RotatingLatticeCipher.INSTANCE.decrypt("SCSOTTDIAEFEFRKB", key);
        String expected = "SIFROTEKSTABCDEF";
        assertEquals(expected, actual);
    }
}