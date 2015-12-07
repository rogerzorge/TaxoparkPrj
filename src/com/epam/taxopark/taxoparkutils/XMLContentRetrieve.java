package com.epam.taxopark.taxoparkutils;

import com.epam.taxopark.vehicles.vehicletypes.Automobile;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.Minivan;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.PassengerCar;
import com.epam.taxopark.vehicles.vehicletypes.autotypes.Suv;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Yahor_Faliazhynski on 12/5/2015.
 */
public class XMLContentRetrieve {

    public List<Automobile> xmlContent() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(new File("D:\\TR\\Trash\\Test java project\\TaxoparkProject\\autopark.xml"));
        document.normalizeDocument();
        Element root = document.getDocumentElement();
        NodeList auto = root.getElementsByTagName("auto");
        List<Automobile> xmlList = new ArrayList<Automobile>();

        System.out.println("+++XML+++");
        for (int i = 0; i < auto.getLength(); i++) {

            Element item = (Element) auto.item(i);
            //System.out.println(item.getTagName());

            String id = item.getAttribute("id");
            String auto_type = item.getAttribute("auto_type");
            Element car_brand = (Element) item.getElementsByTagName("car_brand").item(0);
            Element car_cost = (Element) item.getElementsByTagName("car_cost").item(0);
            Element fuel_consumption = (Element) item.getElementsByTagName("fuel_consumption").item(0);
            Element all_wheel_drive = (Element) item.getElementsByTagName("all_wheel_drive").item(0);
            Element passengers_number = (Element) item.getElementsByTagName("passengers_number").item(0);

            System.out.println(String.format("XML element id(%s), car_type(%s) was parsed:", id, auto_type));
            System.out.println(String.format("%s : %s", car_brand.getTagName(), car_brand.getTextContent()));
            System.out.println(String.format("%s : %s", car_cost.getTagName(), car_cost.getTextContent()));
            System.out.println(String.format("%s : %s", fuel_consumption.getTagName(), fuel_consumption.getTextContent()));
            System.out.println(String.format("%s : %s", all_wheel_drive.getTagName(), all_wheel_drive.getTextContent()));
            System.out.println(String.format("%s : %s\n", passengers_number.getTagName(), passengers_number.getTextContent()));

            if (item.getAttribute("auto_type").equals("Passenger car")) xmlList.add(new PassengerCar(car_brand.getTextContent(), Integer.parseInt(car_cost.getTextContent()), Float.parseFloat(fuel_consumption.getTextContent()), Boolean.parseBoolean(all_wheel_drive.getTextContent()), Integer.parseInt(passengers_number.getTextContent())));
            else if (item.getAttribute("auto_type").equals("Minivan")) xmlList.add(new Minivan(car_brand.getTextContent(), Integer.parseInt(car_cost.getTextContent()), Float.parseFloat(fuel_consumption.getTextContent()), Boolean.parseBoolean(all_wheel_drive.getTextContent()), Integer.parseInt(passengers_number.getTextContent())));
            else if (item.getAttribute("auto_type").equals("Suv")) xmlList.add(new Suv(car_brand.getTextContent(), Integer.parseInt(car_cost.getTextContent()), Float.parseFloat(fuel_consumption.getTextContent()), Integer.parseInt(passengers_number.getTextContent())));
            else System.out.println("No such carType in object model!");
        }

        System.out.println("List elements retrieved from XML: ");
        Iterator<Automobile> it = xmlList.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().vehicleInfo());
        }
        return xmlList;
    }

}
