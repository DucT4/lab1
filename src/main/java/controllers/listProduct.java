/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.File;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.Product;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import models.Brand;
import models.Category;

/**
 *
 * @author Admin
 */
public class listProduct implements IItem<Product> {

    private List<Product> products;

    public listProduct() {
        this.products = new ArrayList<>();
    }

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
            if (p.getId().equalsIgnoreCase(id)) {
                proId = p;
            }
        }
        return proId;
    }

    @Override
    public boolean updateItem(String id) {
        listBrand brandList = new listBrand();
        listCategory categoryList = new listCategory();
        listProduct productList = new listProduct();
        productList.lead();
        brandList.lead();
        categoryList.lead();
        Product resultProduct = findId(id);

        if (resultProduct != null) {
            try {

                Scanner sc = new Scanner(System.in);
                //        
                System.out.print("Enter new name:");
                String name = sc.nextLine();
                if (name.isEmpty()) {
                    name = resultProduct.getName();
                }
                resultProduct.setName(name);
                System.out.print("Enter new model year:");
                String year = sc.nextLine();
                 int interYear = 0;
                 if (year.isEmpty()) {
                     interYear= resultProduct.getModelYear();
                 } else
                 {
                     while (!year.matches("^-?\\d+$" ) && interYear>2024) {
                         System.out.print("Enter year again:");
                         year = sc.nextLine();
                     }
                     interYear = Integer.parseInt(year);
                 }
               resultProduct.setModelYear(interYear);
                //
                System.out.print("Enter new list price:");
                String price = sc.nextLine();
               
                 double douPrice = 0;
                 if (price.isEmpty()) {
                     douPrice= resultProduct.getListPrice();
                 } else
                 {
                     while (!year.matches("^-?\\d+$" ) && douPrice>2024) {
                         System.out.print("Enter price again:");
                         year = sc.nextLine();
                     }
                     douPrice = Double.parseDouble(price);
                 }
               resultProduct.setListPrice(douPrice);
               
               
          
                System.out.print("Enter new brand id:");
                String brand_id = sc.nextLine();
                if (brand_id.isEmpty()) {
                    brand_id = resultProduct.getBrand().getId();
                }
                while (!brandList.checkId(brand_id.toUpperCase())) {
                    System.out.print("Please Enter brand id again:");
                    brand_id = sc.nextLine();
                }
                resultProduct.setBrand(brandList.getItem(brand_id));
                //
                System.out.print("Enter new category id:");
                String category_id = sc.nextLine();
                if (category_id.isEmpty()) {
                    category_id = resultProduct.getCategory().getId();
                }
                while (!categoryList.checkId(category_id.toUpperCase())) {
                    System.out.print("Please Enter category id again:");
                    category_id = sc.nextLine();
                }
                resultProduct.setCategory(categoryList.getItem(category_id));

                int position = this.products.indexOf(resultProduct);
                if (position == -1) {
                    System.out.println("Product not found!!");
                } else {
                    this.products.set(position, resultProduct);
                    System.out.println("updated!");
                }

                //product creat
//                Product pro = new Product(id, name, year, price, brandList.getItem(brand_id), categoryList.getItem(category_id));
                //add pro 
//                System.out.println(productList.addItem(pro) ? "Added successfull" : " Failed to added");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } else {
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
        File productFile = new File("01_product.txt");

        try {
            if (productFile.exists()) {
                PrintWriter printPro = new PrintWriter(productFile);
                for (Product p : products) {
                    String line = p.getId().toUpperCase() + "," + p.getName() + "," + p.getBrand().getId() + "," + p.getCategory().getId() + "," + p.getModelYear() + "," + p.getListPrice() + "\n";
                    printPro.print(line);
                    printPro.flush();
                }
                return true;
            }
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean lead() {
        File productFile = new File("01_product.txt");
        try {
            if (productFile.exists()) {
                List<String> allText = Files.readAllLines(productFile.toPath(), StandardCharsets.UTF_8);
                for (String line : allText) {
                    String txt[] = line.split(",");
                    if (txt.length == 6) {
                        String id = txt[0].trim();
                        String name = txt[1].trim();
                        listBrand brandList = new listBrand();
                        brandList.lead();
                        Brand brand = brandList.getItem(txt[2]);
                        //
                        listCategory categoryList = new listCategory();
                        categoryList.lead();
                        Category category = categoryList.getItem(txt[3]);
                        int modelyear = Integer.parseInt(txt[4]);
                        double listPrice = Double.parseDouble(txt[5]);
                        Product product = new Product(id, name, modelyear, listPrice, brand, category);
                        this.addItem(product);
                    }

                }
                return true;
            }

            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

}
