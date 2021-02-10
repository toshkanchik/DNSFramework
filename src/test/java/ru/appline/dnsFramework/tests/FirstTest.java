package ru.appline.dnsFramework.tests;

import org.junit.jupiter.api.Test;
import ru.appline.dnsFramework.baseClasses.BaseTest;
import ru.appline.dnsFramework.utils.Product;

import static ru.appline.dnsFramework.utils.warrantyEnum.TWO_YEARS;

public class FirstTest extends BaseTest {
    @Test
    public void test(){
        Product playstation = new Product();
        Product playstationPlusWarranty = new Product();
        Product detroit = new Product();

        pages.getStartPage()
                .searchForCatalog("playstation")
                .clickOnName("PlayStation 4 Slim Black")
                .storeProduct(playstation)
                .chooseWarranty(TWO_YEARS)
                .storeProduct(playstationPlusWarranty)
                .clickBuy()
                .searchForProduct("detroit")
                .storeProduct(detroit)
                .clickBuy()
                .checkBasketSum(playstationPlusWarranty.getCost() + detroit.getCost());

        pages.getProductPage()
                .clickBasket()
                .checkItemHasWarranty("PlayStation 4 Slim Black", TWO_YEARS)
                .checkItemPrice("PlayStation 4 Slim Black", playstation.getCost())
                .checkItemPrice("detroit", detroit.getCost())
                .checkSum(playstationPlusWarranty.getCost() + detroit.getCost())
                .removeItem("detroit")
//                .checkNoItemByName("detroit") //Проверка выполняется внутри метода removeItem
                .checkSum(playstationPlusWarranty.getCost())
                .addOneMoreItem("PlayStation 4 Slim Black")
                .addOneMoreItem("PlayStation 4 Slim Black")
                .checkSum(playstationPlusWarranty.getCost() * 3)
                .getRemovedBack()
                .checkItemPrice("detroit", detroit.getCost()) //метод включает в себя проверку наличия элемента
                .checkSum(playstationPlusWarranty.getCost() * 3 + detroit.getCost())
        ;
    }
}
