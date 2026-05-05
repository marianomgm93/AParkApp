package com.equipo7.AParkApp.feature.user.owner;

import java.util.List;

public interface IOwnerService {
    OwnerEntity save(OwnerEntity owner);
    void update(OwnerEntity owner);
    List<OwnerEntity> findAll();
    OwnerEntity findById(Long id);
    void delete(OwnerEntity owner);
}
