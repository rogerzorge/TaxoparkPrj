package com.epam.taxopark.vehicles.vehicletypes.autotypes;

import com.epam.taxopark.vehicles.vehicletypes.Automobile;

/**
 * Created by Yahor_Faliazhynski on 11/17/2015.
 */
public class Suv extends Automobile {

    public Suv() {}

    public Suv(String carBrand, int carCost, float fuelConsumption, int passengersNumber) {
        super(carBrand, carCost, fuelConsumption, true, passengersNumber);
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
