package com.pruebatecnica.wastemanagerservice.repository;

import com.pruebatecnica.wastemanagerservice.entities.WasteManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WasteManagerJpaRepository extends JpaRepository<WasteManagerEntity, Long> {
    Optional<WasteManagerEntity> getWasteManagerEntitiesById(Long id);
}
