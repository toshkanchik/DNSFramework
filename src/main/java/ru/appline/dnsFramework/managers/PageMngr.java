package ru.appline.dnsFramework.managers;

import ru.appline.dnsFramework.pages.*;

public class PageMngr {
    private static PageMngr pageMngr;

    private PageMngr(){}
    public static PageMngr getPageMngr(){
        if(pageMngr == null){
            pageMngr = new PageMngr();
        }
        return pageMngr;
    }


    private StartPage startPage;
    /**
     *
     * @return StartPage
     */
    public StartPage getStartPage(){
        if(startPage == null){
            startPage = new StartPage();
        }
        return startPage;
    }


    private CatalogPage catalogPage;
    /**
     *
     * @return CatalogPage
     */
    public CatalogPage getCatalogPage() {
        if(catalogPage == null){
            catalogPage = new CatalogPage();
        }
        return catalogPage;
    }


    private ProductPage productPage;
    /**
     *
     * @return CatalogPage
     */
    public ProductPage getProductPage() {
        if(productPage == null){
            productPage = new ProductPage();
        }
        return productPage;
    }

    private BasketPage basketPage;

    public BasketPage getBasketPage() {
        if(basketPage == null){
            basketPage = new BasketPage();
        }
        return basketPage;
    }
}
