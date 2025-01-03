package com.example.baitap_md4.service.impl;

import com.example.baitap_md4.model.Position;
import com.example.baitap_md4.repository.IPositionRepository;
import com.example.baitap_md4.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService implements IPositionService {

    @Autowired
    private IPositionRepository positionRepository;

    @Override
    public List<Position> getAll() {
        return positionRepository.findAll();
    }
}
