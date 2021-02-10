package ru.appline.dnsFramework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class CatalogPage extends BasePage{
    @FindBy(xpath = "//div[@class='catalog-item']")
    private List<WebElement> items;

    public ProductPage clickOnName(String name){
        for (WebElement item: items) {
            if (item.findElement(By.xpath("./div/div/div/div/div/div/a")).getText().toLowerCase().contains(name.toLowerCase())) {
                elementToBeClickable(item.findElement(By.xpath("./div/div/div/div/div/div/a"))).click();
                if(!productMark.isDisplayed())Assertions.fail("Не открылась карточка товара из каталога");
                return pages.getProductPage();
            }
        }
        Assertions.fail("Продукт '" + name + "' не найден");
        return pages.getProductPage();
    }
}
