package com.epam.taxopark.vehicles.vehicletypes;

import com.epam.taxopark.vehicles.Vehicle;

/**
 * Created by Yahor_Faliazhynski on 11/13/2015.
 */
public class Automobile extends Vehicle {

    public Automobile() {}

    public Automobile(String carBrand, int carCost, float fuelConsumption, boolean allWheelsDrive, int passengersNumber) {
        this.carBrand = carBrand;
        this.carCost = carCost;
        this.fuelConsumption = fuelConsumption;
        this.allWheelsDrive = allWheelsDrive;
        this.passengersNumber = passengersNumber;
    }

    @Override
    public String vehicleInfo() {
        return "Brand: " + this.carBrand + "; Cost: " + this.carCost + "; Fuel consumption: " + this.fuelConsumption +
               "; All wheels drive support: " + this.allWheelsDrive + "; Passengers number: " + this.passengersNumber;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getCarCost() {
        return carCost;
    }

    public void setCarCost(int carCost) {
        this.carCost = carCost;
    }

    public float getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public boolean getAllWhealsDrive () {
        return allWheelsDrive;
    }

    public void setAllWhealsDrive (boolean allWhealsDrive) {
        this.allWheelsDrive = allWhealsDrive;
    }

    public int getPassengersNumber () {
        return  passengersNumber;
    }

    public void setPassengersNumber (int passengersNumber) {
        this.passengersNumber = passengersNumber;
    }

}
