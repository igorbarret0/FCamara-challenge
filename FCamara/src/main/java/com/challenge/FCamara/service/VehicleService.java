package com.challenge.FCamara.service;

import com.challenge.FCamara.dtos.EstablishmentDto;
import com.challenge.FCamara.dtos.ListVehiclesDto;
import com.challenge.FCamara.dtos.ParkingVehicleDto;
import com.challenge.FCamara.dtos.UpdateVehicleDto;
import com.challenge.FCamara.entities.VehicleType;
import com.challenge.FCamara.repository.EstablishmentRepository;
import com.challenge.FCamara.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;
    private EstablishmentRepository establishmentRepository;
    private MonitoringService monitoringService;

    public VehicleService(VehicleRepository vehicleRepository, EstablishmentRepository establishmentRepository,
                          MonitoringService monitoringService) {
        this.vehicleRepository = vehicleRepository;
        this.establishmentRepository = establishmentRepository;
        this.monitoringService = monitoringService;
    }

    public void parkingVehicle(Long establishmentId ,ParkingVehicleDto request) {

        var establishment = establishmentRepository.findById(establishmentId)
                .orElseThrow(() -> new RuntimeException("Establishment not found with id " + establishmentId));

        var vehicle = request.toEntity(establishment);

        if (vehicle.getType() == VehicleType.CAR) {
            if (establishment.getCarParkingSpaces() > 0) {
                vehicleRepository.save(vehicle);
                monitoringService.registerEntry();
                establishment.setCarParkingSpaces(establishment.getCarParkingSpaces() - 1);
            } else {
                throw new RuntimeException("There is no space for parking a car");
            }
        } else {
            if (establishment.getMotorcycleParkingSpaces() > 0) {
                vehicleRepository.save(vehicle);
                monitoringService.registerEntry();
                establishment.setMotorcycleParkingSpaces(establishment.getMotorcycleParkingSpaces() - 1);
            } else {
                throw new RuntimeException("There is no space for parking a motorcycle");
            }
        }


    }

    public void updateVehicle(Long id, UpdateVehicleDto request) {

        var vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));

        if (request.brand() != null) {
            vehicle.setBrand(request.brand());
        }

        if (request.color() != null) {
            vehicle.setColor(request.color());
        }

        if (request.model() != null) {
            vehicle.setModel(request.model());
        }

        if (request.plate() != null) {
            vehicle.setPlate(request.plate());
        }

        if (request.type() != null) {
            vehicle.setType(request.type());
        }

        vehicleRepository.save(vehicle);
    }

    public List<ListVehiclesDto> getAllVehicles() {

        var allVehicles = vehicleRepository.findAll();

        var response = allVehicles.stream()
                .map(vehicle -> new ListVehiclesDto(vehicle.getId(), vehicle.getBrand(), vehicle.getColor(), vehicle.getModel(),
                        vehicle.getPlate(), vehicle.getType(),
                        new EstablishmentDto(vehicle.getEstablishment().getId(), vehicle.getEstablishment().getName())))
                .toList();

        return response;

    }

    public void exitParking(Long vehicleId) {

        var vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id " + vehicleId));

        var establishment = vehicle.getEstablishment();
        if (vehicle.getType() == VehicleType.CAR) {
            establishment.setCarParkingSpaces(establishment.getCarParkingSpaces() + 1);
            monitoringService.registerExit();
        } else {
            establishment.setMotorcycleParkingSpaces(establishment.getMotorcycleParkingSpaces() + 1);
            monitoringService.registerExit();
        }

        vehicleRepository.delete(vehicle);

    }

}
