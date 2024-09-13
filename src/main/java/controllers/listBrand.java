/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import models.Brand;

/**
 *
 * @author Admin
 */
public class listBrand implements IItem<Brand> {

    public List<Brand> brand;

    public listBrand() {
        this.brand = new ArrayList<>();
    }

    @Override
    public Brand getItem(String id) {
        Brand rs = null;
        for (Brand b : brand) {
            if (b.getId().equalsIgnoreCase(id)) {
                rs =b;
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
    public boolean deleteItem(Brand Item) {
        return brand.remove(Item);
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean lead() {
        File brandFile = new File("01_Brand.txt");
        try {
            List<String> allText = Files.readAllLines(brandFile.toPath(), StandardCharsets.UTF_8);
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

    @Override
    public List<Brand> getAll() {
        return brand;
    }

    public boolean checkId(String id) {
        return this.getItem(id) != null;
    }

    @Override
    public boolean updateItem(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
