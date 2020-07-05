package ru.job4j.cinema;

import java.util.List;

public interface Storage {
    List<Place> getAllPlaces(int idhall);
    Place getPlace(int idhall, int row, int col);
    boolean doPayment(int idhall, int row, int col, String fullname, String phone);
}
