package com.example.md4_anh_hai.service;

import com.example.md4_anh_hai.model.Player;

import java.util.List;
import java.util.Optional;

public interface IPlayerService {
    List<Player> getAll();

    Optional<Player> findById(Long id);

    void save(Player player);

    void remove(Long id);
}
