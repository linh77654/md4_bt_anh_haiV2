package com.example.baitap_md4.repository;

import com.example.baitap_md4.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeamRepository extends JpaRepository<Team, Integer> {
}