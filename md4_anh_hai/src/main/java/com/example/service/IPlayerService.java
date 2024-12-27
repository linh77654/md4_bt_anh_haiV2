package com.example.service;


import com.example.model.Player;
import org.springframework.data.domain.Page;

public interface IPlayerService {
    Page<Player> findAllPaginated(int page);

    void add(Player player);

    void update(Player player);

    Player findById(Integer id);

    void remove(Integer id);
}
