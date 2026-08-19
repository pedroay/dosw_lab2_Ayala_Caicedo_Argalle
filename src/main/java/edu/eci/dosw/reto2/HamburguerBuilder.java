package edu.eci.dosw.reto2;

import java.util.ArrayList;
import java.util.HashMap;


public class HamburguerBuilder implements BuilderFastFood {

    public double price;
   

    private ArrayList<Ingredient> ingredients;
    private HashMap<String,Integer> quantities;

    public HamburguerBuilder(){
        this.ingredients = new ArrayList<Ingredient>();
        this.quantities = new HashMap<String,Integer>();
    }
    
    public void setBread(Ingredient bread){
        ingredients.add(bread);
        quantities.put(bread.getName(), quantities.getOrDefault(bread.getName(), 0) + 1);

    }

     public void setTomato(Ingredient tomato){
        ingredients.add(tomato);
        quantities.put(tomato.getName(), quantities.getOrDefault(tomato.getName(), 0) + 1);
     }

    public void setMeat(Ingredient meat){
        ingredients.add(meat);
        quantities.put(meat.getName(), quantities.getOrDefault(meat.getName(), 0) + 1);
    }

    public void setCheese(Ingredient cheese){
        ingredients.add(cheese);
        quantities.put(cheese.getName(), quantities.getOrDefault(cheese.getName(), 0) + 1);
    }

    public double calculatePrice(){
        double precioTotal = ingredients.stream()
                              .mapToDouble(Ingredient::getPrice)
                              .sum()
                              ;                        
        this.price = precioTotal;
        return this.price;
    }
}
