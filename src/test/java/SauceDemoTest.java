import com.codeborne.selenide.SelenideElement;
import org.example.models.ProductData;
import org.example.models.TestData;
import org.example.models.UserData;
import org.example.steps.LoginSteps;
import org.example.steps.ProductsSteps;
import org.example.utils.JsonReader;
import org.example.utils.Waiters;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemoTest extends BaseTest {

    LoginSteps loginSteps = new LoginSteps();
    ProductsSteps productsSteps = new ProductsSteps();

    @Test(dataProvider = "userData", dataProviderClass = JsonReader.class)
    public void loginTest(UserData userData) {
        loginSteps.login(userData.getUsername(), userData.getPassword());
        loginSteps.verifyLogin("Products");
    }

    @Test(dataProvider = "userData", dataProviderClass = JsonReader.class)
    public void addItemToCart(UserData userData) {
        loginSteps.login(userData.getUsername(), userData.getPassword());
        SelenideElement cheapestProduct = productsSteps.findCheapestPrice();
        String productName = cheapestProduct.find(".inventory_item_name").getText();
        productsSteps.addProductToCart(cheapestProduct);
        productsSteps.goToCart();
        Waiters.sleep();
        productsSteps.verifyProductInCart(productName);
    }

    @Test(dataProvider = "testData", dataProviderClass = JsonReader.class)
    public void compareProducts(TestData testData) {
        loginSteps.login(testData.getUserData().getUsername(), testData.getUserData().getPassword());
        productsSteps.clickFirstAddToCartButton();
        productsSteps.goToCart();

        Assert.assertEquals(productsSteps.getProductData(), productsSteps.setProductData(testData.getProductData()));
    }
}
