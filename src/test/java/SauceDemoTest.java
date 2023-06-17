import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SauceDemoTest extends BaseTest {

    public void login(String username, String password) {
        $(By.id("user-name")).setValue(username);
        $(By.id("password")).setValue(password);
        $(By.id("login-button")).click();
    }

    public SelenideElement findCheapestPrice() {
        ElementsCollection productPrices = $$(By.xpath("//div[@class=\"inventory_item_price\"]"));

        double lowestPrice = Double.MAX_VALUE;
        SelenideElement cheapestProduct = null;

        for (SelenideElement productPrice : productPrices) {
            SelenideElement product = productPrice.closest("div[contains(@class, 'inventory_item')]");
            String priceText = productPrice.getText().replace("$", "");
            double price = Double.parseDouble(priceText);

            if (price < lowestPrice) {
                lowestPrice = price;
                cheapestProduct = product;
            }
        }
        return cheapestProduct;
    }

    public void addProductToCart(SelenideElement product) {
        product.find(".btn_primary").click();
    }

    public void goToCart() {
        $(".shopping_cart_link").click();
    }

    public void verifyProductInCart(String productName) {
        $$(".inventory_item_name")
                .filter(Condition.text(productName))
                .shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    @Test
    public void loginTest() {
        login("standard_user", "secret_sauce");

        $(By.xpath("//span[@class='title']")).shouldHave(text("Products"));
    }

    @Test
    public void addItemToCart() {
        login("standard_user", "secret_sauce");
        SelenideElement cheapestProduct = findCheapestPrice();
        String productName = cheapestProduct.find(".inventory_item_name").getText();
        addProductToCart(cheapestProduct);
        goToCart();
        verifyProductInCart(productName);
    }
}
