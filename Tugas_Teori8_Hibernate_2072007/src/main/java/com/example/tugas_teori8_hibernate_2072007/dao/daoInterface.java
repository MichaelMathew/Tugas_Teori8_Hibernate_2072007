package com.example.tugas_teori8_hibernate_2072007.dao;

import java.util.List;

public interface daoInterface<T> {
    List<T> getData();
    void addData(T data);
    int deleteData(T data);
    int updateData(T data);
}
