package org.example.steps;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginSteps {

    public void login(String username, String password) {
        $(By.id("user-name")).setValue(username);
        $(By.id("password")).setValue(password);
        $(By.id("login-button")).click();
    }
}
