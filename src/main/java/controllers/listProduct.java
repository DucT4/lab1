/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Product;

/**
 *
 * @author Admin
 */
public class listProduct implements IItem<Product> {
    
    public List<Product> products = new ArrayList<>();    
    
    public void displayAll() {
        for (Product p : products) {
            System.out.println(p);
        }
    }
    
    @Override
    public Product getItem(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
    
    @Override
    public boolean addItem(Product Item) {
        return products.add(Item);
    }

    //tim kiem ten theo name
    public List<Product> searchByName(String name) {
        List<Product> resultPro = new ArrayList<>();
        
        for (Product p : products) {
            if (p.getName().toUpperCase().contains(name.toUpperCase())) {
                resultPro.add(p);
            }
        }
        return resultPro;
    }

    //tim sp theo id
    public Product findId(String id) {
        Product proId = null;
        for (Product p : products) {
            if (p.getId() == id) {
                proId = p;
            }
        }
        return proId;
    }
    
    @Override
    public boolean updateItem(String id) {
        Product resultProduct = findId(id);
        if (resultProduct != null) {
            try {
                 listBrand brandList = new listBrand();
            listCategory categoryList = new listCategory();
            listProduct productList = new listProduct();
            brandList.lead();
            categoryList.lead();
            Scanner sc = new Scanner(System.in);
            //        
            System.out.print("Enter new name:");
            String name = sc.nextLine();
            resultProduct.setName(name);
            System.out.print("Enter new model year:");
            int year =sc.nextInt();
          
            while (year > 2024) {
                System.out.print("Please Enter year again (year Invalid!!!!):");
                year = sc.nextInt();
            }
             resultProduct.setModelYear( year);
             //
            System.out.print("Enter new list price:");
            double price = sc.nextDouble();
            while (price <= 0) {
                System.out.print("Please Enter price is positive number:");
                price = sc.nextDouble();
                
            }
            resultProduct.setListPrice(price);
            sc.nextLine();
            System.out.print("Enter new brand id:");
            String brand_id = sc.nextLine();
            while (!brandList.checkId(brand_id.toUpperCase())) {
                System.out.print("Please Enter brand id again:");
                brand_id = sc.nextLine();
            }
                  resultProduct.setBrand(brandList.getItem(brand_id));
                  //
            System.out.print("Enter new category id:");
            String category_id = sc.nextLine();
            
            while (!categoryList.checkId(category_id.toUpperCase())) {
                System.out.print("Please Enter category id again:");
                category_id = sc.nextLine();
            }
                          resultProduct.setCategory(categoryList.getItem(category_id));

            //product creat

            Product pro = new Product(id, name, year, price, brandList.getItem(brand_id), categoryList.getItem(category_id));
            //add pro 
            System.out.println(productList.addItem(pro) ? "Added successfull" : " Failed to added");
            
            } catch (Exception e) {
               e.printStackTrace();
            }
           return true;
        } else
        {
            System.out.println("Product not found!!");
            return false;
        }
        
    }
    
    @Override
    public boolean deleteItem(Product Item) {
        return products.remove(Item);
    }
    
    @Override
    public boolean deleteItem(String id) {
        Product product = getItem(id);
        if (product != null) {
            return products.remove(product);
        }
        return false;
    }
    
    @Override
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public boolean lead() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }
    
    @Override
    public List<Product> getAll() {
        return products;
    }
    
}
