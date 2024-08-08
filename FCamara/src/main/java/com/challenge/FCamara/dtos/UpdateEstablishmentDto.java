package com.challenge.FCamara.dtos;


public record UpdateEstablishmentDto(
        String name,
        String address,
        String phone,
        Integer carParkingSpaces,
        Integer motorcycleParkingSpaces

) {
}
