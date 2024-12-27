package com.example.baitap_md4.service;

import com.example.baitap_md4.model.Player;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPlayerService {
    List<Player> getAll();

    void save(Player player);

    Player findById(Integer id);

    void remove(Integer id);

    Page<Player> findByName(String name, Integer page);

    Page<Player> findAllPaginated(Integer page);


}
