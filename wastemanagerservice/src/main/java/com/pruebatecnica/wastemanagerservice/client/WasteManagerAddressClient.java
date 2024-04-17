package com.pruebatecnica.wastemanagerservice.client;

import com.pruebatecnica.wastemanagerservice.dto.WasteManagerAddressDtoResponse;
import com.pruebatecnica.wastemanagerservice.entities.WasteManagerAddressEntity;
import com.pruebatecnica.wastemanagerservice.dto.WasteManagerAddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "waste-manager-address")
public interface WasteManagerAddressClient {

    @RequestMapping(value = "/managerAddress/hello")
    String helloManagerAdress();

    @GetMapping(value = "/managerAddress/listWasteManagerAddress")
    List<WasteManagerAddressDtoResponse> listManagerAddress();

    @PostMapping(value = "/managerAddress/crearWasteManagerAddress")
    WasteManagerAddressDto crearManagerAddress(@RequestBody WasteManagerAddressDto wasteManagerAddressDto);

    @PutMapping(value = "/managerAddress/updateWasteManagerAddress")
    WasteManagerAddressDto updateManagerAddress(@RequestBody WasteManagerAddressDto wasteManagerAddressEntity);

    @DeleteMapping(value = "/managerAddress/deleteWasteManagerAddress/{id}")
    String deleteManagerAddress(@PathVariable Long id);
}
