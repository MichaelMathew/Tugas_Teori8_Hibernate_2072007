module com.example.uts_2072007 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;

    opens com.example.tugas_teori8_hibernate_2072007 to javafx.fxml;
    exports com.example.tugas_teori8_hibernate_2072007;
    exports com.example.tugas_teori8_hibernate_2072007.model;
    exports com.example.tugas_teori8_hibernate_2072007.util;
    exports com.example.tugas_teori8_hibernate_2072007.dao;
    exports com.example.tugas_teori8_hibernate_2072007.Controller;
    opens com.example.tugas_teori8_hibernate_2072007.model;
}