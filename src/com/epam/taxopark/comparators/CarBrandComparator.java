package com.epam.taxopark.comparators;

import com.epam.taxopark.vehicles.vehicletypes.Automobile;

import java.util.*;

/**
 * Created by Yahor_Faliazhynski on 11/13/2015.
 */
public class CarBrandComparator implements Comparator<Automobile> {
    @Override
    public int compare(Automobile o1, Automobile o2) {
        String autoBrand1 = o1.getCarBrand();
        String autoBrand2 = o2.getCarBrand();

        //Sorting by ascending order
        return autoBrand1.compareTo(autoBrand2);
    }
}
