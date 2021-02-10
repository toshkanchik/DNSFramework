package ru.appline.dnsFramework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.dnsFramework.managers.PageMngr;

import static ru.appline.dnsFramework.managers.DriverMngr.getDriver;

public class BasePage {
    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }
    protected PageMngr pages = PageMngr.getPageMngr();
    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();
    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);

    @FindBy(xpath = "//nav[@id='header-search']/div/form/div/input")
    private WebElement searchInput;

    @FindBy(xpath = "//div[@id='catalog-items-page']")
    private WebElement catalogMark;

    @FindBy(xpath = "//div[@class='product-breadcrumbs']")
    protected WebElement productMark;

    @FindBy(xpath = "//a[@class='ui-link cart-link']")///span/span[@class='cart-link__price']
    private WebElement basket;

    @FindBy(xpath = "//div[@class='cart-page-new']")
    private WebElement basketMark;

    public CatalogPage searchForCatalog(String searchFor){
        fillInputField(searchInput, searchFor+"\n");
        if(!catalogMark.isDisplayed())Assertions.fail("Не открылся каталог с товарами при поиске");
        return pages.getCatalogPage();
    }

    public ProductPage searchForProduct(String searchFor){
        fillInputField(searchInput, searchFor+"\n");
        if(!productMark.isDisplayed()) Assertions.fail("Не открылась карточка товара при прямом поиске");
        return pages.getProductPage();
    }

    protected void fillInputField(WebElement field, String value) {
        scrollToElementJs(field);
        elementToBeClickable(field).click();
        field.sendKeys(value);
    }
    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    protected WebElement elementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected Boolean elementExpectedConditions(WebElement element, String attribute, String value) {
        return wait.until(ExpectedConditions.attributeContains(element,attribute, value));
    }
    protected Boolean waitForRemove(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOfAllElements(element));
    }


    public void checkBasketSum(float value) {
        Assertions.assertEquals(Float.parseFloat(basket.findElement(By.xpath("./span/span[@class='cart-link__price']"))
                        .getText().replaceAll("[^\\d]","")),value,"Сумма в корзине отличается от введённой");
    }

    public BasketPage clickBasket() {
        elementToBeClickable(basket);
        basket.click();
        if(!basketMark.isDisplayed())Assertions.fail("Не открылась корзина");
        return pages.getBasketPage();
    }
}
