package edu.eci.dosw.reto2;

public  class Ingredient {
    protected String name;
    protected double price;

    public Ingredient(String name, double price){
        this.name = name;
        this.price = price;
    }

    public double getPrice(){
        return this.price;
    }

    public String getName(){
        return this.name;
    }
}
