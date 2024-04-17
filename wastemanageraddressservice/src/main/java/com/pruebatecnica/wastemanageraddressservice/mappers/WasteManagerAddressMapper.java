package com.pruebatecnica.wastemanageraddressservice.mappers;

import com.pruebatecnica.wastemanageraddressservice.entities.WasteManagerAddressEntity;
import com.pruebatecnica.wastemanageraddressservice.dto.WasteManagerAddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WasteManagerAddressMapper extends BaseJpaMapper<WasteManagerAddressDto, WasteManagerAddressEntity> {
}
