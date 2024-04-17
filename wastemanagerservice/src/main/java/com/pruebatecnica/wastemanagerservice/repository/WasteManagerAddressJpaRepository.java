package com.pruebatecnica.wastemanagerservice.repository;

import com.pruebatecnica.wastemanagerservice.entities.WasteManagerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteManagerAddressJpaRepository extends JpaRepository<WasteManagerAddressEntity, Long> {
}
