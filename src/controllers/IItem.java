package controllers;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
/**
 *
 * @author Admin
 */
public interface IItem<E> {
    //lấy đối tượng dựa trên id

    E getItem(String id);
    //thêm một đối tượng vào ds

    boolean addItem(E Item);
    //Cập nhật đối tượng 

    boolean updateItem(String id);
    
    // xóa đối tượng khỏi ds dựa trên id

    boolean deleteItem(String id);
    //lưu ds đối tượng vào file

    boolean save();
    //tải ds các đối tượng từ file

    boolean load();


}
