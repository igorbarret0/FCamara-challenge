package com.challenge.FCamara.dtos;

import com.challenge.FCamara.entities.Establishment;
import com.challenge.FCamara.entities.Vehicle;
import com.challenge.FCamara.entities.VehicleType;

public record ParkingVehicleDto(
        String brand,
        String model,
        String color,
        String plate,
        VehicleType type
) {

    public Vehicle toEntity(Establishment establishment) {
        var vehicle =  new Vehicle();
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setColor(color);
        vehicle.setPlate(plate);
        vehicle.setType(type);
        vehicle.setEstablishment(establishment);

        return vehicle;
    }
}
