package models;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class Product  {
    private String id;
    private String name;
    private int modelYear;
    private double listPrice;
    private Brand brand;
    private Category category;

    
    public Product(String id, String name, int modelYear, double listPrice, Brand brand, Category category) {
        this.id = id;
        this.name = name;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
        this.brand = brand;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public double getListPrice() {
        return listPrice;
    }

    public void setListPrice(double listPrice) {
        this.listPrice = listPrice;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    @Override
    public String toString(){
    String str = String.format("%s %s %lf %d %s %s ", this.id, this.name, this.listPrice,this.modelYear,this.brand.getName(),this.category.getName());
    return str;
        

        
    }
}
