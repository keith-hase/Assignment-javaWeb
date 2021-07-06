/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiet.orderdetail;

import java.io.Serializable;

/**
 *
 * @author keith
 */
public class OrderDetailDTO implements Serializable{
    private String nameItem;
    private int quantity;
    private float price, total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String nameItem, int quantity, float price, float total) {
        this.nameItem = nameItem;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
    
}
