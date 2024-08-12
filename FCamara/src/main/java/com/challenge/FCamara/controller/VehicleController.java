package com.challenge.FCamara.controller;

import com.challenge.FCamara.dtos.ListVehiclesDto;
import com.challenge.FCamara.dtos.ParkingVehicleDto;
import com.challenge.FCamara.dtos.UpdateVehicleDto;
import com.challenge.FCamara.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> parkingVehicle(@Valid @PathVariable(name = "id") Long id,
                                               @RequestBody ParkingVehicleDto request){

        vehicleService.parkingVehicle(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVehicle(@PathVariable(name = "id") Long id,
                                              @RequestBody UpdateVehicleDto request) {

        vehicleService.updateVehicle(id, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ListVehiclesDto>> getAllVehicles() {

        var allVehicles = vehicleService.getAllVehicles();
        return new ResponseEntity<>(allVehicles, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> exitParking(@PathVariable(name = "id") Long id) {

        vehicleService.exitParking(id);
        return ResponseEntity.ok().build();
    }

}
