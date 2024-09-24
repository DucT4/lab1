package models;

import java.util.Comparator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
public class Product implements Comparable<Product> {

    private String id;
    private String name;
    private int modelYear;
    private double listPrice;
    private Brand brand;
    private Category category;

    public Product() {

    }

   
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
    public String toString() {

        String str = String.format(""
                + "%s    %s     %.2f       %d         %s     %s ", this.id, this.name, this.listPrice, this.modelYear,
                this.brand.getName(), this.category.getName());
        return str;

    }
    @Override
    public int compareTo(Product o) {
        return this.id.compareTo(o.id);
    }
    
     public int compareToListPrice(Product o) {
        // if (this.listPrice > o.listPrice)
        //     return 1;
        // else if (this.listPrice < o.listPrice)
        //     return -1;
        // else {
        //     return 0;
        // }
        // Không thể sử dụng phương thức compareTo() trực tiếp trên kiểu dữ liệu nguyên thủy double
        // Thay vào đó, ta có thể sử dụng phép so sánh trực tiếp hoặc Double.compare()
        return Double.compare(this.listPrice, o.listPrice);
    }
}
