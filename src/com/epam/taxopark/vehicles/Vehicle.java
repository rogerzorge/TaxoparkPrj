package com.epam.taxopark.vehicles;

/**
 * Created by Yahor_Faliazhynski on 11/13/2015.
 */
public abstract class Vehicle {

    protected String carBrand;
    protected int carCost;
    protected float fuelConsumption;
    protected boolean allWheelsDrive;
    protected int passengersNumber;

    public String engineStarts() {
        return " engine was started";
    }

    public String ride() {
        return " is riding to a destination point";
    }

    public abstract String vehicleInfo();

 }