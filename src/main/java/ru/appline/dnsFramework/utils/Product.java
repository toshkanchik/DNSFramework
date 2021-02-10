package ru.appline.dnsFramework.utils;

public class Product {
    private String name;
    private float price;
    private warrantyEnum warranty;
    private float warrantyPrice;

    public Product(){
        name = "";
        price = 0;
    }
    public Product(String name){
        this.name = name;
    }

    public void setAll(String name, float price, warrantyEnum warranty, float warrantyPrice){
        this.name = name;
        this.price = price;
        this.warranty = warranty;
        this.warrantyPrice = warrantyPrice;
    }

    public float getCost(){
        return price;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public warrantyEnum getWarranty() {
        return warranty;
    }

    public void setWarranty(warrantyEnum warranty) {
        this.warranty = warranty;
    }

    public float getWarrantyPrice() {
        return warrantyPrice;
    }

    public void setWarrantyPrice(float warrantyPrice) {
        this.warrantyPrice = warrantyPrice;
    }
}
