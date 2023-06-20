import com.codeborne.selenide.SelenideElement;
import org.example.steps.LoginSteps;
import org.example.steps.ProductsSteps;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SauceDemoTest extends BaseTest {

    LoginSteps loginSteps = new LoginSteps();
    ProductsSteps productsSteps = new ProductsSteps();

    @Test
    public void loginTest() {
        loginSteps.login("standard_user", "secret_sauce");

        $(By.xpath("//span[@class='title']")).shouldHave(text("Products"));
    }

    @Test
    public void addItemToCart() {
        loginSteps.login("standard_user", "secret_sauce");
        SelenideElement cheapestProduct = productsSteps.findCheapestPrice();
        String productName = cheapestProduct.find(".inventory_item_name").getText();
        productsSteps.addProductToCart(cheapestProduct);
        productsSteps.goToCart();
        productsSteps.verifyProductInCart(productName);
    }
}
