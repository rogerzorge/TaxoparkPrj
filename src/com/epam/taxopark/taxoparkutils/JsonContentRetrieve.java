package com.epam.taxopark.taxoparkutils;

import com.epam.taxopark.derivedvehicleclasses.AutomobileExtended;
import com.epam.taxopark.derivedvehicleclasses.AutomobileExtendedList;
import com.epam.taxopark.vehicles.vehicletypes.Automobile;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.Minivan;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.PassengerCar;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.Suv;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.*;
import java.util.*;

/**
 * Created by Yahor_Faliazhynski on 12/6/2015.
 */
public class JsonContentRetrieve {

    private static String string = "{\n" +
            "\"autopark\":\n" +
            "\t[\n" +
            "\t\t{\n" +
            "\t\t\"autoId\": 10001,\n" +
            "\t\t\"autoType\": \"Passenger car\",\n" +
            "\t\t\"carBrand\": \"Hyindai Solaris\",\n" +
            "\t\t\"carCost\": 13150,\n" +
            "\t\t\"fuelConsumption\": 7.9,\n" +
            "\t\t\"allWheelDrive\": \"false\",\n" +
            "\t\t\"passengersNumber\": 4\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\"autoId\": 10002,\n" +
            "\t\t\"autoType\": \"Minivan\",\n" +
            "\t\t\"carBrand\": \"Toyota Alphard\",\n" +
            "\t\t\"carCost\": 29850,\n" +
            "\t\t\"fuelConsumption\": 12.2,\n" +
            "\t\t\"allWheelDrive\": \"false\",\n" +
            "\t\t\"passengersNumber\": 7\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\"autoId\": 10003,\n" +
            "\t\t\"autoType\": \"Suv\",\n" +
            "\t\t\"carBrand\": \"Chevrolet Niva\",\n" +
            "\t\t\"carCost\": 13100,\n" +
            "\t\t\"fuelConsumption\": 10.8,\n" +
            "\t\t\"allWheelDrive\": \"true\",\n" +
            "\t\t\"passengersNumber\": 4\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}";

    public List<Automobile> jsonContent() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        AutomobileExtendedList autoList = gson.fromJson(string, AutomobileExtendedList.class);
        List<Automobile> automobileJsonList = new ArrayList<>();

        for (int i = 0; i < autoList.getAutoparkList().size(); i++) {
            if (autoList.getAutoparkList().get(i).getAutoType().equals("Passenger car")) automobileJsonList.add(new PassengerCar(autoList.getAutoparkList().get(i).getCarBrand(), autoList.getAutoparkList().get(i).getCarCost(), autoList.getAutoparkList().get(i).getFuelConsumption(), autoList.getAutoparkList().get(i).getAllWhealsDrive(), autoList.getAutoparkList().get(i).getPassengersNumber()));
            else if (autoList.getAutoparkList().get(i).getAutoType().equals("Minivan")) automobileJsonList.add(new Minivan(autoList.getAutoparkList().get(i).getCarBrand(), autoList.getAutoparkList().get(i).getCarCost(), autoList.getAutoparkList().get(i).getFuelConsumption(), autoList.getAutoparkList().get(i).getAllWhealsDrive(), autoList.getAutoparkList().get(i).getPassengersNumber()));
            else if (autoList.getAutoparkList().get(i).getAutoType().equals("Suv")) automobileJsonList.add(new Suv(autoList.getAutoparkList().get(i).getCarBrand(), autoList.getAutoparkList().get(i).getCarCost(), autoList.getAutoparkList().get(i).getFuelConsumption(), autoList.getAutoparkList().get(i).getPassengersNumber()));
            else System.out.println("No such carType in object model!");
        }

        System.out.println("+++JSON+++");
        System.out.println("Taxopark automobiles retrieved from JSON: ");
        Iterator<Automobile> it = automobileJsonList.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().vehicleInfo());
        }

        return automobileJsonList;
    }

}
