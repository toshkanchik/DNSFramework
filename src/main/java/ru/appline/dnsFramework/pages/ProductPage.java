package ru.appline.dnsFramework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ru.appline.dnsFramework.utils.Product;
import ru.appline.dnsFramework.utils.warrantyEnum;

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
        Select selector = new Select(warranty);
        switch(val) {
            case NONE: selector.selectByValue("default");
            case ONE_YEAR: selector.selectByValue("0");
            case TWO_YEARS: selector.selectByValue("1");
        }
        try {//приходится делать именно слипом, что-бы метод был универсальным (при переключении с 1 года на 2 не меняется ничего, кроме цены)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pages.getProductPage();
    }

    public ProductPage clickBuy() {
        Assertions.assertEquals(buttonBuy.getText(),"Купить", "Убедитесь, что такого товара нет в корзине");
        elementToBeClickable(buttonBuy);
        buttonBuy.click();
        try {//для проверки успешного клика необходимо подождать, пока текст в кнопке не поменяется
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(buttonBuy.getText(),"В корзине", "При добавлении в корзину произошла ошибка");
        return pages.getProductPage();
    }
}
