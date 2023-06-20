package org.example.steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsSteps {

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
}
