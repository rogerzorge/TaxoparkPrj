package com.epam.taxopark.vehicles.vehicletypes.autotypes;

import com.epam.taxopark.vehicles.vehicletypes.Automobile;

/**
 * Created by Yahor_Faliazhynski on 11/17/2015.
 */
public class Minivan extends Automobile {

    public Minivan() {}

    public Minivan(String carBrand, int carCost, float fuelConsumption, boolean allWheelsDrive, int passengersNumber) {
        super(carBrand, carCost, fuelConsumption, allWheelsDrive, passengersNumber);
    }

    @Override
    public String ride () {
        return " is riding to a destination point by highways and can transport 7+ passengers";
    }

    @Override
    public String vehicleInfo() {
        return "Automobile type: Minivan; " + super.vehicleInfo();
    }

}
