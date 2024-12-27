package com.example.service.impl;

import com.example.model.Player;
import com.example.repository.IPlayerRepository;
import com.example.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements IPlayerService {

    @Autowired
    private IPlayerRepository playerRepository;

    @Override
    public Page<Player> findAllPaginated(int page) {
        return playerRepository.findAll(PageRequest.of(page, 5));
    }

    @Override
    public void add(Player player) {
        playerRepository.save(player);
    }

    @Override
    public void update(Player player) {
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
}
