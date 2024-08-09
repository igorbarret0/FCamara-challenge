package com.challenge.FCamara.service;

import com.challenge.FCamara.dtos.SaveEstablishmentDto;
import com.challenge.FCamara.dtos.UpdateEstablishmentDto;
import com.challenge.FCamara.entities.Establishment;
import com.challenge.FCamara.repository.EstablishmentRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstablishmentService {

    private EstablishmentRepository establishmentRepository;

    public EstablishmentService(EstablishmentRepository establishmentRepository) {
        this.establishmentRepository = establishmentRepository;
    }

    public void saveEstablishment(SaveEstablishmentDto request) {

        var newEstablishment = new Establishment();
        newEstablishment.setName(request.name());
        newEstablishment.setCNPJ(request.CNPJ());
        newEstablishment.setAddress(request.address());
        newEstablishment.setPhone(request.phone());
        newEstablishment.setCarParkingSpaces(request.carParkingSpaces());
        newEstablishment.setMotorcycleParkingSpaces(request.motorcycleParkingSpaces());

        establishmentRepository.save(newEstablishment);

    }

    public List<Establishment> getAllEstablishments() {

        var allEstablishments = establishmentRepository.findAll();
        return allEstablishments;
    }

    public void updateEstablishment(Long id, UpdateEstablishmentDto request) {

        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Establishment not found with id " + id));

        if (request.name() != null) {
            establishment.setName(request.name());
        }
        if (request.address() != null) {
            establishment.setAddress(request.address());
        }
        if (request.phone() != null) {
            establishment.setPhone(request.phone());
        }
        if (request.addCarParkingSpaces() != 0) {
            establishment.setCarParkingSpaces(establishment.getCarParkingSpaces() + request.addCarParkingSpaces());
        }
        if (request.addMotorcycleParkingSpaces() != 0) {
            establishment.setMotorcycleParkingSpaces(establishment.getMotorcycleParkingSpaces() + request.addMotorcycleParkingSpaces());
        }

        establishmentRepository.save(establishment);

    }

    public void deleteEstablishment(Long id) {

        var establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Establishment not found with id " + id));

        establishmentRepository.delete(establishment);
    }

}
