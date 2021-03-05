package by.bsuir.poit.scrambler.model;

public class ApplicationProperties {

    private final String inputRootDir;
    private final String outputRootDir;
    private final String inputFileName;
    private final String outputFileName;

    public ApplicationProperties(String inputRootDir, String outputRootDir, String inputFileName, String outputFileName) {
        this.inputRootDir = inputRootDir;
        this.outputRootDir = outputRootDir;
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public String getInputRootDir() {
        return inputRootDir;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }
}

