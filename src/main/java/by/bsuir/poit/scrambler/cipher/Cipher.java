package by.bsuir.poit.scrambler.cipher;

public interface Cipher {

    String encrypt(String text, Object key);

    String decrypt(String text, Object key);
}
