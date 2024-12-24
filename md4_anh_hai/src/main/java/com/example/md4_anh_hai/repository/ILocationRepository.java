package com.example.md4_anh_hai.repository;

import com.example.md4_anh_hai.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findByNameContaining(String name);
}
