package com.challenge.FCamara.factory;

import com.challenge.FCamara.dtos.SaveEstablishmentDto;
import com.challenge.FCamara.entities.Establishment;

import java.util.List;

public class EstablishmentFactory {



    public static Establishment buildEstablishment() {

        var establishment = new Establishment("name", "CNPJ", "address", "phone"
                , 10, 10, null);

        return establishment;
    }

    public static Establishment buildEstablishmentWithNoSpaceForPark() {

        var establishment = new Establishment("name", "CNPJ", "address", "phone"
                , 10, 0, null);

        return establishment;
    }

    public static SaveEstablishmentDto buildEstablishmentDto() {

        var dto = new SaveEstablishmentDto("name", "CNPJ", "address",
                "phone", 10, 10);

        return dto;
    }

    public static List<Establishment> buildEstablishmentList() {

        var data1 = new Establishment("name", "CNPJ", "address", "phone"
                , 10, 10, null);

        var data2 = new Establishment("name2", "CNPJ2", "address2", "phone2"
                , 20, 20, null);

        return List.of(data1, data2);
    }


}
