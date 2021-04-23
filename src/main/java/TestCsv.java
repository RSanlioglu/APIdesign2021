import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import dataCSV.CoreCSV;
import dataCSV.converting.ConverterCSV;
import DataAccess.DataAccessCSV;
import dataXML.converting.ConverterXML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**This class is just for fast testing and will be deleted before the last submit **/
public class TestCsv {

    public static void main(String[] args) throws IOException {

        /*DataAccess csv = new DataAccess();

        ArrayList<Runner> runner = new ArrayList <>();
        runner.add(new Runner("01","Roberts", "Juarez",21, 'M',"Ethiopia",9.5f,
                10, 3, 0));

        csv.writeCollectionToDataFile(Collections.singletonList(runner),"runner.csv", Runner.class,
                false);*/

        //ConverterXML converterXML = new ConverterXML();
        // converterXML.convertToCSV("xml.xml", "");
        List<CoreCSV.Car> carList = new ArrayList<>();
        CoreCSV.Car car1 = new CoreCSV.Car(111,"Audi","A3",3000);
        CoreCSV.Car car2 = new CoreCSV.Car(23,"Volvo","20",4000);
        carList.add(car1);
        carList.add(car2);
        carList.add(new CoreCSV.Car(413,"Audi","A5",2002));

       // DataAccessXML dataAccessXML = new DataAccessXML("cars.xml", CoreCSV.Car.class, "Car");
        DataAccessCSV dataAccessCSV = new DataAccessCSV("car111.csv", CoreCSV.Car.class, false);
        dataAccessCSV.writeList(Collections.singletonList(carList));
        List<Object> objects = dataAccessCSV.getAllObjects();
        System.out.println(objects);
        System.out.println(carList);
        ConverterCSV.convertToJSON(dataAccessCSV,"car111.json", CoreCSV.Car.class);
        ConverterCSV.convertToXML(dataAccessCSV, "car111.xml", CoreCSV.Car.class, "Car");

        //dataAccessXML.writeList(Collections.singletonList(carList));
        //dataAccessXML.appendObject(new Car (213,"Audi","A3",2000));
       // dataAccessXML.appendList(Collections.singletonList(carList));
       // dataAccessXML.appendObject(car1);
        //dataAccessXML.appendObject(car2);
       //dataAccessXML.writeObject(car1);
       //dataAccessXML.appendObject(car2);
      //  dataAccessXML.updateObject(car1, car2);
        //dataAccessXML.deleteObject(car1);
       // dataAccessXML.doesExist(car1);
      //  System.out.println(  dataAccessXML.getAllObjects().toString());
    //    ConverterXML.convertToJSON("cars.xml","cars.json", CoreCSV.Car.class);
        ConverterXML.convertToCSV("cars.xml","cars.csv", CoreCSV.Car.class, false);
        //void updateObject(Object oldObject, Object newObject);
       // dataAccessXML.doesExist(tesla); //Check if the tesla is inside the file before deleting it
        //dataAccessXML.deleteObject(tesla);
       //Check if the tesla is inside the file after deleting it

    }
    public static void deserializeFromXML() {
        try {
            XmlMapper xmlMapper = new XmlMapper();

            // read file and put contents into the string
            String readContent = new String(Files.readAllBytes(Paths.get("cars.xml")));

            // deserialize from the XML into a Phone object
            CoreCSV.Car deserializedData = xmlMapper.readValue(readContent, CoreCSV.Car.class);

            // Print object details
            System.out.println("Deserialized data: ");
            System.out.println("\tgetRegistrationID: " + deserializedData.getRegistrationID());
            System.out.println("\tgetModel: " + deserializedData.getModel());
            System.out.println("\tgetProducer: " + deserializedData.getProducer());
        } catch (IOException e) {
            // handle the exception
        }
    }
}

