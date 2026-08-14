package edu.eci.dosw.reto2;

public class Restaurant {
    private HamburguerBuilder hamburguer;
    private Ingredient bread = new Ingredient("Bread", 2000);
    private Ingredient tomato = new Ingredient("Tomato", 1200);;
    private Ingredient cheese = new Ingredient("Cheese", 3500);
    private Ingredient meat = new Ingredient("Meat", 8000);

    public Restaurant() {
    }

    public void newHamburguer(){
        this.hamburguer = new HamburguerBuilder();
    }

    public void withBread(int cantidad){
        for(int i=0;i < cantidad;i ++){
        hamburguer.setBread(this.bread);
        }
    }

    public void withTomato(int cantidad){
        for(int i=0;i < cantidad;i ++){
        hamburguer.setTomato(this.tomato);
        }
    }

    public void withCheese(int cantidad){
        for(int i = 0; i < cantidad; i++){
            hamburguer.setCheese(this.cheese);
        }
    }

    public void withMeat(int cantidad){
        for(int i = 0; i < cantidad; i++){
            hamburguer.setMeat(this.meat);
        }
    }
}
