/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

/**
 *
 * @author Riverto
 */
public class Article {
    public int sku;
    private int existence;
    private double price;
    private double cost;
    public String description;
    
    public Article(int sku, double price, String description){
        this.sku=sku;
        this.existence=0;
        this.price=price;
        this.cost=0;
        this.description=description;
    }
    public Article(){
        this.sku=00000000;
        this.existence=0;
        this.price=0;
        this.cost=0;
        this.description="n/a";
    }
    public void purchase(int cant, double cost){
        this.existence+=cant;
        this.cost+=cost;
    }
    public boolean sale(int quantity){
        if(quantity>0&&quantity<this.existence){
            this.existence-=quantity;
            return true;
        } else return false;
    }
    public int checkExistence(){
        return this.existence;
    }
    public void updatePrice(double price){
        if(price>0) this.price=price;
    }
}
