/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import manageFile.ManageFile;
import models.Product;
import models.Brand;
import models.Category;
import java.util.Collections;


/**
 *
 * @author Admin
 */
public class ListProduct implements IItem<Product> {

    private static final String FILE_NAME = "01_product.txt";

    private List<Product> products;

    public ListProduct() {
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

    // tim kiem ten theo name
    public List<Product> searchByName(String name) {
        List<Product> resultPro = new ArrayList<>();

        for (Product p : products) {
            if (p.getName().toUpperCase().contains(name.toUpperCase())) {
                resultPro.add(p);
            }
        }
        return resultPro;
    }

    // tim sp theo id
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
        ListBrand brandList = new ListBrand();
        ListCategory categoryList = new ListCategory();
        ListProduct productList = new ListProduct();
        productList.load();
        brandList.load();
        categoryList.load();
        Product resultProduct = findId(id);

        if (resultProduct != null) {
            try {

                // Scanner sc = new Scanner(System.in);
                // //
                // System.out.print("Enter new name:");
                // String name = sc.nextLine();
                // if (name.isEmpty()) {
                // name = resultProduct.getName();
                // }
                // resultProduct.setName(name);
                // System.out.print("Enter new model year:");
                // String year = sc.nextLine();
                // int interYear = 0;
                // if (year.isEmpty()) {
                // interYear = resultProduct.getModelYear();
                // } else {
                // while (!year.matches("^-?\\d+$") && interYear > 2024) {
                // System.out.print("Enter year again:");
                // year = sc.nextLine();
                // }
                // interYear = Integer.parseInt(year);
                // }
                // resultProduct.setModelYear(interYear);
                // //
                // System.out.print("Enter new list price:");
                // String price = sc.nextLine();

                // double douPrice = 0;
                // if (price.isEmpty()) {
                // douPrice = resultProduct.getListPrice();
                // } else {
                // while (!year.matches("^-?\\d+$") && douPrice > 2024) {
                // System.out.print("Enter price again:");
                // year = sc.nextLine();
                // }
                // douPrice = Double.parseDouble(price);
                // }
                // resultProduct.setListPrice(douPrice);

                // System.out.print("Enter new brand id:");
                // String brand_id = sc.nextLine();
                // if (brand_id.isEmpty()) {
                // brand_id = resultProduct.getBrand().getId();
                // }
                // while (!brandList.checkId(brand_id.toUpperCase())) {
                // System.out.print("Please Enter brand id again:");
                // brand_id = sc.nextLine();
                // }
                // resultProduct.setBrand(brandList.getItem(brand_id));
                // //
                // System.out.print("Enter new category id:");
                // String category_id = sc.nextLine();
                // if (category_id.isEmpty()) {
                // category_id = resultProduct.getCategory().getId();
                // }
                // while (!categoryList.checkId(category_id.toUpperCase())) {
                // System.out.print("Please Enter category id again:");
                // category_id = sc.nextLine();
                // }
                // resultProduct.setCategory(categoryList.getItem(category_id));

                // int position = this.products.indexOf(resultProduct);
                // if (position == -1) {
                // System.out.println("Product not found!!");
                // } else {
                // this.products.set(position, resultProduct);
                // System.out.println("updated!");
                // }

                Scanner sc = new Scanner(System.in);

                System.out
                        .print("Enter name (or press Enter to keep current name '" + resultProduct.getName() + "'): ");
                String name = sc.nextLine().trim();
                if (name.isEmpty()) {
                    name = resultProduct.getName();
                }

                // --------------------------------------------------------------------------
                System.out.print("Enter model year (or press Enter to keep current year " + resultProduct.getModelYear()
                        + "): ");
                String yearInput = sc.nextLine().trim();
                int year;
                if (yearInput.isEmpty()) {
                    year = resultProduct.getModelYear();
                } else {
                    year = Integer.parseInt(yearInput);
                    while (year > 2024) {
                        System.out.print("Please Enter year again (year Invalid!!!!): ");
                        year = Integer.parseInt(sc.nextLine().trim());
                    }
                }

                System.out.print("Enter list price (or press Enter to keep current price "
                        + resultProduct.getListPrice() + "): ");
                String priceInput = sc.nextLine().trim();
                double price;
                if (priceInput.isEmpty()) {
                    price = resultProduct.getListPrice();
                } else {
                    price = Double.parseDouble(priceInput);
                    while (price <= 0) {
                        System.out.print("Please Enter price as a positive number: ");
                        price = Double.parseDouble(sc.nextLine().trim());
                    }
                }

                System.out.print("Enter brand id (or press Enter to keep current brand '"
                        + resultProduct.getBrand().getId() + "'): ");
                String brand_id = sc.nextLine().trim();
                if (brand_id.isEmpty()) {
                    brand_id = resultProduct.getBrand().getId();
                } else {
                    while (!brandList.checkId(brand_id.toUpperCase())) {
                        System.out.print("Please Enter brand id again: ");
                        brand_id = sc.nextLine().trim();
                    }
                }

                System.out.print("Enter category id (or press Enter to keep current category '"
                        + resultProduct.getCategory().getId() + "'): ");
                String category_id = sc.nextLine().trim();
                if (category_id.isEmpty()) {
                    category_id = resultProduct.getCategory().getId();
                } else {
                    while (!categoryList.checkId(category_id.toUpperCase())) {
                        System.out.print("Please Enter category id again: ");
                        category_id = sc.nextLine().trim();
                    }
                }

                // Update the product with new values
                resultProduct.setName(name);
                resultProduct.setModelYear(year);
                resultProduct.setListPrice(price);
                resultProduct.setBrand(brandList.getItem(brand_id));
                resultProduct.setCategory(categoryList.getItem(category_id));
                // product creat
                // Product pro = new Product(id, name, year, price, brandList.getItem(brand_id),
                // categoryList.getItem(category_id));
                // add pro
                // System.out.println(productList.addItem(pro) ? "Added successfull" : " Failed
                // to added");
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
    public boolean deleteItem(String id) {
        Product product = getItem(id.toUpperCase());
        if (product != null) {
            return products.remove(product);
        }
        return false;
    }

    @Override
    public boolean save() {
                 try {
                    ManageFile fileProduct = new ManageFile();
                    List<String> data = new ArrayList<>();
                    for (Product product : products) {
                        String line = String.format("%s,%s,%s,%s,%d,%.2f",
                            product.getId(),
                            product.getName(),
                            product.getBrand().getId(),
                            product.getCategory().getId(),
                            product.getModelYear(),
                            product.getListPrice()
                        );
                        data.add(line);
                    }
                    fileProduct.save(FILE_NAME, data);
                    return true;
                 } catch (Exception e) {
                    System.err.println("Error saving products: " + e.getMessage());
                    return false;
                 }
        }

        // Giải thích cách làm việc của code:
        // 1. Tạo một đối tượng File với tên file được định nghĩa trong FILE_NAME.
        // 2. Kiểm tra xem file có tồn tại không bằng phương thức exists().
        // 3. Nếu file tồn tại:
        // a. Tạo một PrintWriter để ghi dữ liệu vào file.
        // b. Duyệt qua danh sách các sản phẩm (products).
        // c. Với mỗi sản phẩm, tạo một chuỗi chứa thông tin sản phẩm, phân cách bằng
        // dấu phẩy.
        // d. Ghi chuỗi thông tin vào file và đẩy dữ liệu ngay lập tức bằng flush().
        // e. Trả về true nếu quá trình ghi thành công.
        // 4. Nếu file không tồn tại, trả về false.
        // 5. Nếu có bất kỳ lỗi IOException nào xảy ra trong quá trình ghi:
        // a. In thông báo lỗi.
        // b. Trả về false để chỉ ra rằng quá trình lưu không thành công.
    

    @Override
    public boolean load() {

        try {
            ManageFile fileProduct = new ManageFile();
            List<String> allText = fileProduct.load(FILE_NAME);
            if (allText != null) {
                for (String line : allText) {
                    String txt[] = line.split(",");
                    if (txt.length == 6) {
                        String id = txt[0].trim();
                        String name = txt[1].trim();
                        ListBrand brandList = new ListBrand();
                        brandList.load();
                        Brand brand = brandList.getItem(txt[2]);

                        ListCategory categoryList = new ListCategory();
                        categoryList.load();
                        Category category = categoryList.getItem(txt[3]);
                        int modelyear = Integer.parseInt(txt[4]);
                        double listPrice = Double.parseDouble(txt[5]);
                        Product product = new Product(id, name, modelyear, listPrice, brand, category);
                        this.addItem(product);
                    }
                }
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error loading products: " + e.getMessage());
            return false;
        }

        return false;
    }

    public boolean checkId(String id) {
        return this.getItem(id.toUpperCase()) != null;
    }

    public void sortProduct() {
        Collections.sort(products);
    }

    public void sortProductByPrice() {
        Collections.sort(products, Product::compareToListPrice);
    }
}
