package com.epam.taxopark;

import com.epam.taxopark.derivedvehicleclasses.AutomobileExtended;
import com.epam.taxopark.taxoparkutils.DBContentRetrieve;
import com.epam.taxopark.taxoparkutils.JsonContentRetrieve;
import com.epam.taxopark.taxoparkutils.TaxoparkUtils;
import com.epam.taxopark.taxoparkutils.XMLContentRetrieve;
import com.epam.taxopark.vehicles.vehicletypes.Automobile;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.Minivan;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.PassengerCar;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.Suv;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.sql.SQLException;
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
    static List<String> tempStringList;

    public static void main(String[] args) throws IOException {

        int randomCarIndex;
        int taxoparkCost = 0;
        int listSize = 0;
        String stringResult;

        List<Automobile> list = new ArrayList<Automobile>();
        list.add(new PassengerCar("Fiat 500", 8500, 5.5f, false, 4));
        list.add(new PassengerCar("Ford Fiesta", 11400, 5.8f, false, 4));
        list.add(new PassengerCar("Renault Clio", 10600, 5.7f, false, 4));

        try {
            fileItems();
        } catch (InputMismatchException e) {
            System.out.println("INPUT MISMATCH EXCEPTION: " + e + "\nEnter positive integer number!\n");
        }

        String[][] strMass = new String[fileItemsCount][6];//Binary array for temporary storage of parsed String from DataSource.txt file

        try {
            fileReader();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND EXCEPTION: Check whether an input file exists, please!\n");
        }

//Parsing a string by substrings and storing in a binary array
        for (int i = 0; i < tempStringList.size(); i++) {
                strMass[i] = tempStringList.get(i).split(";");
            }

        for (int i = 0; i < tempStringList.size(); i++) {

                if (tempStringList.get(i).contains("Suv")) {
                    list.add(new Suv(strMass[i][1], Integer.parseInt(strMass[i][2]), Float.parseFloat(strMass[i][3]), Integer.parseInt(strMass[i][4])));
                } else if (tempStringList.get(i).contains("Minivan")) {
                    list.add(new Minivan(strMass[i][1], Integer.parseInt(strMass[i][2]), Float.parseFloat(strMass[i][3]), Boolean.parseBoolean(strMass[i][4]), Integer.parseInt(strMass[i][5])));
                } else if (tempStringList.get(i).contains("Passenger Car")) {
                    list.add(new PassengerCar(strMass[i][1], Integer.parseInt(strMass[i][2]), Float.parseFloat(strMass[i][3]), Boolean.parseBoolean(strMass[i][4]), Integer.parseInt(strMass[i][5])));
                }
        }
        System.out.println("\n************************************************************************");

//      Retrieve List records from Derby DB
        DBContentRetrieve dbContentRetrieve = new DBContentRetrieve();
        try {
            //dbContentRetrieve.dbContent();
            list.addAll(dbContentRetrieve.dbContent());//Add dbList to list
        } catch (SQLException e) {
            System.out.println("SQLException(ErrorCode = " + e.getErrorCode() + "): " + e + "\n");
        }

        System.out.println("\n************************************************************************");

//      Retrieve List records from XML file
        XMLContentRetrieve xmlContentRetrieve = new XMLContentRetrieve();
        try {
            list.addAll(xmlContentRetrieve.xmlContent());
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException was catched: " + e + "\n");
        } catch (SAXException e) {
            System.out.println("SAXException was catched: " + e + "\n");
        }

        System.out.println("\n************************************************************************");

//      Retrieve List records from JSON (stored as a static String)
        JsonContentRetrieve jsonContentRetrieve = new JsonContentRetrieve();
        list.addAll(jsonContentRetrieve.jsonContent());

        System.out.println("\n************************************************************************\n");

        list.get(0).setCarCost(8600);
        list.get(1).setFuelConsumption(5.85f);

//Counting of absolute (quantity) and relative (%) values of different autotypes in taxopark
        TaxoparkUtils taxoUtils = new TaxoparkUtils();
        taxoUtils.autoPercentageCount(list);

//Output through iterator
        System.out.println("Taxopark before sorting (in total " + list.size() + " automobiles from different sources (Datasource file, DB, XML and JSON)): ");
        taxoUtils.outputThroughIterator(list);
        System.out.println();

//Sorting by different parameters
//Sorting by CAR BRAND
        taxoUtils.taxoparkSorting(list, "carBrand");
        System.out.println();

//Sorting by CAR COST
        taxoUtils.taxoparkSorting(list, "carCost");
        System.out.println();

//Sorting by FUEL CONSUMPTION
        taxoUtils.taxoparkSorting(list, "fuelConsumption");
        System.out.println();

//Calculation of taxopark cost
        for (int i = 0; i < list.size(); i++) {
            taxoparkCost += list.get(i).getCarCost();
        }
        System.out.println("Taxopark cost: " + taxoparkCost + " USD\n");

//Enter a definite ranges of car cost and fuel consumption
        try {
            filterRangeReaderFunc();
        } catch (InputMismatchException e) {
            System.out.println("INPUT MISMATCH EXCEPTION: " + e + "\nEnter positive integer number as car cost or fuel consumption!");
        }

//Filter definite cars by car cost
        List<Automobile> filteredList = taxoUtils.carCostFiltering(list, lowRangeValueCarCost, highRangeValueCarCost);
        System.out.println();

//Counting of absolute (quantity) and relative (%) values of different autotypes in taxopark after filtering by car cost
        taxoUtils.autoPercentageCount(filteredList);

//Show cars list filtered by car cost
        taxoUtils.showingAutoByCarCost(filteredList);

//Filter definite cars by fuel consumption
        taxoUtils.fuelConsumptionFiltering(filteredList, lowRangeValueFuelConsumption, highRangeValueFuelConsumption);
        System.out.println();

//Counting of absolute (quantity) and relative (%) values of different autotypes in taxopark after filtering by car cost and fuel consumption
        taxoUtils.autoPercentageCount(filteredList);

//Show final cars list filtered by car cost and fuel consumption
        taxoUtils.showingAutobyCostAndFuel(filteredList);
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
        resultStoring(stringResult);

        System.out.println("\nSorting and filtering were DONE!");
    }

