package com.example.simplechef;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class RecipeClass {

    private ArrayList<Ingredients> ingredientList = new ArrayList<>();
    private String name, description, cost, time, steps;
    private Bitmap image;


    //Constructor
    public RecipeClass() {

    }
    //Constructor
    public RecipeClass(String name, String description, String steps) {
        this.name = name;
        this.description = description;
        this.steps = steps;
    }
    public void AddIngredient(String name, Double price, String quantity){
        Ingredients obj = new Ingredients(name, price, quantity);
        ingredientList.add(obj);
    }



    //GETTERS && SETTERS
    public ArrayList<Ingredients> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(ArrayList<Ingredients> ingredientList) {
        this.ingredientList = ingredientList;
    }
    public Ingredients getIngredientAtIndex(int index){
        return ingredientList.get(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
