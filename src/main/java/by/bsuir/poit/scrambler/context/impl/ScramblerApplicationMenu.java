package by.bsuir.poit.scrambler.context.impl;

import by.bsuir.poit.scrambler.cipher.Cipher;
import by.bsuir.poit.scrambler.cipher.impl.CaesarCipher;
import by.bsuir.poit.scrambler.cipher.impl.ColumnCipher;
import by.bsuir.poit.scrambler.cipher.impl.RailFenceCipher;
import by.bsuir.poit.scrambler.cipher.impl.RotatingLatticeCipher;
import by.bsuir.poit.scrambler.context.ApplicationMenu;
import by.bsuir.poit.scrambler.encryptor.impl.TextEncryptor;
import by.bsuir.poit.scrambler.exception.KeyTypeException;
import by.bsuir.poit.scrambler.exception.TextOutputException;
import by.bsuir.poit.scrambler.model.ApplicationStatus;
import by.bsuir.poit.scrambler.model.KeyType;
import by.bsuir.poit.scrambler.util.UserInputOutput;

import java.io.IOException;

public enum ScramblerApplicationMenu implements ApplicationMenu {
    INSTANCE;

    @Override
    public void printAvailableOptions() {
        System.out.println("Choose action: ");
        System.out.println("1 - encrypt text with Caesar cipher");
        System.out.println("2 - decrypt text with Caesar cipher");
        System.out.println("3 - encrypt text with Column cipher");
        System.out.println("4 - decrypt text with Column cipher");
        System.out.println("5 - encrypt text with Rail fence cipher");
        System.out.println("6 - decrypt text with Rail fence cipher");
        System.out.println("7 - encrypt text with Rotating lattice cipher");
        System.out.println("8 - decrypt text with Rotating lattice cipher");
        System.out.println("0 - close application");
    }

    @Override
    public ApplicationStatus handleUserInput(String userInput) {
        ApplicationStatus applicationStatus = ApplicationStatus.IN_PROGRESS;
        switch (userInput) {
            case "1": {
                encrypt(CaesarCipher.INSTANCE, KeyType.NUMBER);
                break;
            }
            case "2": {
                decrypt(CaesarCipher.INSTANCE, KeyType.NUMBER);
                break;
            }
            case "3": {
                encrypt(ColumnCipher.INSTANCE, KeyType.STRING);
                break;
            }
            case "4": {
                decrypt(ColumnCipher.INSTANCE, KeyType.STRING);
                break;
            }
            case "5": {
                encrypt(RailFenceCipher.INSTANCE, KeyType.NUMBER);
                break;
            }
            case "6": {
                decrypt(RailFenceCipher.INSTANCE, KeyType.NUMBER);
                break;
            }
            case "7": {
                encrypt(RotatingLatticeCipher.INSTANCE, KeyType.ARRAY);
                break;
            }
            case "8": {
                decrypt(RotatingLatticeCipher.INSTANCE, KeyType.ARRAY);
                break;
            }
            case "0": {
                applicationStatus = ApplicationStatus.COMPLETED;
                break;
            }
            default:
                System.out.printf("%s \n\n", "Unknown command. Please try again");
        }
        return applicationStatus;
    }

    private void encrypt(Cipher cipher, KeyType keyType) {
        String textToEncrypt = fetchInputData();
        System.out.println("Input key");
        Object key = fetchUserKey(keyType);
        TextEncryptor.INSTANCE.setCipher(cipher);
        String encryptedText;
        try {
            encryptedText = TextEncryptor.INSTANCE.encryptText(textToEncrypt, key);
        } catch (KeyTypeException e) {
            System.out.printf("%s\n\n", e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Something went wrong. Try again or contact support");
            return;
        }
        outputResult(encryptedText);
    }

    private void decrypt(Cipher cipher, KeyType keyType) {
        String textToDecrypt = fetchInputData();
        System.out.println("Input key");
        Object key = fetchUserKey(keyType);
        TextEncryptor.INSTANCE.setCipher(cipher);
        String encryptedText;
        try {
            encryptedText = TextEncryptor.INSTANCE.decryptText(textToDecrypt, key);
        } catch (KeyTypeException e) {
            System.out.println(e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Something went wrong. Try again or contact support");
            return;
        }
        outputResult(encryptedText);
    }

    private Object fetchUserKey(KeyType keyType) {
        Object key;
        switch (keyType) {
            case NUMBER:
                key = UserInputOutput.getIntFromConsole();
                break;
            case STRING:
                key = UserInputOutput.getStringFromConsole();
                break;
            case ARRAY:
                key = UserInputOutput.getNumericMatrix(4, 2);
                break;
            default:
                key = "0";
        }
        return key;
    }

    private String fetchInputData() {
        System.out.println("1 - input from console");
        System.out.println("2 - input from file");
        String userInput = UserInputOutput.getStringFromConsole();
        String result = "";
        while (!userInput.equals("1") && !userInput.equals("2")) {
            System.out.println("Incorrect data. Try again. ");
            userInput = UserInputOutput.getStringFromConsole();
        }
        try {
            result = userInput.equals("1") ? UserInputOutput.getStringFromConsole() :
                    UserInputOutput.getTextFromFile();
        } catch (IOException e) {
            System.out.println("File reading error. Check properties file");
        }
        return result;
    }

    private void outputResult(String text) {
        System.out.println("1 - output to console");
        System.out.println("2 - output to file");
        if (UserInputOutput.userHalfChoice()) {
            System.out.println(text);
        } else {
            try {
                UserInputOutput.outputTextToFile(text);
            } catch (TextOutputException e) {
                System.out.println(e + " Check properties file");
            }
        }
    }
}
