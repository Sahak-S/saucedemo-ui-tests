package org.example.saucedemouitests.pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private final WebDriver driver;
    private final By inventoryContainer = By.id("inventory_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка, что страница товаров открылась")
    public boolean isOpened() {
        return driver.findElement(inventoryContainer).isDisplayed();
    }
}
