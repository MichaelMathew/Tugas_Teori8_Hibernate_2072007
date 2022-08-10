package com.example.tugas_teori8_hibernate_2072007.dao;

import com.example.tugas_teori8_hibernate_2072007.model.Movie;
import com.example.tugas_teori8_hibernate_2072007.model.User;
import com.example.tugas_teori8_hibernate_2072007.model.Watchlist;
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

public class WListDao implements daoInterface<Watchlist>{
    public List<Watchlist> SelectedList(User data) {
        List<Watchlist> wList;
        Session s = MyConnection.getSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Watchlist.class);
        Root<Watchlist> r = cq.from(Watchlist.class);
        Predicate p1 = cb.equal(r.get("userByUserIdUser"),data);
        cq.where(p1);
        wList = s.createQuery(cq).getResultList();
        s.close();
        return wList;

    }

    @Override
    public List<Watchlist> getData() {
        List<Watchlist> wList;
        Session s = MyConnection.getSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Watchlist.class);
        cq.from(Watchlist.class);
        wList = s.createQuery(cq).getResultList();
        s.close();
        return wList;
    }

    @Override
    public void addData(Watchlist data) {
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
    public int deleteData(Watchlist data) {
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
    public int updateData(Watchlist data) {
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
