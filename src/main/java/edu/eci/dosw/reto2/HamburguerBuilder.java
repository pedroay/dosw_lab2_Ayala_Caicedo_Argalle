package edu.eci.dosw.reto2;

import java.util.ArrayList;


public class HamburguerBuilder implements BuilderFastFood {

    public double price;
   

    private ArrayList<Ingredient> ingredients;

    public HamburguerBuilder(){
        this.ingredients = new ArrayList<Ingredient>();
    }
    
    public void setBread(Ingredient bread){
        ingredients.add(bread);
    }

     public void setTomato(Ingredient tomato){
        ingredients.add(tomato);
     }

    public void setMeat(Ingredient meat){
        ingredients.add(meat);
    }

    public void setCheese(Ingredient cheese){
        ingredients.add(cheese);
    }

    public double calculatePrice(){
        double precioTotal = ingredients.stream()
                              .mapToDouble(Ingredient::getPrice) // Extrae el precio de cada objeto
                              .sum()
                              ;                        
        this.price = precioTotal;
        return this.price;
    }
}
