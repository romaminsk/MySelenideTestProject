package org.example.page;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    public ElementsCollection inventoryItemsNameText = $$(By.xpath("//div[@class='inventory_item_name']"));
}
