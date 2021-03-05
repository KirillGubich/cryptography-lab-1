package by.bsuir.poit.scrambler.cipher.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class CaesarCipherTest {

    @Test
    public void encrypt() {
        String expected = "HVWGWGHSGHTCFQWDVSF";
        String actual = CaesarCipher.INSTANCE.encrypt("THISISTESTFORCIPHER", 14);
        assertEquals(expected, actual);
    }

    @Test
    public void decrypt() {
        String expected = "THISISTESTFORCIPHER";
        String actual = CaesarCipher.INSTANCE.decrypt("HVWGWGHSGHTCFQWDVSF", 14);
        assertEquals(expected, actual);
    }

    @Test
    public void decryptSecond() {
        String expected = "HARDTESTFORPROGRAMM";
        String actual = CaesarCipher.INSTANCE.decrypt("CVMYOZNOAJMKMJBMVHH", 21);
        assertEquals(expected, actual);
    }
}