package com.challenge.FCamara.dtos;


public record UpdateEstablishmentDto(
        String name,
        String address,
        String phone,
        int addCarParkingSpaces,
        int addMotorcycleParkingSpaces

) {
}
