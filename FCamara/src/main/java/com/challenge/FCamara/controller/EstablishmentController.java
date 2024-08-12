package com.challenge.FCamara.controller;

import com.challenge.FCamara.dtos.SaveEstablishmentDto;
import com.challenge.FCamara.dtos.UpdateEstablishmentDto;
import com.challenge.FCamara.entities.Establishment;
import com.challenge.FCamara.service.EstablishmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/establishment")
public class EstablishmentController {

    private EstablishmentService establishmentService;

    public EstablishmentController (EstablishmentService establishmentService) {
        this.establishmentService = establishmentService;
    }

    @PostMapping
    public ResponseEntity<Void> saveEstablishment(@Valid @RequestBody SaveEstablishmentDto request) {

        establishmentService.saveEstablishment(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Establishment>> getAllEstablishments() {

        var allEstablishments = establishmentService.getAllEstablishments();
        return new ResponseEntity<>(allEstablishments, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEstablishment(@PathVariable(name = "id") Long id,
                                                    @RequestBody UpdateEstablishmentDto request) {

        establishmentService.updateEstablishment(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstablishment(@PathVariable(name = "id") Long id) {

        establishmentService.deleteEstablishment(id);
        return ResponseEntity.ok().build();
    }

}
