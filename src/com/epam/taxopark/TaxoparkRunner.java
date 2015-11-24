package com.epam.taxopark;

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
import com.sun.javafx.scene.control.skin.VirtualFlow;

import javax.print.DocFlavor;
import java.io.*;
import java.util.*;

/**
 * Created by Yahor_Faliazhynski on 11/13/2015.
 */
public class TaxoparkRunner {

    static String answerKey;
    static int lowRangeValueCarCost;
    static int highRangeValueCarCost;
    static int lowRangeValueFuelConsumption;
    static int highRangeValueFuelConsumption;
    static int fileItemsCount;

    public static void main(String[] args) throws IOException {

        int randomCarIndex;
        int taxoparkCost = 0;
        int listSize = 0;
        int passCarCount = 0;
        int minivanCount = 0;
        int suvCount = 0;
        String stringResult;

        List<Automobile> list = new ArrayList<Automobile>();
        list.add(new PassengerCar("Fiat 500", 8500, 5.5f, false, 4));
        list.add(new PassengerCar("Ford Fiesta", 11400, 5.8f, false, 4));
        list.add(new PassengerCar("Renault Clio", 10600, 5.7f, false, 4));
        list.add(new PassengerCar("Audi A1", 14600, 5.4f, false, 4));
        list.add(new PassengerCar("VW Golf", 14500, 5.9f, false, 5));
        list.add(new PassengerCar("Peugeot 308", 13900, 6.0f, false, 5));
        list.add(new PassengerCar("Alfa Romeo Giulietta", 14900, 6.2f, false, 5));
        list.add(new PassengerCar("Toyota Auris", 15300, 6.3f, false, 5));
        list.add(new PassengerCar("Mercedes A Class", 21000, 7.4f, false, 4));
        list.add(new Minivan("Citroen C4 Picasso", 16350, 8.6f, false, 7));
        list.add(new Minivan("VW Touran", 18700, 9.1f, false, 7));
        list.add(new Suv("Scoda Yeti", 18300, 5.6f, true, 5));
        list.add(new Minivan("Citroen C4 Picasso", 16200, 8.6f, false, 7));
        list.add(new Minivan("Opel Zafira", 19200, 9.7f, false, 7));
        list.add(new PassengerCar("Volvo S80", 29300, 8.9f, true, 5));
        list.add(new PassengerCar("Jaguar XF", 43150, 12.7f, true, 5));
        list.add(new Minivan("Mercedes Vito", 39990, 13.1f, false, 9));
        list.add(new Minivan("Seat Alhambra", 27950, 12.3f, false, 7));
        list.add(new PassengerCar("Mercedes S Class Estate", 68900, 14.9f, true, 5));
        list.add(new Minivan("Ford S Max", 23700, 9.9f, false, 7));
        list.add(new Minivan("Ford Galaxy", 24600, 10.3f, false, 7));
        list.add(new Minivan("VW Sharan", 22100, 9.6f, false, 7));
        list.add(new Minivan("VW Caravelle", 28700, 11.8f, false, 8));
        list.add(new Minivan("Ford Transit", 29100, 12.6f, false, 9));
        list.add(new Suv("VW Tiguan",25900, 7.5f, true, 5));
        list.add(new Suv("Ford Kuga", 21450, 8.9f, true, 5));
        list.add(new Suv("BMW X1", 36700, 9.8f, true, 5));
        list.add(new Suv("Porsche Macan", 78900, 12.8f, true, 5));
        list.add(new Suv("Porsche Cayenne", 89950, 13.9f, true, 5));

        list.get(0).setCarCost(8600);
        list.get(1).setFuelConsumption(5.85f);
        list.get(4).setCarBrand("VW Golf 6");

        try {
            fileItems();
        } catch (InputMismatchException e) {
            System.out.println("INPUT MISMATCH EXCEPTION: " + e + "\nEnter positive integer number!\n");
        }

        List<String> tempStringList = new ArrayList<String>();//Temp String List for lines from file storing
        String[][] strMass = new String[fileItemsCount][6];//Binary array for temporary storage of parsed String from DataSource.txt file

        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new FileReader("DataSource.txt"));
            String line;
            for (int i = 0; i < fileItemsCount; i++) {
                if ((line = inputStream.readLine()) != null) {
                    tempStringList.add(line);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        System.out.println("Taxopark items added from DataSource.txt file: ");
        for (int i = 0; i < tempStringList.size(); i++) {
                System.out.println("Element [" + i + "]: " + tempStringList.get(i));
            }

        //Parsing a string by substrings and storing in a binary array
        for (int i = 0; i < tempStringList.size(); i++) {
                strMass[i] = tempStringList.get(i).split(";");
            }

        for (int i = 0; i < tempStringList.size(); i++) {

                if (tempStringList.get(i).contains("Suv")) {
                    list.add(new Suv(strMass[i][1], Integer.parseInt(strMass[i][2]), Float.parseFloat(strMass[i][3]), Boolean.parseBoolean(strMass[i][4]), Integer.parseInt(strMass[i][5])));
                } else if (tempStringList.get(i).contains("Minivan")) {
                    list.add(new Minivan(strMass[i][1], Integer.parseInt(strMass[i][2]), Float.parseFloat(strMass[i][3]), Boolean.parseBoolean(strMass[i][4]), Integer.parseInt(strMass[i][5])));
                } else if (tempStringList.get(i).contains("Passenger Car")) {
                    list.add(new PassengerCar(strMass[i][1], Integer.parseInt(strMass[i][2]), Float.parseFloat(strMass[i][3]), Boolean.parseBoolean(strMass[i][4]), Integer.parseInt(strMass[i][5])));
                }
        }

        System.out.println();

        //Counting of absolute (quantity) and relative (%) values of different autotypes in taxopark
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getClass().getName().contains("PassengerCar")) passCarCount++;
                else if (list.get(i).getClass().getName().contains("Minivan")) minivanCount++;
                else if (list.get(i).getClass().getName().contains("Suv")) suvCount++;
        }
        System.out.println("Passenger cars in taxopark: " + passCarCount + "(" + Math.round(passCarCount * 100 / list.size()) + "%)");
        System.out.println("Minivans in taxopark: " + minivanCount + "(" + Math.round(minivanCount * 100 / list.size()) + "%)");
        System.out.println("SUVs in taxopark: " + suvCount + "(" + (100 - Math.round(passCarCount * 100 / list.size()) - Math.round(minivanCount * 100 / list.size())) + "%)");

