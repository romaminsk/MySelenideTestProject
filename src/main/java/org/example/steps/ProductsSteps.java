package org.example.steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.example.models.ProductData;
import org.example.page.CartPage;
import org.example.page.ProductsPage;

public class ProductsSteps {

    ProductsPage productsPage = new ProductsPage();
    CartPage cartPage = new CartPage();

    public SelenideElement findCheapestPrice() {

        double lowestPrice = Double.MAX_VALUE;
        SelenideElement cheapestProduct = null;

        for (SelenideElement productPrice : productsPage.productPricesText) {
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
        productsPage.shoppingCartLink.click();
    }

    public void verifyProductInCart(String productName) {
        cartPage.inventoryItemsNameText
                .filter(Condition.text(productName))
                .shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    public void clickFirstAddToCartButton() {
        productsPage.firstAddToCartButton.click();
    }

    public ProductData setProductData(ProductData productData) {
        productData.setProductName(productData.getProductName());
        productData.setProductPrice(productData.getProductPrice());
        productData.setProductDescription(productData.getProductDescription());
        return productData;
    }

    public ProductData getProductData() {
        ProductData productData = new ProductData();

        productData.setProductName(cartPage.inventoryItemsNameText.get(0).getText());
        productData.setProductPrice(cartPage.inventoryItemsPriceText.getText());
        productData.setProductDescription(cartPage.inventoryItemsDescriptionText.getText());
        return productData;
    }
}
