package com.example.baitap_md4.repository;

import com.example.baitap_md4.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPositionRepository extends JpaRepository<Position, Integer> {
}