// Static method for reading ranges of car cost and fuel consumption
    static void filterRangeReaderFunc() throws IOException {

        Scanner scn = new Scanner(System.in);

        System.out.println("Do you want to use a filter (Answer Y or y for filter specifying on the next step): ");
        answerKey = scn.next().trim();

        if (answerKey.equals("Y") || answerKey.equals("y")) {

            System.out.println("Enter the low range value of car cost (positive integer): ");
            lowRangeValueCarCost = scn.nextInt();

            System.out.println("Enter the high range value of car cost (positive integer): ");
            highRangeValueCarCost = scn.nextInt();

            System.out.println("Enter the low range value of fuel consumption (positive integer): ");
            lowRangeValueFuelConsumption = scn.nextInt();

            System.out.println("Enter the high range value of fuel consumption (positive integer): ");
            highRangeValueFuelConsumption = scn.nextInt();
        } else System.out.println("No filter used!");

        System.out.println("\nSpecified car cost range is [" + lowRangeValueCarCost + "; " + highRangeValueCarCost + "]");
        System.out.println("Specified fuel consumption range is [" + lowRangeValueFuelConsumption + "; " + highRangeValueFuelConsumption + "]");
        if (lowRangeValueCarCost > highRangeValueCarCost) System.out.println("Please enter highRangeValueCarCost > lowRangeValueCarCost to get non-empty filter result!");
        if (lowRangeValueFuelConsumption > highRangeValueFuelConsumption) System.out.println("Please enter highRangeValueFuelConsumption > lowRangeValueFuelConsumption to get non-empty filter result!");
    }

//Static method for file items count manually specification
    static void fileItems() throws IOException{

        Scanner scn = new Scanner(System.in);

        System.out.println("How many items do you want to add to the taxopark list? Enter positive integer number equals to or less than lines quantity in DataSource.txt file located in project root folder:");
        fileItemsCount = scn.nextInt();

        System.out.println();
    }

//Static method for items reading from file
    static void fileReader() throws IOException {

        tempStringList = new ArrayList<String>();//Temp String List for lines from file storing

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

        System.out.println("+++DATASOURCE FILE+++");
        System.out.println("Taxopark items added from DataSource.txt file: ");
        for (int i = 0; i < tempStringList.size(); i++) {
            System.out.println("Element [" + i + "]: " + tempStringList.get(i));
        }
    }

//Static method for result storing
    static void resultStoring(String stringResult) throws IOException {

        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileWriter("ChoiceResult.txt"));
            outputStream.println(stringResult);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }

    }
}
