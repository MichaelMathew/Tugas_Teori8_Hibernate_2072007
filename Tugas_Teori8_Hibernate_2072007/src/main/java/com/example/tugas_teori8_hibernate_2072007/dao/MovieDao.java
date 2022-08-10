package com.example.tugas_teori8_hibernate_2072007.dao;

import com.example.tugas_teori8_hibernate_2072007.model.Movie;
import com.example.tugas_teori8_hibernate_2072007.util.MyConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MovieDao implements daoInterface<Movie> {
    @Override
    public List<Movie> getData() {
        List<Movie> mList;
        Session s = MyConnection.getSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Movie.class);
        cq.from(Movie.class);
        mList = s.createQuery(cq).getResultList();
        s.close();
        return mList;
    }

    public List<Movie> FilterData(String data) {
        List<Movie> mList;
        Session s = MyConnection.getSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Movie.class);
        Root<Movie> r = cq.from(Movie.class);
        Predicate p1 = cb.like(r.get("genre"),"%" + data + "%");
        cq.where(p1);
        mList = s.createQuery(cq).getResultList();
        s.close();
        return mList;
    }

    public void addData(Movie data) {
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
    public int deleteData(Movie data) {
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
    public int updateData(Movie data) {
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
