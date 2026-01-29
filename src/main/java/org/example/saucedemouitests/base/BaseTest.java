package org.example.saucedemouitests.base;

import io.qameta.allure.Step;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.example.saucedemouitests.base.DriverFactory.createDriver;



public abstract class BaseTest {

    protected WebDriver driver;


    @BeforeEach
    @Step("Инициализация браузера")
    void setUp() {

        String browser = System.getProperty("browser", "chrome");
        driver = createDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }


    @AfterEach
    @Step("Закрытие браузера")
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
