package cafe.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.Properties;

public class Settings {

    private static final String SELENIUM_BASEURL = "selenium.baseUrl";
    private static final String SELENIUM_BROWSER = "selenium.browser";
    private static final String SELENIUM_PROPERTIES = "selenium.properties";
    private static final String OUTPUT_DIR = "outputDir";

    private String baseUrl;
    private BrowserType browser;
    private Properties properties = new Properties();
    private String outputDir;

    public Settings() {
        loadSettings();
    }

    private void loadSettings() {
        properties = loadPropertiesFile();
        baseUrl = properties.getProperty(SELENIUM_BASEURL);
        browser = BrowserType.Browser(properties.getProperty(SELENIUM_BROWSER));
        outputDir = properties.getProperty(OUTPUT_DIR);
    }

    private Properties loadPropertiesFile() {
        try {
            String filename = getPropertyOrNull(SELENIUM_PROPERTIES);
            if (filename == null) {
                filename = SELENIUM_PROPERTIES;
            }
            InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
            if (stream == null) {
                stream = new FileInputStream(new File(filename));
            }
            Properties result = new Properties();
            result.load(stream);
            return result;
        } catch (IOException e) {
            throw new UnknownPropertyException("Property file is not found");
        }
    }

    public String getPropertyOrNull(String name) {
        return getProperty(name, false);
    }

    private String getProperty(String name, boolean forceExceptionIfNotDefined) {
        String result;
        if ((result = System.getProperty(name, null)) != null) {
            return result;
        } else if ((result = getPropertyFromPropertiesFile(name)) != null) {
            return result;
        } else if (forceExceptionIfNotDefined) {
            throw new UnknownPropertyException("Unknown property: [" + name + "]");
        }
        return result;
    }

    private String getPropertyFromPropertiesFile(String name) {
        Object result = properties.get(name);
        if (result == null) {
            return null;
        } else {
            return result.toString();
        }
    }

    public WebDriver getDriver() {
        return  getDriver(browser);
    }

    private WebDriver getDriver(BrowserType browserType) {
        switch (browserType) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case GC:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

            default:
                throw new UnknownBrowserException("Cannot create driver for unknown browser type");
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public BrowserType getBrowser() {
        return browser;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public class UnknownBrowserException extends RuntimeException {
        public UnknownBrowserException(String message) {
            super(message);
        }
    }

    public class UnknownPropertyException extends RuntimeException {
        public UnknownPropertyException(String message) {
            super(message);
        }
    }

}
