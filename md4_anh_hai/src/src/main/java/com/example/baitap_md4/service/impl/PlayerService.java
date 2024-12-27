package com.example.baitap_md4.service.impl;

import com.example.baitap_md4.model.Player;
import com.example.baitap_md4.repository.IPlayerRepository;
import com.example.baitap_md4.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService {

    @Autowired
    private IPlayerRepository playerRepository;

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }

    @Override
    public Player findById(Integer id) {
        return playerRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Integer id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Page<Player> findByName(String name, Integer page) {
        return playerRepository.findAllByNameContainingIgnoreCase(name, PageRequest.of(page, 5));
    }

    @Override
    public Page<Player> findAllPaginated(Integer page) {
        return playerRepository.findAll(PageRequest.of(page, 5));
    }


}
