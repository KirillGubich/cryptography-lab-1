package by.bsuir.poit.scrambler.cipher.impl;

import org.junit.Test;

import static org.junit.Assert.*;

public class RailFenceCipherTest {

    @Test
    public void encryptWithKeyThree() {
        String expected = "holelwrdlo";
        String actual = RailFenceCipher.INSTANCE.encrypt("helloworld", 3);
        assertEquals(expected, actual);
    }

    @Test
    public void encryptWithKeyFour() {
        String expected = "hoewrlolld";
        String actual = RailFenceCipher.INSTANCE.encrypt("helloworld", 4);
        assertEquals(expected, actual);
    }

    @Test
    public void decrypt() {
        String expected = "helloworld";
        String actual = RailFenceCipher.INSTANCE.decrypt("holelwrdlo", 3);
        assertEquals(expected, actual);
    }
}