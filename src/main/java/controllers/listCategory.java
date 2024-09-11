/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import models.Category;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;


/**
 *
 * @author Admin
 */
public class listCategory implements IItem<Category> {

    private List<Category> categoties;

    public listCategory() {
        this.categoties = new ArrayList<>();
    }

    @Override
    public Category getItem(String id) {
        for (Category c : categoties) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public boolean addItem(Category Item) {
        return categoties.add(Item);
    }

    @Override
    public boolean updateItem(Category Item) {
        int i = categoties.indexOf(getItem(Item.getId()));
        if (i >= 0) {
            categoties.set(i, Item);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteItem(Category Item) {
        return categoties.remove(Item);
    }

    @Override
    public boolean deleteItem(String id) {
        Category c = getItem(id);
        if (c != null) {
            return categoties.remove(c);
        }
        return false;
    }

    @Override
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean lead() {

        File categoryFile = new File("D:\\Code_Ky3\\lab211\\lab\\lab1\\lab01\\01_Category.txt");
        try {
            List<String> allText = Files.readAllLines(categoryFile.toPath(), StandardCharsets.UTF_8);
            for (String line : allText) {
                String txt[] = line.split(",");
                if (txt.length == 2) {
                    String id = txt[0].trim();
                    String name = txt[1].trim();
                    Category category = new Category(name, id);
                    this.addItem(category);
         
                }

            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Category> getAll() {
        return categoties;
    }
  public boolean checkId(String id) {
        return this.getItem(id.toUpperCase()) !=null;
    }
}
