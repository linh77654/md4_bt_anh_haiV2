package com.example.repository;


import com.example.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeamRepository extends JpaRepository<Team, Integer> {
}