package org.example.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public SelenideElement userNameField = $(By.id("user-name"));
    public SelenideElement passwordField = $(By.id("password"));
    public SelenideElement loginButton = $(By.id("login-button"));
}
