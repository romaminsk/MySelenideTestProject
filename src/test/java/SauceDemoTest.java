import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SauceDemoTest {

    @Test
    public void loginTest() {
        // Open the Sauce Demo website
        open("https://www.saucedemo.com/");

        // Perform login
        $(By.id("user-name")).setValue("standard_user");
        $(By.id("password")).setValue("secret_sauce");
        $(By.id("login-button")).click();

        // Assertion - check if login is successful
        // For example, assert that the product page is displayed after successful login
        $(By.xpath("//span[@class='title']")).shouldHave(text("Products"));
    }
}
