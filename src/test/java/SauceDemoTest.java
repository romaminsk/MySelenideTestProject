import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SauceDemoTest {

    @Test
    public void loginTest() {
        open("https://www.saucedemo.com/");

        $(By.id("user-name")).setValue("standard_user");
        $(By.id("password")).setValue("secret_sauce");
        $(By.id("login-button")).click();

        $(By.xpath("//span[@class='title']")).shouldHave(text("Products"));
    }
}
