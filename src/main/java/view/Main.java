/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controllers.listBrand;
import controllers.listCategory;
import controllers.listProduct;
import java.util.List;
import java.util.Scanner;
import models.Product;

/**
 *
 * @author Admin
 */
public class Main {

    public static void addProduct() {
        try {
            listBrand brandList = new listBrand();
            listCategory categoryList = new listCategory();
            listProduct productList = new listProduct();
            brandList.lead();
            categoryList.lead();
//--------------------------------------------------------------------
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter id:");
            String id = sc.nextLine();
            while (id.isEmpty()) {
                System.out.print("Please Enter  id again:");
                id = sc.nextLine();
            }
            System.out.print("Enter name:");
            String name = sc.nextLine();
            System.out.print("Enter model year:");
            int year = sc.nextInt();
            while (year > 2024) {
                System.out.print("Please Enter year again (year Invalid!!!!):");
                year = sc.nextInt();
            }
            System.out.print("Enter list price:");
            double price = sc.nextDouble();
            while (price <= 0) {
                System.out.print("Please Enter price is positive number:");
                price = sc.nextDouble();

            }
            sc.nextLine();
            System.out.print("Enter brand id:");
            String brand_id = sc.nextLine();
            while (!brandList.checkId(brand_id.toUpperCase())) {
                System.out.print("Please Enter brand id again:");
                brand_id = sc.nextLine();
            }
            System.out.print("Enter category id:");
            String category_id = sc.nextLine();

            while (!categoryList.checkId(category_id.toUpperCase())) {
                System.out.print("Please Enter category id again:");
                category_id = sc.nextLine();
            }
            //product creat

            Product pro = new Product(id, name, year, price, brandList.getItem(brand_id), categoryList.getItem(category_id));
            //add pro 
            System.out.println(productList.addItem(pro) ? "Added successfull" : " Failed to added");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            System.out.println("===== BIKE STORE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add a product");
            System.out.println("2. Search product by name");
            System.out.println("3. Update product");
            System.out.println("4. Delete product");
            System.out.println("5. Save products to file");
            System.out.println("6. Print list products from the file");
            System.out.println("7. Quit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:

                    sc.nextLine();
                    System.out.print("Enter name:");
                    String searchName = null;
                    searchName = sc.nextLine();
                    listProduct pro = new listProduct();
                    List<Product> resultByName = pro.searchByName(searchName);

                    if (resultByName.isEmpty()) {
                        System.out.print("Not found!!");
                    } else {
                        System.out.print("List of product that you search:");
                        for (Product p : resultByName) {
                            System.out.println(p);
                        }
                    }

                    break;
                case 3:
                    //update
                    System.out.print("Enter id update:");
                    String idUpdate = sc.nextLine();
                    listProduct updatePro = new listProduct();
                    updatePro.updateItem(idUpdate);

                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;

            }
        } while (choice <= 7);
    }
}
