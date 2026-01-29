package org.example.saucedemouitests.test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.saucedemouitests.base.BaseTest;
import org.example.saucedemouitests.pages.InventoryPage;
import org.example.saucedemouitests.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Epic("Авторизация")
@Feature("Логин пользователя")
public class LoginTests extends BaseTest {

    @Test
    @Story("Успешный логин")
    void successfulLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        assertTrue(inventoryPage.isOpened(),
                "Страница товаров должна открыться");
    }

    @Test
    @Story("Неверный пароль")
    void loginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "wrong_password");

        assertTrue(loginPage.getErrorMessage()
                        .contains("Username and password do not match"),
                "Ожидается сообщение об ошибке");
    }

    @Test
    @Story("Заблокированный пользователь")
    void lockedOutUserLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.getErrorMessage()
                        .contains("locked out"),
                "Пользователь должен быть заблокирован");
    }
    @Test
    @Story("Пустые поля")
    void loginWithEmptyFields() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("", "");

        assertTrue(loginPage.getErrorMessage()
                        .contains("Username is required"),
                "Должна быть ошибка о пустом логине");
    }

    @Test
    @Story("Performance glitch user")
    void performanceGlitchUserLogin() {
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);

        loginPage.login("performance_glitch_user", "secret_sauce");

        assertTrue(inventoryPage.isOpened(),
                "Страница должна открыться даже с задержкой");
    }
}