        System.out.println();

        //Output through iterator
        System.out.println("Taxopark before sorting:");
        try {
            Iterator<Automobile> it;
            it = list.iterator();
//            it = null;
            //Throwing a custom NPE if it == null and printing it into console
            //Without this line if it == null built-in NPE is thrown when we try to call any method of it, f.e. <it.hasNext()>
            if (it == null) throw new CustomNullPointerException("Custom NullPointerException! It appears when declared link-variable doesn't refer to any real object in Heap! Check this case.");

            while (it.hasNext()) {
                System.out.println(it.next().vehicleInfo());
            }
        } catch (NullPointerException e) {
            System.out.println("CUSTOM NULL POINTER EXCEPTION: " + e);
        }

        System.out.println();

        //Sorting by different parameters
        //Sorting by CAR BRAND
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
        }

        System.out.println();

        //Sorting by CAR COST
        Collections.sort(list, new CarCostComparator());
        System.out.println("Taxopark after sorting by CAR COST:");
        try {
            for (int i = 0; i <= list.size(); i++) {

                //Throwing a custom ArrayIndexOutofBoundsException (String attribute as text message in constructor) if i == list.size() and printing it into console
                if (i == list.size()) throw new CustomArrayIndexOutofBoundsException("Custom ArrayIndexOutofBoundsException! It appears because out of bound index of the list [" + list.size() + "] was chosen!" + " Check this case.");

                System.out.println(i + ") " + list.get(i).getCarBrand() + "\t" + list.get(i).getCarCost() + "\t" + list.get(i).getFuelConsumption() + "\t" + list.get(i).getAllWhealsDrive() + "\t" + list.get(i).getPassengersNumber());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("CUSTOM ARRAY INDEX OUT OF BOUNDS EXCEPTION: " + e);
        }

        System.out.println();

        //Sorting by FUEL CONSUMPTION
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
        }

        System.out.println();

        //Calculation of taxopark cost
        for (int i = 0; i < list.size(); i++) {
            taxoparkCost += list.get(i).getCarCost();
        }
        System.out.println("Taxopark cost: " + taxoparkCost + " USD");

        System.out.println();

        //Enter a definite ranges of car cost and fuel consumption
        try {
            filterRangeReaderFunc();
        } catch (InputMismatchException e) {
            System.out.println("INPUT MISMATCH EXCEPTION: " + e + "\nEnter positive integer number as car cost or fuel consumption!");
        }

        System.out.println();

        System.out.println("Specified car cost range is [" + lowRangeValueCarCost + "; " + highRangeValueCarCost + "]");
        System.out.println("Specified fuel consumption range is [" + lowRangeValueFuelConsumption + "; " + highRangeValueFuelConsumption + "]");
        if (lowRangeValueCarCost > highRangeValueCarCost) System.out.println("Please enter highRangeValueCarCost > lowRangeValueCarCost to get non-empty filter result!");
        if (lowRangeValueFuelConsumption > highRangeValueFuelConsumption) System.out.println("Please enter highRangeValueFuelConsumption > lowRangeValueFuelConsumption to get non-empty filter result!");

        //Filter definite cars by car cost
        List<Automobile> filteredList = new ArrayList<Automobile>();

        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).getCarCost() >= lowRangeValueCarCost) && (list.get(i).getCarCost() <= highRangeValueCarCost)) {
                filteredList.add(list.get(i));
            }
        }

        System.out.println();

        //Counting of absolute (quantity) and relative (%) values of different autotypes in taxopark after filtering by car cost
        passCarCount = 0;
        minivanCount = 0;
        suvCount = 0;
        try {
            for (int i = 0; i < filteredList.size(); i++) {
                if (filteredList.get(i).getClass().getName().contains("PassengerCar")) passCarCount++;
                else if (filteredList.get(i).getClass().getName().contains("Minivan")) minivanCount++;
                else if (filteredList.get(i).getClass().getName().contains("Suv")) suvCount++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Esception 1: " + e);
        }

        //Throwing custom ArithmeticException if dividing by zero
        try {
            System.out.println("Passenger cars in chosen CAR COST range: " + passCarCount + "(" + Math.round(passCarCount * 100 / filteredList.size()) + "%)");
            System.out.println("Minivans in chosen CAR COST range: " + minivanCount + "(" + Math.round(minivanCount * 100 / filteredList.size()) + "%)");
            System.out.println("SUVs in chosen CAR COST range: " + suvCount + "(" + (100 - Math.round(passCarCount * 100 / list.size()) - Math.round(minivanCount * 100 / list.size())) + "%)");
        } catch (ArithmeticException e) {
            System.out.println("BUILT-IN ARITHMETIC EXCEPTION: " + e);
        }

        //Show cars list filtered by car cost
        if (filteredList.size() != 0) {
            System.out.println("The list of cars filtered by CAR COST (" + filteredList.size() + " items):");
            Iterator<Automobile> it2 = filteredList.iterator();
            while (it2.hasNext()) {
                System.out.println(it2.next().vehicleInfo());
            }
        }

        listSize = filteredList.size();

        //Filter definite cars by fuel consumption
        if (listSize == 0) {
            System.out.println("No car in taxopark from specified CAR COST range");
        }else {
            for (int i = (listSize - 1); i >= 0; i--) {
                if ((filteredList.get(i).getFuelConsumption() < lowRangeValueFuelConsumption) || (filteredList.get(i).getFuelConsumption() > highRangeValueFuelConsumption)) {
                    filteredList.remove(i);
                }
            }
        }

        System.out.println();

        //Counting of absolute (quantity) and relative (%) values of different autotypes in taxopark after filtering by car cost and fuel consumption
        passCarCount = 0;
        minivanCount = 0;
        suvCount = 0;
        try {
            for (int i = 0; i < filteredList.size(); i++) {
                if (filteredList.get(i).getClass().getName().contains("PassengerCar")) passCarCount++;
                else if (filteredList.get(i).getClass().getName().contains("Minivan")) minivanCount++;
                else if (filteredList.get(i).getClass().getName().contains("Suv")) suvCount++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception 2: " + e);
        }

        //Throwing built-in ArithmeticException if dividing by zero
        try {
            if (filteredList.size() == 0) throw new CustomArithmeticException("Custom ArithmeticException! It appears if dividing by zero. Check this case.");
            System.out.println("Passenger cars in chosen CAR COST and FUEL CONSUMPTION range: " + passCarCount + "(" + Math.round(passCarCount * 100 / filteredList.size()) + "%)");
            System.out.println("Minivans in chosen CAR COST and FUEL CONSUMPTION range: " + minivanCount + "(" + Math.round(minivanCount * 100 / filteredList.size()) + "%)");
            System.out.println("SUVs in chosen CAR COST and FUEL CONSUMPTION range: " + suvCount + "(" + (100 - Math.round(passCarCount * 100 / filteredList.size()) - Math.round(minivanCount * 100 / list.size())) + "%)");
        } catch (ArithmeticException e) {
            System.out.println("CUSTOM ARITHMETIC EXCEPTION: " + e);
        }

        //Show final cars list filtered by car cost and fuel consumption
        if (filteredList.size() == 0){
            System.out.println("No car in taxopark from specified FUEL CONSUMPTION range");
        } else {

            System.out.println("The list of cars filtered by CAR COST and FUEL CONSUMPTION (" + filteredList.size() + " items):");
            Iterator<Automobile> it3 = filteredList.iterator();
            while (it3.hasNext()) {
                System.out.println(it3.next().vehicleInfo());
            }
        }

        System.out.println();
        //Choosing a random car from list collection filtered by car cost and fuel consumption
        randomCarIndex = (int)(Math.random() * (filteredList.size()));

        if (filteredList.size() != 0) {
            stringResult = "I have chosen a car by [" + randomCarIndex + "] index - " + filteredList.get(randomCarIndex).getCarBrand() +
                    "\n" + filteredList.get(randomCarIndex).getCarBrand() + filteredList.get(randomCarIndex).engineStarts() + "\n" +
                    filteredList.get(randomCarIndex).getCarBrand() + filteredList.get(randomCarIndex).ride();
            System.out.println(stringResult);
        } else {
            stringResult = "No car was chosen! Try again!";
            System.out.println(stringResult);
        }

        //Printing of chosen car actions into ChoiceResult.txt file in project folder
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileWriter("ChoiceResult.txt"));
            outputStream.println(stringResult);
        } finally {
            if (outputStream != null) {
            outputStream.close();
            }
        }

        System.out.println();
        System.out.println("Sorting and filtering was DONE!");
    }

    // Static method for reading ranges of car cost and fuel consumption
    static void filterRangeReaderFunc() throws IOException {

        Scanner scn = new Scanner(System.in);

        System.out.println("Do you want to use filter (Answer Y or y for filter specifying on the next step): ");
        answerKey = scn.next().trim();

        if (answerKey.equals("Y") || answerKey.equals("y")) {

            System.out.println("Enter the low range value of car cost (integer): ");
            lowRangeValueCarCost = scn.nextInt();

            System.out.println("Enter the high range value of car cost (integer): ");
            highRangeValueCarCost = scn.nextInt();

            System.out.println("Enter the low range value of fuel consumption (integer): ");
            lowRangeValueFuelConsumption = scn.nextInt();

            System.out.println("Enter the high range value of fuel consumption (integer): ");
            highRangeValueFuelConsumption = scn.nextInt();
        } else System.out.println("No filter used!");
    }

    //Static method for file items count manually specification
    static void fileItems() throws IOException{

        Scanner scn = new Scanner(System.in);

        System.out.println("How many items do you want to add to the taxopark list? Enter positive integer number equals to or less than lines quantity in DataSource.txt file located in project root folder:");
        fileItemsCount = scn.nextInt();

        System.out.println();
    }
}
