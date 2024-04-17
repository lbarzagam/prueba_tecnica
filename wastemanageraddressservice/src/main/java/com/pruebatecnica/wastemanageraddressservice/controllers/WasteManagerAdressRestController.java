package com.pruebatecnica.wastemanageraddressservice.controllers;

import com.pruebatecnica.wastemanageraddressservice.dto.WasteManagerAddressDto;
import com.pruebatecnica.wastemanageraddressservice.dto.WasteManagerAddressDtoResponse;
import com.pruebatecnica.wastemanageraddressservice.entities.WasteManagerAddressEntity;
import com.pruebatecnica.wastemanageraddressservice.service.WasteManagerAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/managerAddress")
@RequiredArgsConstructor
public class WasteManagerAdressRestController {

    private final WasteManagerAddressService wasteManagerAddressService;

    @GetMapping(value = "/hello")
    public String helloManagerAdress(){
        return "Hello";
    }

    @GetMapping(value = "/listWasteManagerAddress")
    public List<WasteManagerAddressDtoResponse> listManagerAdress(){
        return wasteManagerAddressService.wasteManagerAddressEntityList();
    }

    @PostMapping(value = "/crearWasteManagerAddress")
    public WasteManagerAddressDto crearManagerAdress(@RequestBody WasteManagerAddressDto wasteManagerAddressDto){
        WasteManagerAddressDto wasteManagerAddressDtoCreated = wasteManagerAddressService.crearWasteManagerAddress(wasteManagerAddressDto);
        return wasteManagerAddressDtoCreated;
    }

    @PutMapping(value = "/updateWasteManagerAddress")
    public WasteManagerAddressDto updateManagerAdress(@RequestBody WasteManagerAddressDto wasteManagerAddressDto){
        return wasteManagerAddressService.updateWasteManagerAddress(wasteManagerAddressDto);
    }

    @DeleteMapping(value = "/deleteWasteManagerAddress/{id}")
    public String deleteManagerAdress(@PathVariable (value = "id") Long id){
        return wasteManagerAddressService.deleteWasteManagerAddress(id);
    }
}
