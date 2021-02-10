package ru.appline.dnsFramework.managers;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverMngr {
    private static WebDriver driver;
    private static DriverMngr instance = null;


    private DriverMngr() {
        PropMngr props = PropMngr.GetPropMngr();
        switch (props.getProperty("type.browser")) {
            case "edge":
                System.setProperty("webdriver.edge.driver", props.getProperty("path.edge.driver.windows"));
                driver = new EdgeDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", props.getProperty("path.chrome.driver.windows"));
                driver = new ChromeDriver();
                break;
            default:
                Assertions.fail("Типа браузера '" + props.getProperty("type.browser") + "' не существует во фреймворке");
        }
    }

    public static WebDriver getDriver(){
        if(instance == null){
            instance = new DriverMngr();
        }
        return driver;
    }
    public static void quitDriver(){
        if(driver!=null){
            driver.quit();
            driver = null;
        }
    }
}
