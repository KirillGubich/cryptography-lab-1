package by.bsuir.poit.scrambler.encryptor.impl;

import by.bsuir.poit.scrambler.cipher.Cipher;
import by.bsuir.poit.scrambler.encryptor.Encryptor;

public enum TextEncryptor implements Encryptor {
    INSTANCE;

    private Cipher cipher;

    public void setCipher(Cipher cipher) {
        this.cipher = cipher;
    }

    @Override
    public String encryptText(String text, Object key) {
        return cipher.encrypt(text, key);
    }

    @Override
    public String decryptText(String text, Object key) {
        return cipher.decrypt(text, key);
    }
}