package ru.job4j.autosale.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_gen")
    @SequenceGenerator(name = "seller_gen", sequenceName = "seller_id_seq", allocationSize = 1)
    private int id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private long regdate;
    private String password;

    public static Seller of(int id) {
        Seller seller = new Seller();
        seller.id = id;
        return seller;
    }

    public static Seller of(String name, String address, String email, String phone, long regdate, String password) {
        Seller seller = new Seller();
        seller.name = name;
        seller.address = address;
        seller.email = email;
        seller.phone = phone;
        seller.regdate = regdate;
        seller.password = password;
        return seller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getRegdate() {
        return regdate;
    }

    public void setRegdate(long regdate) {
        this.regdate = regdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Seller seller = (Seller) o;
        return id == seller.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return name;
    }
}
