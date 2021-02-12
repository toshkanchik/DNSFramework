package ru.appline.dnsFramework.managers;

import java.util.concurrent.TimeUnit;

import static ru.appline.dnsFramework.managers.DriverMngr.*;

public class InitMngr {
    private static final PropMngr props = PropMngr.GetPropMngr();

    public static void InitAll() {
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(props.getProperty("implicitly.wait")), TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(props.getProperty("page.load.timeout")), TimeUnit.SECONDS);
    }

    public static void closeAll() {
        quitDriver();
    }
}
