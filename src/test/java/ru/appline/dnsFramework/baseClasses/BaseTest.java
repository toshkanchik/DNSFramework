package ru.appline.dnsFramework.baseClasses;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.appline.dnsFramework.managers.PageMngr;
import ru.appline.dnsFramework.managers.PropMngr;

import static ru.appline.dnsFramework.managers.DriverMngr.getDriver;
import static ru.appline.dnsFramework.managers.InitMngr.*;
import static ru.appline.dnsFramework.managers.PageMngr.getPageMngr;

public class BaseTest {
    protected PageMngr pages = getPageMngr();
    @BeforeAll
    static void beforeAll(){
        InitAll();
    }
    @BeforeEach
    void beforeEach(){
        getDriver().get(PropMngr.GetPropMngr().getProperty("app.url"));
    }
    @AfterAll
    static void after(){
        closeAll();
    }
}
