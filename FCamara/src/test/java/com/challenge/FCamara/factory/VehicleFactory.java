package com.challenge.FCamara.factory;

import com.challenge.FCamara.dtos.ListVehiclesDto;
import com.challenge.FCamara.dtos.ParkingVehicleDto;
import com.challenge.FCamara.dtos.UpdateVehicleDto;
import com.challenge.FCamara.entities.Vehicle;
import com.challenge.FCamara.entities.VehicleType;

import java.util.List;

public class VehicleFactory {

    public static ParkingVehicleDto buildCarParkingVehicleDto() {

        var dto = new ParkingVehicleDto("brand", "model", "color", "plate", VehicleType.CAR);
        return dto;
    }

    public static ParkingVehicleDto buildMotorcycleParkingVehicleDto() {

        var dto = new ParkingVehicleDto("brand", "model", "color", "plate", VehicleType.MOTORCYCLE);
        return dto;
    }

    public static UpdateVehicleDto buildUpdateVehicleDto() {

        var dto = new UpdateVehicleDto("brandUpdated", "colorUpdated", "modelUpdated",
                "plateUpdated", VehicleType.MOTORCYCLE);
        return dto;
    }

    public static List<Vehicle> buildListVehiclesDto() {

        var establishment = EstablishmentFactory.buildEstablishment();
        var vehicle1 = VehicleFactory.buildCarParkingVehicleDto().toEntity(establishment);
        var vehicle2 = VehicleFactory.buildMotorcycleParkingVehicleDto().toEntity(establishment);

        return List.of(vehicle1, vehicle2);

    }

}
