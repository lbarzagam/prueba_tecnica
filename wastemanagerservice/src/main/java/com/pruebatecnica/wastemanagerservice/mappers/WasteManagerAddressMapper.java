package com.pruebatecnica.wastemanagerservice.mappers;

import com.pruebatecnica.wastemanagerservice.entities.WasteManagerAddressEntity;
import com.pruebatecnica.wastemanagerservice.dto.WasteManagerAddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WasteManagerAddressMapper extends BaseJpaMapper<WasteManagerAddressDto, WasteManagerAddressEntity>{
}
