package by.bsuir.poit.scrambler.encryptor;

public interface Encryptor {

    String encryptText(String text, Object key);

    String decryptText(String text, Object key);
}
