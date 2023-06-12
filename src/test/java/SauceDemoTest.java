import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

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

    @Test
    public void findCheapestItemAndAddToBasket() {
        // Open the Sauce Demo website
        open("https://www.saucedemo.com/");

        // Perform login
        login("standard_user", "secret_sauce");

        // Find the product with the lowest price
        SelenideElement cheapestProduct = findCheapestProduct();

        // Get the product name and price
        String productName = cheapestProduct.find(".inventory_item_name").getText();
        String productPrice = cheapestProduct.find(".inventory_item_price").getText();

        // Add the cheapest product to the shopping cart
        addProductToCart(cheapestProduct);

        // Verify that the product is added to the cart
        verifyProductInCart(productName);

        // Go to the cart page
        goToCart();

        // Verify that the product is in the cart
        verifyProductInCart(productName);

        // Verify the product price in the cart
        verifyProductPriceInCart(productPrice);
    }

    private void login(String username, String password) {
        $("#user-name").setValue(username);
        $("#password").setValue(password);
        $(".btn_action").click();
    }

    private SelenideElement findCheapestProduct() {
        // Get all the product prices
        ElementsCollection productPrices = $$(By.xpath("//div[@class='inventory_item_price']"));

        // Initialize variables to track the cheapest price and product element
        double lowestPrice = Double.MAX_VALUE;
        SelenideElement cheapestProduct = null;

        // Iterate through each product price
        for (SelenideElement productPrice : productPrices) {
            // Get the parent product element
            SelenideElement product = productPrice.closest("div[contains(@class,'inventory_item')]");

            // Get the price as a double value
            String priceText = productPrice.getText().replace("$", "");
            double price = Double.parseDouble(priceText);

            // Update the lowest price and product if the current price is lower
            if (price < lowestPrice) {
                lowestPrice = price;
                cheapestProduct = product;
            }
        }

        // Ensure that the cheapest product element is found
        assert cheapestProduct != null : "Cheapest product not found";

        return cheapestProduct;
    }

    private void addProductToCart(SelenideElement product) {
        product.find(".btn_primary").click();
    }

    private void verifyProductInCart(String productName) {
        $(".shopping_cart_badge").shouldHave(Condition.text("1"));
        $$(".inventory_item_name")
                .filter(Condition.text(productName))
                .shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    private void goToCart() {
        $(".shopping_cart_link").click();
    }

    private void verifyProductPriceInCart(String productPrice) {
        $(".inventory_item_price")
                .shouldHave(Condition.text(productPrice));
    }
}
