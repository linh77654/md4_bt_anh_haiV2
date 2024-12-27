package com.example.repository;


import com.example.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlayerRepository extends JpaRepository<Player, Integer> {

    Page<Player> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Player> findAll(Pageable pageable);

}