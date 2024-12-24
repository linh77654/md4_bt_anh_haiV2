package com.example.md4_anh_hai.service;

import com.example.md4_anh_hai.model.Location;
import com.example.md4_anh_hai.repository.ILocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService implements ILocationService {

    @Autowired
    private ILocationRepository locationRepository;

    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    @Override
    public void save(Location location) {
        locationRepository.save(location);
    }

    @Override
    public void update(int id, Location location) {
        Location existingLocation = locationRepository.findById(id).orElse(null);
        if (existingLocation != null) {
            existingLocation.setName(location.getName());
            locationRepository.save(existingLocation);
        }
    }

    @Override
    public void remove(int id) {
        locationRepository.deleteById(id);
    }

    @Override
    public Location findById(int id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Location> findByName(String name) {
        return locationRepository.findByNameContaining(name);
    }
}
