package com.example.inventorymanagementapplication;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Inventory {
    StringProperty serialNumber = new SimpleStringProperty();
    StringProperty productPrice = new SimpleStringProperty();
    StringProperty productName = new SimpleStringProperty();

    public String getSerialNumber() {
        return serialNumber.get();
    }

    public StringProperty serialNumberProperty() {
        return serialNumber;
    }

    public String getProductPrice() {
        return productPrice.get();
    }

    public StringProperty productPriceProperty() {
        return productPrice;
    }

    public String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber.set(serialNumber);
    }

    public void setProductPrice(String productPrice) {
        this.productPrice.set(productPrice);
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

}

