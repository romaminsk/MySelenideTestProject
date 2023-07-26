package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.example.page.LoginPage;
import org.example.page.ProductsPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

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

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failed screen", new ByteArrayInputStream(screenshot));
        }
    }
}
