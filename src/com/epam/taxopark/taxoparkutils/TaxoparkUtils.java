package com.epam.taxopark.taxoparkutils;

import com.epam.taxopark.comparators.CarBrandComparator;
import com.epam.taxopark.comparators.CarCostComparator;
import com.epam.taxopark.comparators.FuelConsumptionComparator;
import com.epam.taxopark.userexceptions.CustomArithmeticException;
import com.epam.taxopark.userexceptions.CustomArrayIndexOutofBoundsException;
import com.epam.taxopark.userexceptions.CustomNullPointerException;
import com.epam.taxopark.vehicles.vehicletypes.Automobile;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.Minivan;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.PassengerCar;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.Suv;

import java.util.*;

/**
 * Created by Yahor_Faliazhynski on 12/1/2015.
 */
public class TaxoparkUtils {

    public void autoPercentageCount (List<Automobile> list) {

        int passCarCount = 0;
        int minivanCount = 0;
        int suvCount = 0;

        try {for (int i = 0; i <= list.size(); i++) {
            if (i == list.size()) throw new CustomArrayIndexOutofBoundsException(i);

            if (list.get(i) instanceof PassengerCar) passCarCount++;
            else if (list.get(i) instanceof Minivan) minivanCount++;
            else if (list.get(i) instanceof Suv) suvCount++;
        }
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("CUSTOM ARRAY INDEX OUT OF BOUNDS EXCEPTION: " + e);
        }

        try {
            if (list.size() == 0) throw new CustomArithmeticException("Dividing by zero! Check list.size() value, please!");

            System.out.println("Passenger cars in taxopark: " + passCarCount + "(" + Math.round(passCarCount * 100 / list.size()) + "%)");
            System.out.println("Minivans in taxopark: " + minivanCount + "(" + Math.round(minivanCount * 100 / list.size()) + "%)");
            System.out.println("SUVs in taxopark: " + suvCount + "(" + (100 - Math.round(passCarCount * 100 / list.size()) - Math.round(minivanCount * 100 / list.size())) + "%)\n");
        } catch (ArithmeticException e) {
            System.out.println("CUSTOM ARITHMETIC EXCEPTION: " + e);
        }
    }

    public void outputThroughIterator (List<Automobile> list) {

        Iterator<Automobile> it;
        it = list.iterator();
      //it = null;
// Throwing a custom NPE if it == null and printing it into console
// Without this line if it == null built-in NPE is thrown when we try to call any method of it, f.e. <it.hasNext()>
        try {
            if (it == null) throw new CustomNullPointerException("Custom NullPointerException! It appears when declared link-variable doesn't refer to any real object in Heap! Check this case.");

            while (it.hasNext()) {
                System.out.println(it.next().vehicleInfo());
            }
        }catch (NullPointerException e) {
            System.out.println("CUSTOM NULL POINTER EXCEPTION: " + e);
        }
    }

    public void taxoparkSorting(List<Automobile> list, String comparatorType) {

        switch (comparatorType) {
            case "carBrand":
                Collections.sort(list, new CarBrandComparator());
                System.out.println("Taxopark after sorting by CAR BRAND:");
                try {
                    for (int i = 0; i <= list.size(); i++) {

                        //Throwing a custom ArrayIndexOutofBoundsException (int attribute as index in constructor) if i == list.size() and printing it into console
                        if (i == list.size()) throw new CustomArrayIndexOutofBoundsException(i);

                        System.out.println(i + ") " + list.get(i).getCarBrand() + "\t" + list.get(i).getCarCost() + "\t" + list.get(i).getFuelConsumption() + "\t" + list.get(i).getAllWhealsDrive() + "\t" + list.get(i).getPassengersNumber());
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("CUSTOM ARRAY INDEX OUT OF BOUNDS EXCEPTION: " + e);
                } break;
            case "carCost":
                Collections.sort(list, new CarCostComparator());
                System.out.println("Taxopark after sorting by CAR COST:");
                try {
                    for (int i = 0; i <= list.size(); i++) {

                        //Throwing a custom ArrayIndexOutofBoundsException (String attribute as text message in constructor) if i == list.size() and printing it into console
                        if (i == list.size())
                            throw new CustomArrayIndexOutofBoundsException("Custom ArrayIndexOutofBoundsException! It appears because out of bound index of the list [" + list.size() + "] was chosen!" + " Check this case.");

                        System.out.println(i + ") " + list.get(i).getCarBrand() + "\t" + list.get(i).getCarCost() + "\t" + list.get(i).getFuelConsumption() + "\t" + list.get(i).getAllWhealsDrive() + "\t" + list.get(i).getPassengersNumber());
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("CUSTOM ARRAY INDEX OUT OF BOUNDS EXCEPTION: " + e);
                } break;
            case "fuelConsumption":
                Collections.sort(list, new FuelConsumptionComparator());
                System.out.println("Taxopark after sorting by FUEL CONSUMPTION:");
                try {
                    for (int i = 0; i <= list.size(); i++) {

                        //Throwing a custom ArrayIndexOutofBoundsException (without any attribute in constructor) if i == list.size() and printing it into console
                        if (i == list.size()) throw new CustomArrayIndexOutofBoundsException();

                        System.out.println(i + ") " + list.get(i).getCarBrand() + "\t" + list.get(i).getCarCost() + "\t" + list.get(i).getFuelConsumption() + "\t" + list.get(i).getAllWhealsDrive() + "\t" + list.get(i).getPassengersNumber());
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("CUSTOM ARRAY INDEX OUT OF BOUNDS EXCEPTION: " + e);
                } break;
        }
    }

    public List<Automobile> carCostFiltering(List<Automobile> list, int lowRangeValueCarCost, int highRangeValueCarCost) {
        List<Automobile> filteredList = new ArrayList<Automobile>();

        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).getCarCost() >= lowRangeValueCarCost) && (list.get(i).getCarCost() <= highRangeValueCarCost)) {
                filteredList.add(list.get(i));
            }
        } return filteredList;
    }

    public void fuelConsumptionFiltering(List<Automobile> list, int lowRangeValueFuelConsumption, int highRangeValueFuelConsumption) {

        if (list.size() == 0) {
            System.out.println("No car in taxopark from specified CAR COST range");
        }else {
            for (int i = (list.size() - 1); i >= 0; i--) {
                if ((list.get(i).getFuelConsumption() < lowRangeValueFuelConsumption) || (list.get(i).getFuelConsumption() > highRangeValueFuelConsumption)) {
                    list.remove(i);
                }
            }
        }
    }

    public void showingAutoByCarCost(List<Automobile> list) {

        if (list.size() != 0) {
            System.out.println("The list of cars filtered by CAR COST (" + list.size() + " items):");
            Iterator<Automobile> it2 = list.iterator();
            while (it2.hasNext()) {
                System.out.println(it2.next().vehicleInfo());
            }
        }

    }

    public void showingAutobyCostAndFuel(List<Automobile> list) {

        if (list.size() == 0){
            System.out.println("No car in taxopark from specified FUEL CONSUMPTION range");
        } else {

            System.out.println("The list of cars filtered by CAR COST and FUEL CONSUMPTION (" + list.size() + " items):");
            Iterator<Automobile> it3 = list.iterator();
            while (it3.hasNext()) {
                System.out.println(it3.next().vehicleInfo());
            }
        }
    }
}
