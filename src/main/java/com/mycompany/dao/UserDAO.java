/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;
import com.mycompany.entity.User;
import com.mycompany.utils.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author tran
 */
public abstract class UserDAO extends DAO<User, String> {
  @Override
     public void insert(User entity) {
      String sql= "INSERT    INTO    NGUOIDUNG ( HOTEN,NGAYSINH,EMAIL,TENTK) VALUES  (?,  ?,  ?,  ?,  ?,  ?,  ?)";
    XJdbc.update(sql, entity.getHoten(),entity.getNgaysinh(),entity.getEmail(),entity.getTENTK()
   );
   }     
}
