package com.pruebatecnica.wastemanagerservice.mappers;

import com.pruebatecnica.wastemanagerservice.entities.WasteManagerEntity;
import com.pruebatecnica.wastemanagerservice.dto.WasteManagerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WasteManagerMapper extends BaseJpaMapper<WasteManagerDto, WasteManagerEntity>{
}
