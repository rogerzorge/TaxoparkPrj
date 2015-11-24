package com.epam.taxopark.vehicles.vehicletypes.autotypes;

import com.epam.taxopark.vehicles.vehicletypes.Automobile;

/**
 * Created by Yahor_Faliazhynski on 11/17/2015.
 */
public class Suv extends Automobile {

    public Suv(String carBrand, int carCost, float fuelConsumption, boolean allWheelsDrive, int passengersNumber) {
        this.carBrand = carBrand;
        this.carCost = carCost;
        this.fuelConsumption = fuelConsumption;
        this.allWheelsDrive = allWheelsDrive;
        this.passengersNumber = passengersNumber;
    }

    @Override
    public String ride () {
        return " is riding to a destination point by the shortest route";
    }

    @Override
    public String vehicleInfo() {
        return "Automobile type: SUV; " + super.vehicleInfo();
    }

}
