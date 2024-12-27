package com.example.baitap_md4.service.impl;

import com.example.baitap_md4.model.Team;
import com.example.baitap_md4.repository.ITeamRepository;
import com.example.baitap_md4.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements ITeamService {
    @Autowired
    private ITeamRepository teamRepository;

    @Override
    public List<Team> getAll(){
        return teamRepository.findAll();
    }

    @Override
    public void save(Team team){
        teamRepository.save(team);
    }

    @Override
    public Team findById(Integer id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Override
    public void remove(Integer id) {
        teamRepository.deleteById(id);
    }

    @Override
    public List<Team> findByName(String name) {
        return null;
    }
}