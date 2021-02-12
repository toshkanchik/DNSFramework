package ru.appline.dnsFramework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.appline.dnsFramework.utils.Product;
import ru.appline.dnsFramework.utils.warrantyEnum;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class ProductPage extends BasePage{
    @FindBy(xpath = "//h1[@class='page-title product-card-top__title']")
    private WebElement header;
    @FindBy(xpath = "//span[contains(@class, 'product-card-price__current')]")
    private WebElement price;
    @FindBy(xpath = "//select[@class='ui-input-select product-warranty__select']")
    private WebElement warranty;
    @FindBy(xpath = "//div[@class='primary-btn']/button")
    private WebElement buttonBuy;

    public ProductPage storeProduct(Product product) {
        product.setName(header.getText());
        product.setPrice(Float.parseFloat(price.getText().replaceAll("[^\\d]","")));
        return pages.getProductPage();
    }

    public ProductPage chooseWarranty(warrantyEnum val) {
        String tmp = price.getText();
        Select selector = new Select(warranty);
        switch(val) {
            case NONE: selector.selectByValue("default");
            case ONE_YEAR: selector.selectByValue("0");
            case TWO_YEARS: selector.selectByValue("1");
        }
        wait.until(ExpectedConditions.not(textToBePresentInElement(price, tmp)));
        return pages.getProductPage();
    }

    public ProductPage clickBuy() {
        elementToBeClickable(buttonBuy);
        Assertions.assertEquals(buttonBuy.getText(),"Купить", "Убедитесь, что такого товара нет в корзине");
        buttonBuy.click();
        wait.until(ExpectedConditions.textToBePresentInElement(buttonBuy, "В корзине"));
        return pages.getProductPage();
    }
}
