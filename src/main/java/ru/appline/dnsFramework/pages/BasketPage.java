package ru.appline.dnsFramework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.dnsFramework.utils.warrantyEnum;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class BasketPage extends BasePage{
    @FindBy(xpath = "//div[@class='cart-items__product']")
    private List<WebElement> itemsInBasket;

    @FindBy(xpath = "//div[@class='total-amount__label']/div/div[@class='price__block " +
            "price__block_main']/span[@class='price__current']")
    private WebElement sumField;

    @FindBy(xpath = "//div[@class='group-tabs-menu']/span/span[@class='restore-last-removed']")
    private WebElement restoreRemoved;

    private WebElement item;

    public BasketPage checkItemHasWarranty(String item, warrantyEnum warranty) {
        List<WebElement> warrantyFields = findItemByName(item).
                findElements(By.xpath("./div/div[@class='cart-items__additionals']/div/" +
                        "div[@class='additional-warranties-row__warranties']/div/div/div/div/div/.."));
        String classField;
        String text;
        String shouldHave;
        switch (warranty){
            case NONE:
                shouldHave = "Без";
                break;
            case ONE_YEAR:
                shouldHave = "12";
                break;
            case TWO_YEARS:
                shouldHave = "24";
                break;
            default:
                shouldHave = "";
        }

        for(WebElement warrantyField: warrantyFields){
            classField = warrantyField.findElement(By.xpath("./div/span")).getAttribute("class");
            text = warrantyField.findElement(By.xpath("./div/span")).getText().trim();
            if(text.contains(shouldHave))
                {if(!classField.contains("checked"))Assertions.fail("Нужная гарантия не выбрана");}
            else{
                if(classField.contains("checked"))Assertions.fail("Выбрана неправильная гарантия");
            }
        }
        return pages.getBasketPage();
    }

    private WebElement findItemByName(String name){
        for (WebElement item:itemsInBasket){
            if(item.findElement(By.xpath("./div/div/div[@class='cart-items__product-info']" +
                    "/div/div[@class='cart-items__product-name']/a")).getText().toLowerCase().contains(name.toLowerCase()))
                return item;
        }
        Assertions.fail("Не удалось найти товар по имени");
        return null;
    }

    public BasketPage checkItemPrice(String itemName, float cost) {
        String price = findItemByName(itemName).findElement(By.xpath("./div/div/div[@class='cart-items__product-info']/"+
                "div[@class='cart-items__product-block-amount']/div[@class='cart-items__product-price']"+
                "/div/div/span[@class='price__current']")).getText();
        Assertions.assertEquals(Float.parseFloat(price.replaceAll("[^\\d]","")), cost,
                "При проверке цены возникло несоответствие");
        return pages.getBasketPage();
    }

    public BasketPage checkSum(float cost) {
        Assertions.assertEquals(Float.parseFloat(sumField.getText().replaceAll("[^\\d]","")), cost,
                "При проверке суммы товаров возникло несоответсвие");
        return pages.getBasketPage();
    }

    public BasketPage removeItem(String itemName) {
        item = findItemByName(itemName);
        item.findElement(By.xpath("./div/div/div[@class='cart-items__product-info']/div/div/div" +
                "/button[.='Удалить']")).click();
        waitForRemove(item);
        return checkNoItemByName(itemName);
    }

    public BasketPage checkNoItemByName(String itemName) {
        for (WebElement item:itemsInBasket){
            if(item.findElement(By.xpath("./div/div/div[@class='cart-items__product-info']" +
                    "/div/div[@class='cart-items__product-name']/a")).getText().toLowerCase().contains(itemName.toLowerCase())) {
                Assertions.fail("Продукт '" + itemName + "' находится в корзине");
                return null;
            }
        }
        return pages.getBasketPage();
    }

    public BasketPage addOneMoreItem(String itemName) {
        item = findItemByName(itemName).findElement(By.xpath("./div/div/div[@class='cart-items__product-info']/div/div/div/input"));
        int num = Integer.parseInt(item.getAttribute("value").replaceAll("[^\\d]",""));
        elementToBeClickable(findItemByName(itemName).findElement(By.xpath("./div/div/div[@class='cart" +
                "-items__product-info']/div/div/div/button[@data-commerce-action='CART_ADD']"))).click();
        elementExpectedConditions(item, "value", Integer.toString(num + 1));
        return pages.getBasketPage();
    }

//    @FindBy(xpath = "//a[.='Печать']")
//    private WebElement print;

    @FindBy(xpath = "//div[@class='cart-products-count']")
    private WebElement numInBasket;

    public BasketPage getRemovedBack() {
        String tmp = numInBasket.getText();
        elementToBeClickable(restoreRemoved).click();

//        item = restoreRemoved;
//        waitForRemove(item);
//        wait.until(() -> {return (getDriver()
//                .findElement(By.xpath("//a[.='Печать']"))
//                .getAttribute("nextElementSibling") == null);});
//        elementExpectedConditions(print,"nextElementSibling", "");

//        wait.until(ExpectedConditions.attributeToBe(print, "nextElementSibling", ""));
//        wait.until(ExpectedConditions.not(attributeToBe(numInBasket, "value", tmp)));
        wait.until(ExpectedConditions.not(textToBePresentInElement(numInBasket, tmp)));

        return pages.getBasketPage();
    }
}
