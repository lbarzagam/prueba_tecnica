package com.pruebatecnica.wastemanagerservice.service;

import com.pruebatecnica.wastemanagerservice.client.WasteManagerAddressClient;
import com.pruebatecnica.wastemanagerservice.dto.WasteManagerDto;
import com.pruebatecnica.wastemanagerservice.dto.WasteManagerDtoResponse;
import com.pruebatecnica.wastemanagerservice.entities.WasteManagerEntity;
import com.pruebatecnica.wastemanagerservice.infra.exceptions.DomainException;
import com.pruebatecnica.wastemanagerservice.infra.util.reflection.Util_Reflection;
import com.pruebatecnica.wastemanagerservice.infra.validators.CodigoErrorEnum;
import com.pruebatecnica.wastemanagerservice.mappers.WasteManagerMapper;
import com.pruebatecnica.wastemanagerservice.repository.WasteManagerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.boon.core.reflection.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WasteManagerService {
    private final WasteManagerJpaRepository wasteManagerJpaRepository;
    private final WasteManagerMapper wasteManagerMapper;
    private final WasteManagerAddressClient wasteManagerAddressClient;

    public ResponseEntity sayHello() {
        return new ResponseEntity("Hello", HttpStatus.OK);
    }

    public List<WasteManagerDtoResponse> listWaste() {
        List<WasteManagerDtoResponse> wasteManagerDtoResponseList = new ArrayList<>();
        wasteManagerJpaRepository.findAll().iterator().forEachRemaining(wasteManagerEntity -> {

            WasteManagerDtoResponse wasteManagerDtoResponse = new WasteManagerDtoResponse();
            wasteManagerDtoResponse.crearNuevo(wasteManagerMapper.toDomainModel(wasteManagerEntity));

            wasteManagerDtoResponseList.add(wasteManagerDtoResponse);
        });
        return wasteManagerDtoResponseList;
    }

    public WasteManagerDto crearWaste(WasteManagerDto wasteManagerDto) {
        WasteManagerEntity wasteManagerEntity = wasteManagerMapper.toJpaModel(wasteManagerDto);
        return wasteManagerMapper.toDomainModel(wasteManagerJpaRepository.save(wasteManagerEntity));
    }

    public WasteManagerDto updateWaste(WasteManagerDto wasteManagerDto) {
        WasteManagerEntity wasteEntityBD = wasteManagerMapper.toJpaModel(findWasteById(wasteManagerDto.getId()));
        BeanUtils.copyProperties(wasteManagerDto, wasteEntityBD, Util_Reflection.getNullPropertyNames(wasteManagerDto));
        return wasteManagerMapper.toDomainModel(wasteManagerJpaRepository.save(wasteEntityBD));
    }

    public WasteManagerDto findWasteById(Long id) {
        Optional<WasteManagerEntity> wasteManagerEntityOptional = wasteManagerJpaRepository.getWasteManagerEntitiesById(id);
        if (!wasteManagerEntityOptional.isPresent()) {
            throw new DomainException(CodigoErrorEnum.ERROR_INTEGRIDAD_DATOS,
                    String.format("La entidad WasteManagerEntity con id: %s no fue encontrada", id.toString()));
        }
        return wasteManagerMapper.toDomainModel(wasteManagerEntityOptional.get());
    }
}
