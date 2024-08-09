package com.challenge.FCamara.dtos;

import com.challenge.FCamara.entities.VehicleType;

public record ListVehiclesDto(
        Long id,
        String brand,
        String color,
        String model,
        String plate,
        VehicleType type,
        EstablishmentDto establishment
) {

}
