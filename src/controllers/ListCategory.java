/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import models.Category;
import java.util.List;
import manageFile.ManageFile;

/**
 *
 * @author Admin
 */
public class ListCategory implements IItem<Category> {
    private static final String FILE_NAME = "01_Category.txt";

    private List<Category> categoties;

    public ListCategory() {
        this.categoties = new ArrayList<>();
    }

    @Override
    public Category getItem(String id) {
        for (Category c : categoties) {
            if (c.getId().toUpperCase().equalsIgnoreCase(id.toUpperCase())) {
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
    public boolean deleteItem(String id) {
        Category c = getItem(id);
        if (c != null) {
            return categoties.remove(c);
        }
        return false;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public boolean load() {

        ManageFile fileCategory = new  ManageFile();
        try {
            List<String> allText = fileCategory.load(FILE_NAME);
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

    public boolean checkId(String id) {
        return this.getItem(id.toUpperCase()) != null;
    }

    @Override
    public boolean updateItem(String id) {

        return false;
    }
}
