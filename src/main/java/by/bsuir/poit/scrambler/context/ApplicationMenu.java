package by.bsuir.poit.scrambler.context;

import by.bsuir.poit.scrambler.model.ApplicationStatus;

public interface ApplicationMenu {

    void printAvailableOptions();

    ApplicationStatus handleUserInput(String userInput);
}
