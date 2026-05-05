package com.equipo7.AParkApp.feature.user.owner;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class OwnerService implements IOwnerService{
    @Autowired
    private OwnerRepository repository;

    @Override
    public OwnerEntity save(OwnerEntity owner) {
        repository.save(owner);
        return owner;
    }

    @Override
    public void update(OwnerEntity owner) {
        /// TODO LOGICA
    }

    @Override
    public List<OwnerEntity> findAll() {
        return List.of();
    }

    @Override
    public OwnerEntity findById(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void delete(OwnerEntity owner) {

    }
}
