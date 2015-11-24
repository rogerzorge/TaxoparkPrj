package com.epam.taxopark.comparators;

import com.epam.taxopark.vehicles.vehicletypes.Automobile;

import java.util.*;

/**
 * Created by Yahor_Faliazhynski on 11/13/2015.
 */
public class CarCostComparator implements Comparator<Automobile>{
    @Override
    public int compare(Automobile o1, Automobile o2) {

        //Sorting by descending order
        return o2.getCarCost() - o1.getCarCost();
    }
}
