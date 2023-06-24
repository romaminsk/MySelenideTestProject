package org.example.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsPage {

    public ElementsCollection productPricesText = $$(By.xpath("//div[@class='inventory_item_price']"));
    public SelenideElement shoppingCartLink = $(By.xpath("//a[@class='shopping_cart_link']"));
    public SelenideElement titleText = $(By.xpath("//span[@class='title']"));
    public SelenideElement firstAddToCartButton = $$(By.id("add-to-cart-sauce-labs-backpack")).first();
}
