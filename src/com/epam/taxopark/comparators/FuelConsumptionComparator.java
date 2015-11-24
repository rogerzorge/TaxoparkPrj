package com.epam.taxopark.comparators;

import com.epam.taxopark.vehicles.vehicletypes.Automobile;

import java.util.*;

/**
 * Created by Yahor_Faliazhynski on 11/13/2015.
 */
public class FuelConsumptionComparator implements Comparator<Automobile>{
    @Override
    public int compare(Automobile o1, Automobile o2) {
        Float fuelCons1 = o1.getFuelConsumption();
        Float fuelCons2 = o2.getFuelConsumption();

        //Sorting by ascending order
        return fuelCons1.compareTo(fuelCons2);
    }
}
