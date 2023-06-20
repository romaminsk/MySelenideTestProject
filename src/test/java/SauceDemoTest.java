import com.codeborne.selenide.SelenideElement;
import org.example.steps.LoginSteps;
import org.example.steps.ProductsSteps;
import org.example.utils.Waiters;
import org.testng.annotations.Test;

public class SauceDemoTest extends BaseTest {

    LoginSteps loginSteps = new LoginSteps();
    ProductsSteps productsSteps = new ProductsSteps();

    @Test(enabled = false)
    public void loginTest() {
        loginSteps.login("standard_user", "secret_sauce");
        loginSteps.verifyLogin("Products");
    }

    @Test
    public void addItemToCart() {
        loginSteps.login("standard_user", "secret_sauce");
        SelenideElement cheapestProduct = productsSteps.findCheapestPrice();
        String productName = cheapestProduct.find(".inventory_item_name").getText();
        productsSteps.addProductToCart(cheapestProduct);
        productsSteps.goToCart();
        Waiters.sleep();
        productsSteps.verifyProductInCart(productName);
    }
}
