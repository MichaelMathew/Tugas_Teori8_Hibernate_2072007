package com.example.tugas_teori8_hibernate_2072007.dao;

import com.example.tugas_teori8_hibernate_2072007.model.Movie;
import com.example.tugas_teori8_hibernate_2072007.model.User;
import com.example.tugas_teori8_hibernate_2072007.util.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements daoInterface<User>{

    @Override
    public List<User> getData() {
        List<User> uList;
        Session s = MyConnection.getSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(User.class);
        cq.from(User.class);
        uList = s.createQuery(cq).getResultList();
        s.close();
        return uList;
    }

    @Override
    public void addData(User data) {
        Session s = MyConnection.getSession();
        Transaction t = s.beginTransaction();
        try{
            s.save(data);
            t.commit();
        }
        catch (Exception e){
            t.rollback();
        }
        s.close();
    }

    @Override
    public int deleteData(User data) {
        int hasil = 0;
        Session s = MyConnection.getSession();
        Transaction t = s.beginTransaction();
        try{
            s.delete(data);
            t.commit();
            hasil = 1;
        }
        catch (Exception e){
            t.rollback();
        }
        s.close();
        return hasil;
    }

    @Override
    public int updateData(User data) {
        int hasil = 0;
        Session s = MyConnection.getSession();
        Transaction t = s.beginTransaction();
        try{
            s.update(data);
            t.commit();
            hasil = 1;
        }
        catch (Exception e){
            t.rollback();
        }
        s.close();
        return hasil;
    }
}
