package com.example.md4_anh_hai.service;

import com.example.md4_anh_hai.model.Location;

import java.util.List;

public interface ILocationService {

    List<Location> getAll();

    void save(Location location);

    void update(int id, Location location);

    void remove(int id);

    Location findById(int id);

    List<Location> findByName(String name);
}
