package com.challenge.FCamara.service;

import com.challenge.FCamara.dtos.ListVehiclesDto;
import com.challenge.FCamara.entities.Vehicle;
import com.challenge.FCamara.factory.EstablishmentFactory;
import com.challenge.FCamara.factory.VehicleFactory;
import com.challenge.FCamara.repository.EstablishmentRepository;
import com.challenge.FCamara.repository.VehicleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    VehicleRepository vehicleRepository;

    @Mock
    EstablishmentRepository establishmentRepository;

    @InjectMocks
    VehicleService vehicleService;

    @Test
    @DisplayName("Should park vehicle correctly")
    public void parkingVehicle() {

        var establishment = EstablishmentFactory.buildEstablishment();
        var vehicleDto = VehicleFactory.buildCarParkingVehicleDto();
        var vehicle = vehicleDto.toEntity(establishment);

        when(establishmentRepository.findById(anyLong()))
                .thenReturn(Optional.of(establishment));

        vehicleService.parkingVehicle(1L, VehicleFactory.buildCarParkingVehicleDto());

        verify(vehicleRepository, times(1)).save(any(Vehicle.class));
        assertEquals(establishment.getCarParkingSpaces(), 9);
    }

    @Test
    @DisplayName("Trying to park a vehicle in a non-existent establishment")
    public void parkingVehicleNonExistentEstablishment() {

        var vehicleDto = VehicleFactory.buildCarParkingVehicleDto();

        when(establishmentRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> vehicleService.parkingVehicle(99L, vehicleDto)
        );

        verify(vehicleRepository, times(0)).save(any(Vehicle.class));
        assertEquals("Establishment not found with id 99", exception.getMessage());
    }

    @Test
    @DisplayName("Trying to park a vehicle in a establishment with no space")
    public void parkingVehicleNoSpaceEstablishment() {

        var establishment = EstablishmentFactory.buildEstablishmentWithNoSpaceForPark();
        var vehicleDto = VehicleFactory.buildMotorcycleParkingVehicleDto();
        var vehicle = vehicleDto.toEntity(establishment);

        when(establishmentRepository.findById(anyLong()))
                .thenReturn(Optional.of(establishment));


        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> vehicleService.parkingVehicle(1L, vehicleDto)
        );

        verify(vehicleRepository, times(0)).save(any(Vehicle.class));
        assertEquals("There is no space for parking a motorcycle", exception.getMessage());
    }

    @Test
    @DisplayName("Should update vehicle correctly")
    public void updateVehicle() {

        var establishment = EstablishmentFactory.buildEstablishment();
        var vehicleDto = VehicleFactory.buildCarParkingVehicleDto();
        var vehicle = vehicleDto.toEntity(establishment);
        var request = VehicleFactory.buildUpdateVehicleDto();

        when(vehicleRepository.findById(anyLong()))
                .thenReturn(Optional.of(vehicle));

        vehicleService.updateVehicle(1L, request);

        assertEquals(vehicle.getBrand(), request.brand());
        assertEquals(vehicle.getColor(), request.color());
        assertEquals(vehicle.getModel(), request.model());
        assertEquals(vehicle.getPlate(), request.plate());
        assertEquals(vehicle.getType(), request.type());
    }

    @Test
    @DisplayName("Trying to update a vehicle who does not exist")
    public void updateVehicleWhoDoesNotExist() {

        var vehicle = VehicleFactory.buildUpdateVehicleDto();

        when(vehicleRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> vehicleService.updateVehicle(99L, vehicle)
        );

        assertEquals("Vehicle not found with id: 99", exception.getMessage());
    }

    @Test
    @DisplayName("Should return all vehicles correctly")
    public void getAllVehicles() {


        var vehicles = VehicleFactory.buildListVehiclesDto();

        when(vehicleRepository.findAll())
                .thenReturn(vehicles);


        List<ListVehiclesDto> result = vehicleService.getAllVehicles();

        assertEquals(2, result.size());
        assertEquals(vehicles.getFirst().getId(), result.get(0).id());
        assertEquals(vehicles.getFirst().getBrand(), result.get(0).brand());
        assertEquals(vehicles.getFirst().getColor(), result.get(0).color());
        assertEquals(vehicles.getFirst().getModel(), result.get(0).model());
        assertEquals(vehicles.getFirst().getPlate(), result.get(0).plate());
        assertEquals(vehicles.getFirst().getType(), result.get(0).type());
        assertEquals(vehicles.getFirst().getEstablishment().getId(), result.get(0).establishment().id());
        assertEquals(vehicles.getFirst().getEstablishment().getName(), result.get(0).establishment().name());

        assertEquals(vehicles.getLast().getId(), result.get(1).id());
        assertEquals(vehicles.getLast().getBrand(), result.get(1).brand());
        assertEquals(vehicles.getLast().getColor(), result.get(1).color());
        assertEquals(vehicles.getLast().getModel(), result.get(1).model());
        assertEquals(vehicles.getLast().getPlate(), result.get(1).plate());
        assertEquals(vehicles.getLast().getType(), result.get(1).type());
        assertEquals(vehicles.getLast().getEstablishment().getId(), result.get(1).establishment().id());
        assertEquals(vehicles.getLast().getEstablishment().getName(), result.get(1).establishment().name());
    }

    @Test
    @DisplayName("Should exit parking correctly")
    public void exitParking() {

        var establishment = EstablishmentFactory.buildEstablishment();
        var vehicleDto = VehicleFactory.buildCarParkingVehicleDto();
        var vehicle = vehicleDto.toEntity(establishment);

        when(vehicleRepository.findById(anyLong()))
                .thenReturn(Optional.of(vehicle));


        vehicleService.exitParking(1L);

        assertEquals(vehicle.getEstablishment().getCarParkingSpaces(), 11);

    }

}
