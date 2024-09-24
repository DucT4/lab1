/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import manageFile.ManageFile;
import models.Brand;

/**
 *
 * @author Admin
 */
public class ListBrand implements IItem<Brand> {

    private static final String FILE_NAME = "01_Brand.txt";
    public List<Brand> brand;

    public ListBrand() {
        this.brand = new ArrayList<>();
    }

    @Override
    public Brand getItem(String id) {
        Brand rs = null;
        for (Brand b : brand) {
            if (b.getId().equalsIgnoreCase(id)) {
                rs = b;
                break;
            }
        }
        return rs;
    }

    @Override
    public boolean addItem(Brand Item) {
        return brand.add(Item);
    }

    @Override
    public boolean deleteItem(String id) {
        Brand b = getItem(id);
        if (b != null) {
            return brand.remove(b);
        }
        return false;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {
        ManageFile fileBrand = new ManageFile();
        try {
            List<String> allText = fileBrand.load(FILE_NAME);
            for (String line : allText) {
                String txt[] = line.split(",");
                if (txt.length == 3) {
                    String id = txt[0].trim();
                    String name = txt[1].trim();
                    String country = txt[2].trim();

                    Brand brand = new Brand(id, name, country);
                    this.addItem(brand);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean checkId(String id) {
        return this.getItem(id) != null;
    }

    @Override
    public boolean updateItem(String id) {
        return false;
    }

}
