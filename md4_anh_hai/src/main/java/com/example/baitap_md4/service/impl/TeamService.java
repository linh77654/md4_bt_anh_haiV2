package com.example.baitap_md4.service.impl;

import com.example.baitap_md4.model.Team;
import com.example.baitap_md4.repository.ITeamRepository;
import com.example.baitap_md4.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements ITeamService {

    @Autowired
    private ITeamRepository teamRepository;

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public Page<Team> findAllPaginated(Integer page) {
        return teamRepository.findAll(PageRequest.of(page, 5));
    }

    @Override
    public void add(Team team) {
        teamRepository.save(team);
    }

    @Override
    public Team findById(Integer id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Team team) {
        if (team.getId() != null && teamRepository.existsById(team.getId())) {
            teamRepository.save(team);
        }
    }

    @Override
    public void remove(Integer id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
        }
    }
}