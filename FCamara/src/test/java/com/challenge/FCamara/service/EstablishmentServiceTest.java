package com.challenge.FCamara.service;

import com.challenge.FCamara.dtos.UpdateEstablishmentDto;
import com.challenge.FCamara.entities.Establishment;
import com.challenge.FCamara.factory.EstablishmentFactory;
import com.challenge.FCamara.repository.EstablishmentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class EstablishmentServiceTest {


    @Mock
    EstablishmentRepository establishmentRepository;

    @InjectMocks
    EstablishmentService establishmentService;

    @Test
    @DisplayName("Should save a person correctly")
    public void saveEstablishment() {

        var data = EstablishmentFactory.buildEstablishmentDto();

        establishmentService.saveEstablishment(data);

        verify(establishmentRepository, times(1)).save(any(Establishment.class));
    }

    @Test
    @DisplayName("Should return all establishments")
    public void getAllEstablishments() {

        var mockEstablishments = EstablishmentFactory.buildEstablishmentList();

        when(establishmentRepository.findAll())
                .thenReturn(mockEstablishments);


        var response = establishmentService.getAllEstablishments();

        assertEquals(2, response.size());
        assertEquals(mockEstablishments.getFirst().getName(), response.getFirst().getName());
        assertEquals(mockEstablishments.getLast().getName(), response.getLast().getName());
    }

    @Test
    @DisplayName("Should updated an establishment correctly")
    public void updateEstablishment() {

        var establishment = EstablishmentFactory.buildEstablishment();
        var spacesCarBefore = establishment.getCarParkingSpaces();
        var spacesMotorcycleBefore = establishment.getMotorcycleParkingSpaces();

        var establishmentUpdated = new UpdateEstablishmentDto("newName", "newAddress",
                "newPhone", 20, 20);

        when(establishmentRepository.findById(anyLong()))
                .thenReturn(Optional.of(establishment));

        establishmentService.updateEstablishment(1L, establishmentUpdated);

        verify(establishmentRepository, times(1)).findById(anyLong());
        verify(establishmentRepository, times(1)).findById(anyLong());

        // verify if the fields were updated correctly
        assertEquals(establishmentUpdated.name(), establishment.getName());
        assertEquals(establishmentUpdated.address(), establishment.getAddress());
        assertEquals(establishmentUpdated.phone(), establishment.getPhone());
        assertEquals(establishment.getCarParkingSpaces(), (spacesCarBefore + establishmentUpdated.addCarParkingSpaces()));
        assertEquals(establishment.getMotorcycleParkingSpaces(), (spacesMotorcycleBefore + establishmentUpdated.addMotorcycleParkingSpaces()));

    }

    @Test
    @DisplayName("Should throw exception when try update a establishment who does not exist")
    public void updateEstablishmentWhoDoesNotExist() {

        var establishmentUpdated = new UpdateEstablishmentDto("newName", "newAddress",
                "newPhone", 20, 20);

        when(establishmentRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> establishmentService.updateEstablishment(99L, establishmentUpdated)
        );

        assertEquals("Establishment not found with id 99", exception.getMessage());

    }

    @Test
    @DisplayName("Should delete a establishment correctly")
    public void deleteEstablishment() {


        var establishment = EstablishmentFactory.buildEstablishment();

        when(establishmentRepository.findById(anyLong()))
                .thenReturn(Optional.of(establishment));

        establishmentService.deleteEstablishment(1L);

        verify(establishmentRepository, times(1)).delete(any(Establishment.class));
    }

    @Test
    @DisplayName("Should throw exception when try to delete a establishment who does not exist")
    public void deleteEstablishmentWhoDoesNotExist() {

        when(establishmentRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> establishmentService.deleteEstablishment(99L)
        );

        assertEquals("Establishment not found with id 99", exception.getMessage());
        verify(establishmentRepository, times(1)).findById(anyLong());
        verify(establishmentRepository, times(0)).delete(any(Establishment.class));
    }

}
