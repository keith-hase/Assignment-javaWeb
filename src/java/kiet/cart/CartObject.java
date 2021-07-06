/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kiet.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author keith
 */
public class CartObject implements Serializable{
    private Map<String,Integer> items;
    
    public Map<String,Integer> getItem(){
        return items;
    }
    public void addToCart(String id){
        if (id == null) {
            
        }
        if (id.trim().isEmpty()) {
            return;
        }
        if(this.items == null){
            this.items = new HashMap<>();
        }
        //3. Check id exited??
        int quantity = 1;
        if(this.items.containsKey(id)){
            quantity = this.items.get(id) + 1;
        }
        this.items.put(id, quantity);
    }
    
    public void removeItemFromCart(String id){
        if (this.items == null ||this.items.isEmpty()) {
            return ;
        }
        if (this.items.containsKey(id)) {
            this.items.remove(id);
        
        }
        
    }
}
