package org.example.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    public ElementsCollection inventoryItemsNameText = $$(By.xpath("//div[@class='inventory_item_name']"));
    public SelenideElement inventoryItemsPriceText = $(By.xpath("//div[@class='inventory_item_price']"));
    public SelenideElement inventoryItemsDescriptionText = $(By.xpath("//div[@class='inventory_item_desc']"));
}
