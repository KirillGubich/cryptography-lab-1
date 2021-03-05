package by.bsuir.poit.scrambler.util;

import by.bsuir.poit.scrambler.exception.PropertiesLoadingException;
import by.bsuir.poit.scrambler.model.ApplicationProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class PropertyReaderUtil {

    private static final Properties properties = new Properties();

    private PropertyReaderUtil() {
    }

    public static ApplicationProperties loadProperties() throws PropertiesLoadingException {
        final String propertiesFileName = "src/main/resources/application.properties";
        try (FileInputStream fileInput = new FileInputStream(propertiesFileName)) {
            properties.load(fileInput);
        } catch (FileNotFoundException e) {
            throw new PropertiesLoadingException("Property file cannot be found");
        } catch (IOException e) {
            throw new PropertiesLoadingException("Property file reading error");
        }
        return new ApplicationProperties(
                properties.getProperty("inputRootDir"),
                properties.getProperty("outputRootDir"),
                properties.getProperty("inputFileName"),
                properties.getProperty("outputFileName")
        );
    }

}
