package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.page.LoginPage;
import org.example.page.ProductsPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class LoginCucumberSteps {

    LoginPage loginPage = new LoginPage();
    ProductsPage productsPage = new ProductsPage();

    @Given("I am on the login page")
    public void navigateToLoginPage() {
        open("https://www.saucedemo.com/");
    }

    @When("I enter the username {string} and password {string}")
    public void login(String username, String password) {
        loginPage.userNameField.setValue(username);
        loginPage.passwordField.setValue(password);
        loginPage.loginButton.click();
    }

    @Then("I should see the products page with title {string}")
    public void verifyLogin(String titleName) {
        productsPage.titleText.shouldHave(text(titleName));
    }
}
