package com.challenge.FCamara.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

   private String brand;

   private String model;

   private String color;

   private String plate;

   private String type;

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "establishment_id")
   private Establishment establishment;

    public Vehicle() {}

    public Vehicle(String brand, String model, String color, String plate, String type, Establishment establishment) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }
}
