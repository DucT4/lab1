/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controllers.ListBrand;
import controllers.ListCategory;
import controllers.ListProduct;
import java.util.List;
import java.util.Scanner;
import models.Product;

/**
 *
 * @author Admin
 */
public class Main {

    public static Product addProduct() {
        try {
            ListBrand brandList = new ListBrand();
            ListCategory categoryList = new ListCategory();
            ListProduct productList = new ListProduct();
            productList.load();
            brandList.load();
            categoryList.load();
            // --------------------------------------------------------------------

            Scanner sc = new Scanner(System.in);

            // Yêu cầu người dùng nhập ID sản phẩm
            System.out.print("Enter id (format: A followed by 3 digits):");
            String id = sc.nextLine().trim().toUpperCase();
            
            // Vòng lặp kiểm tra tính hợp lệ của ID
            // Vòng lặp kiểm tra tính hợp lệ của ID
            while (!id.matches("A\\d{3}") || productList.checkId(id)) {
                // Điều kiện này kiểm tra hai trường hợp:
                // 1. !id.matches("A\\d{3}"): Kiểm tra xem ID không khớp với mẫu "A" theo sau bởi 3 chữ số
                // 2. productList.checkId(id): Kiểm tra xem ID đã tồn tại trong danh sách sản phẩm
                // Vòng lặp tiếp tục nếu một trong hai điều kiện trên là đúng
                if (!id.matches("A\\d{3}")) {
                    // Nếu ID không đúng định dạng (A + 3 số), yêu cầu nhập lại
                    System.out.print("Invalid format. Please enter id in the format A followed by 3 digits: ");
                } else {
                    // Nếu ID đã tồn tại trong danh sách sản phẩm, yêu cầu nhập ID khác
                    System.out.print("ID already exists. Please enter a different id: ");
                }
                // Nhập lại ID
                id = sc.nextLine().trim().toUpperCase();
            }
            // Kết thúc vòng lặp khi ID hợp lệ và chưa tồn tại
            System.out.print("Enter name:");
            String name = sc.nextLine();

            // --------------------------------------------------------------------------
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
            // product creat

            Product pro = new Product(id, name, year, price, brandList.getItem(brand_id),
                    categoryList.getItem(category_id));
            return pro;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListProduct pro = new ListProduct();
        boolean continuePro = true;
        pro.load();
        int choice;
        do {
            System.out.println("===== BIKE STORE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add a product");
            System.out.println("2. Search product by name");
            System.out.println("3. Update product");
            System.out.println("4. Delete product");
            System.out.println("5. Save products to file");
            System.out.println("6. Print list products from the file");
            System.out.println("7. Sort product by id");
            System.out.println("8. Sort product by price");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Thêm dòng này
            switch (choice) {

                case 1:
                    Product product = addProduct();
                    System.out.println(pro.addItem(product) ? "Added successfull" : " Failed to added");

                    break;
                case 2:

                    sc.nextLine();
                    System.out.print("Enter name:");
                    String searchName = null;
                    searchName = sc.nextLine();

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
                    // update
                    sc.nextLine();
                    System.out.print("Enter id update:");
                    String idUpdate = sc.nextLine();
                    System.out.println(pro.updateItem(idUpdate) ? "Updated successfully!!" : "Failed to delete!!!");
                    break;
                case 4:
                    // delete
                    System.out.print("Enter id delele:");
                    sc.nextLine();
                    String idDelete = sc.nextLine();

                    System.out.println(pro.deleteItem(idDelete) ? "Deleted successfull!!" : " Failed to delete!!!");

                    break;
                case 5:
                    System.out.println(pro.save() ? "Save data successfully" : "Failed to save!!!");
                    break;
                case 6:
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.println("ID      NAME        LISTPRICE     MODELYEAR     BRAND     CATEGORY   ");
                    pro.displayAll();
                    System.out.println("---------------------------------------------------------------------------");
                    break;
                case 7:
                    System.out.println("Sort by product id");
                    pro.sortProduct();
                    pro.displayAll();
                    break;

                    case 8:
                    System.out.println("Sort by product price");
                    pro.sortProductByPrice();
                    pro.displayAll();
                    break;
            } 
            System.out.print("Do you want to continue? (y/n): ");
            String continueChoice = sc.nextLine();

            if (!continueChoice.equalsIgnoreCase("y")) {
                continuePro = false;
                break;
            }

        } while (true);
    }
}
