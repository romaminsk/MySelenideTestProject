package org.example.steps;

import org.example.page.LoginPage;
import org.example.page.ProductsPage;

import static com.codeborne.selenide.Condition.text;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();
    ProductsPage productsPage = new ProductsPage();

    public void login(String username, String password) {
        loginPage.userNameField.setValue(username);
        loginPage.passwordField.setValue(password);
        loginPage.loginButton.click();
    }

    public void verifyLogin(String titleName) {
        productsPage.titleText.shouldHave(text(titleName));
    }
}
