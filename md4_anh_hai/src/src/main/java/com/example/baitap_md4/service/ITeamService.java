package com.example.baitap_md4.service;


import com.example.baitap_md4.model.Team;

import java.util.List;

public interface ITeamService {

    List<Team> getAll();

    void save(Team team);

    Team findById(Integer id);

    void remove(Integer id);

    List<Team> findByName(String name);
}
