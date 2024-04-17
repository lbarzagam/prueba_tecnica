package com.pruebatecnica.wastemanageraddressservice.service;

import com.pruebatecnica.wastemanageraddressservice.dto.WasteManagerAddressDto;
import com.pruebatecnica.wastemanageraddressservice.dto.WasteManagerAddressDtoResponse;
import com.pruebatecnica.wastemanageraddressservice.entities.WasteManagerAddressEntity;
import com.pruebatecnica.wastemanageraddressservice.infra.exceptions.DomainException;
import com.pruebatecnica.wastemanageraddressservice.infra.util.reflection.Util_Reflection;
import com.pruebatecnica.wastemanageraddressservice.infra.validators.CodigoErrorEnum;
import com.pruebatecnica.wastemanageraddressservice.mappers.WasteManagerAddressMapper;
import com.pruebatecnica.wastemanageraddressservice.repository.WasteManagerAddressJpaRepository;
import lombok.RequiredArgsConstructor;
import org.boon.core.reflection.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WasteManagerAddressService {

    private final WasteManagerAddressJpaRepository wasteManagerAddressJpaRepository;
    private final WasteManagerAddressMapper wasteManagerAddressMapper;

    public List<WasteManagerAddressDtoResponse> wasteManagerAddressEntityList() {
        try {
            List<WasteManagerAddressDtoResponse> wasteManagerDtoResponseList = new ArrayList<>();

            wasteManagerAddressMapper.toDomainModel(wasteManagerAddressJpaRepository.findAll()).forEach(address -> {
                WasteManagerAddressDtoResponse wasteManagerAddressDtoResponse = new WasteManagerAddressDtoResponse();
                wasteManagerAddressDtoResponse.crearNuevo(address);

                wasteManagerDtoResponseList.add(wasteManagerAddressDtoResponse);
            });
            return wasteManagerDtoResponseList;

        } catch (Exception ex) {
            throw new DomainException(CodigoErrorEnum.ERROR_INTEGRIDAD_DATOS, ex.getMessage());
        }
    }

    public WasteManagerAddressDto crearWasteManagerAddress(WasteManagerAddressDto wasteManagerAddressDto) {
        try {
            WasteManagerAddressEntity wasteManagerAddressEntity = wasteManagerAddressMapper.toJpaModel(wasteManagerAddressDto);
            wasteManagerAddressJpaRepository.save(wasteManagerAddressEntity);
            return wasteManagerAddressMapper.toDomainModel(wasteManagerAddressEntity);
        } catch (Exception ex) {
            throw new DomainException(CodigoErrorEnum.ERROR_INTEGRIDAD_DATOS, ex.getMessage());
        }
    }

    public WasteManagerAddressDto updateWasteManagerAddress(WasteManagerAddressDto wasteManagerAddressDto) {
        try {
            WasteManagerAddressEntity addressEntityBD = findById(wasteManagerAddressDto.getId());
            BeanUtils.copyProperties(wasteManagerAddressDto, addressEntityBD, Util_Reflection.getNullPropertyNames(wasteManagerAddressDto));

            return wasteManagerAddressMapper.toDomainModel(wasteManagerAddressJpaRepository.save(addressEntityBD));

        } catch (Exception ex) {
            throw new DomainException(CodigoErrorEnum.ERROR_INTEGRIDAD_DATOS, ex.getMessage());
        }
    }

    public String deleteWasteManagerAddress(Long id) {
        try {
            WasteManagerAddressEntity addressEntityBD = findById(id);
            wasteManagerAddressJpaRepository.delete(addressEntityBD);

            return String.format("El elemento con id %s fue eliminado", id);
        } catch (Exception ex) {
            throw new DomainException(CodigoErrorEnum.ERROR_ELIMINANDO_ELEMENTO, ex.getMessage());
        }
    }

    public WasteManagerAddressEntity findById(Long id) {
        Optional<WasteManagerAddressEntity> addressEntityBD = wasteManagerAddressJpaRepository.findById(id);
        if (!addressEntityBD.isPresent())
            throw new DomainException(CodigoErrorEnum.ERROR_INTEGRIDAD_DATOS, "Entidad no encontrada");

        return addressEntityBD.get();
    }
}
