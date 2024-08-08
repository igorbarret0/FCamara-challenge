package com.challenge.FCamara.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_establishments")
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "establishment_id")
    private Long id;

    private String name;

    private String CNPJ;

    private String address;

    private String phone;

    private int carParkingSpaces;

    private int motorcycleParkingSpaces;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "establishment")
    private List<Vehicle> vehicles;

    public Establishment() {}

    public Establishment(String name, String CNPJ, String address, String phone, int carParkingSpaces, int motorcycleParkingSpaces, List<Vehicle> vehicles) {
        this.name = name;
        this.CNPJ = CNPJ;
        this.address = address;
        this.phone = phone;
        this.carParkingSpaces = carParkingSpaces;
        this.motorcycleParkingSpaces = motorcycleParkingSpaces;
        this.vehicles = vehicles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCarParkingSpaces() {
        return carParkingSpaces;
    }

    public void setCarParkingSpaces(int carParkingSpaces) {
        this.carParkingSpaces = carParkingSpaces;
    }

    public int getMotorcycleParkingSpaces() {
        return motorcycleParkingSpaces;
    }

    public void setMotorcycleParkingSpaces(int motorcycleParkingSpaces) {
        this.motorcycleParkingSpaces = motorcycleParkingSpaces;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
