/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Product;

/**
 *
 * @author Admin
 */
public class listProduct implements IItem<Product> {

    public List<Product> products = new ArrayList<>();

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

    @Override
    public boolean updateItem(Product Item) {
        int i = products.indexOf(getItem(Item.getId()));
        if (i >= 0) {
            products.set(i, Item);
            return true;
        }
        return false;
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
