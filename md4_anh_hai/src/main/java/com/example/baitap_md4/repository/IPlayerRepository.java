package com.example.baitap_md4.repository;

import com.example.baitap_md4.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlayerRepository extends JpaRepository<Player, Integer> {

    Page<Player> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Player> findAll(Pageable pageable);

    int countByStatus(String status);
}