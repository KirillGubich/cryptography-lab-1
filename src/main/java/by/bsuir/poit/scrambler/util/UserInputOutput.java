package by.bsuir.poit.scrambler.util;

import by.bsuir.poit.scrambler.exception.TextOutputException;
import by.bsuir.poit.scrambler.model.ApplicationProperties;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public final class UserInputOutput {

    private UserInputOutput() {
    }

    public static String getStringFromConsole() {
        Scanner consoleInput = new Scanner(System.in);
        System.out.print(">> ");
        return consoleInput.nextLine();
    }

    public static int getIntFromConsole() {
        Scanner consoleInput = new Scanner(System.in);
        int value;
        System.out.print(">> ");
        while (!consoleInput.hasNextInt()) {
            consoleInput.next();
            System.out.println("Incorrect data. Try again. ");
            System.out.print(">> ");
        }
        value = consoleInput.nextInt();
        return value;
    }

    public static String getTextFromFile() throws IOException {
        ApplicationProperties applicationProperties = PropertyReaderUtil.loadProperties();
        String inputFilePath = Paths.get(applicationProperties.getInputRootDir(),
                applicationProperties.getInputFileName()).toString();
        String result = "";
        result = Files.lines(Paths.get(inputFilePath)).reduce("", String::concat);
        return result;
    }

    public static void outputTextToFile(String text) throws TextOutputException {
        if (text == null) {
            return;
        }
        ApplicationProperties applicationProperties = PropertyReaderUtil.loadProperties();
        File outputFile = Paths.get(applicationProperties.getOutputRootDir(),
                applicationProperties.getOutputFileName()).toFile();
        try (FileWriter fileWriter = new FileWriter(outputFile);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw new TextOutputException("Text output error.");
        }
    }

    public static Boolean userHalfChoice() {
        String userInput = UserInputOutput.getStringFromConsole();
        while (!userInput.equals("1") && !userInput.equals("2")) {
            System.out.println("Incorrect data. Try again. ");
            userInput = UserInputOutput.getStringFromConsole();
        }
        return userInput.equals("1");
    }

    public static int[][] getNumericMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = getIntFromConsole();
            }
        }
        return matrix;
    }
}
