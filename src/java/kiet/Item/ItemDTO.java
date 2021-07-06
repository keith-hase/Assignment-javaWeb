/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiet.Item;

import java.io.Serializable;

/**
 *
 * @author keith
 */
public class ItemDTO implements Serializable{
    private String name;
    private int id;
    private float price;
    private boolean status;

    public ItemDTO() {
    }

    public ItemDTO(String name, int id, float price) {
        this.name = name;
        this.id = id;
       
        this.price = price;
  
    }
     public ItemDTO(String name, float price) {
        this.name = name;

       
        this.price = price;
  
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
