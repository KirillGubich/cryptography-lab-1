package by.bsuir.poit.scrambler.context.impl;

import by.bsuir.poit.scrambler.context.Application;
import by.bsuir.poit.scrambler.context.ApplicationMenu;
import by.bsuir.poit.scrambler.model.ApplicationStatus;
import by.bsuir.poit.scrambler.util.UserInputOutput;

public enum ScramblerApplication implements Application {
    INSTANCE;

    private final ApplicationMenu applicationMenu = ScramblerApplicationMenu.INSTANCE;

    @Override
    public void launchMenu() {
        ApplicationStatus applicationStatus = ApplicationStatus.IN_PROGRESS;
        while (applicationStatus.equals(ApplicationStatus.IN_PROGRESS)) {
            applicationMenu.printAvailableOptions();
            String userInput = UserInputOutput.getStringFromConsole();
            applicationStatus = applicationMenu.handleUserInput(userInput);
        }
    }
}
