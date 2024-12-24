package com.example.md4_anh_hai.repository;

import com.example.md4_anh_hai.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlayRepository extends JpaRepository<Player , Long> {

    Page<Player> findAllByNameContainingIgnoreCase(String fullName, PageRequest of);
}
