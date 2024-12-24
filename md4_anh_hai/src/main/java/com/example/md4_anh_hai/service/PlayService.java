package com.example.md4_anh_hai.service;

import com.example.md4_anh_hai.model.Player;
import com.example.md4_anh_hai.repository.IPlayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PlayService implements IPlayerService {

    @Autowired
    private IPlayRepository playerRepository;

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public void save(Player player) {
        playerRepository.save(player);
    }

    @Override
    public void update(Player player) {

    }

    @Override
    public void remove(Long id) {
        playerRepository.deleteById(id);
    }


    @Override
    public Page<Player> findByName(String fullName, Integer page, Integer size) {
        return playerRepository.findAllByNameContainingIgnoreCase(fullName, PageRequest.of(page, 5));
    }
}
