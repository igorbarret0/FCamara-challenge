package com.challenge.FCamara.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String color;

   @Column(unique = true)
   @NotBlank
   private String plate;

   @Enumerated(EnumType.STRING)
   @NotNull
   private VehicleType type;

   @ManyToOne()
   @JoinColumn(name = "establishment_id")
   private Establishment establishment;

    public Vehicle() {}

    public Vehicle(String brand, String model, String color, String plate, VehicleType type, Establishment establishment) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.plate = plate;
        this.type = type;
        this.establishment = establishment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }
}
