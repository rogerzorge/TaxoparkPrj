package com.epam.taxopark.taxoparkutils;

import com.epam.taxopark.vehicles.vehicletypes.Automobile;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.Minivan;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.PassengerCar;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.Suv;
import org.apache.derby.jdbc.EmbeddedDriver;

import java.sql.*;
import java.util.*;

/**
 * Created by Yahor_Faliazhynski on 12/4/2015.
 */
public class DBContentRetrieve {

    public List<Automobile> dbContent() throws SQLException{

        DriverManager.registerDriver(new EmbeddedDriver());
        Connection connection = DriverManager.getConnection("jdbc:derby:my_auto;create=true;user=me;password=mine");
        System.out.println("+++DB+++");
        System.out.println("Connected to [my_auto] database!");
        Statement statement = connection.createStatement();

//      DB/table creation and records insertion
        try {
            statement.executeUpdate("CREATE TABLE autopark (ID int GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), CARTYPE varchar(64) NOT NULL, CARBRAND varchar(255) NOT NULL, CARCOST int NOT NULL, FUELCONSUMPTION int NOT NULL, ALLWHEELSDRIVE int NOT NULL, PASSENGERNUMBER int NOT NULL)");
            statement.addBatch("INSERT INTO autopark (CARTYPE, CARBRAND, CARCOST, FUELCONSUMPTION, ALLWHEELSDRIVE, PASSENGERNUMBER) VALUES ('Passenger car', 'Chrysler Sebring', 18900, 13.7, 0, 4)");
            statement.addBatch("INSERT INTO autopark (CARTYPE, CARBRAND, CARCOST, FUELCONSUMPTION, ALLWHEELSDRIVE, PASSENGERNUMBER) VALUES ('Minivan', 'Peugeot 5008', 25900, 11.8, 0, 7)");
            statement.addBatch("INSERT INTO autopark (CARTYPE, CARBRAND, CARCOST, FUELCONSUMPTION, ALLWHEELSDRIVE, PASSENGERNUMBER) VALUES ('Suv', 'Mitsubishi Outlander', 27500, 12.4, 1, 4)");
            statement.executeBatch();
        }catch (SQLException e) {
            System.out.println("SQLException(ErrorCode = " + e.getErrorCode() + "): " + e);
        }

        ResultSet resultSet = statement.executeQuery("SELECT * FROM autopark");
        ResultSetMetaData metaData = resultSet.getMetaData();

        int dbLines = 0;//DB records counter
        int columnCount = metaData.getColumnCount();

//      DB records output
        System.out.println("DB records to retrieve into Automobile object:");
        while (resultSet.next()) {
            dbLines++;
            System.out.println("==========================");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(String.format("'%s' : '%s'", metaData.getColumnName(i), resultSet.getString(i)));
            }
            System.out.println("==========================");
        }
        resultSet.close();

        System.out.println("\nRecords number in table: " + dbLines);
        System.out.println("Columns number: " + columnCount + "\n");

        resultSet = statement.executeQuery("SELECT * FROM autopark");

//      Create empty List elements
        List<Automobile> dbList = new ArrayList<Automobile>();
        while (resultSet.next()) {
            if (resultSet.getString(2).equals("Passenger car")) dbList.add(new PassengerCar());
            else if (resultSet.getString(2).equals("Minivan")) dbList.add(new Minivan());
            else if (resultSet.getString(2).equals("Suv")) dbList.add(new Suv());
            else System.out.println("No such carType in object model!");
        }
        resultSet.close();

        resultSet = statement.executeQuery("SELECT * FROM autopark");

//      Setting of dbList elements by DB table values
        int j = 0;
        while (resultSet.next()) {
            for (int i = 2; i <= columnCount; i++) {
                switch (i) {
                    case 3: dbList.get(j).setCarBrand(resultSet.getString(i));
                        break;
                    case 4: dbList.get(j).setCarCost(resultSet.getInt(i));
                        break;
                    case 5: dbList.get(j).setFuelConsumption(resultSet.getInt(i));
                        break;
                    case 6: dbList.get(j).setAllWhealsDrive(resultSet.getBoolean(i));
                        break;
                    case 7: dbList.get(j).setPassengersNumber(resultSet.getInt(i));
                        break;
                    default: break;
                }
            }
            j++;
        }
        resultSet.close();

//      dbList elements retrieved from DB table output
        Iterator<Automobile> it = dbList.iterator();
        System.out.println("List elements retrieved from DB:");
        while (it.hasNext()) {
            System.out.println(it.next().vehicleInfo());
        }
        return dbList;
    }

}
