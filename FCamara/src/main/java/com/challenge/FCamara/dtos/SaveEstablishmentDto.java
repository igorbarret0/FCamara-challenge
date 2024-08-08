package com.challenge.FCamara.dtos;

public record SaveEstablishmentDto(
        String name,
        String CNPJ,
        String address,
        String phone,
        int carParkingSpaces,
        int motorcycleParkingSpaces
) {
}
