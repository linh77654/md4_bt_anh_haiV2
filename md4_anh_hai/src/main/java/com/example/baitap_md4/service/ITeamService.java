package com.example.baitap_md4.service;

import com.example.baitap_md4.model.Team;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITeamService {

    List<Team> getAll();

    Page<Team> findAllPaginated(Integer page);

    void add(Team team);

    Team findById(Integer id);

    void update(Team team);

    void remove(Integer id);
}
