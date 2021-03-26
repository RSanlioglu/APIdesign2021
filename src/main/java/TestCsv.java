import dataXML.Car;
import dataXML.dataAccess.DataAccessXML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestCsv {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException {

        /*DataAccess csv = new DataAccess();

        ArrayList<Runner> runner = new ArrayList <>();
        runner.add(new Runner("01","Roberts", "Juarez",21, 'M',"Ethiopia",9.5f,
                10, 3, 0));

        csv.writeCollectionToDataFile(Collections.singletonList(runner),"runner.csv", Runner.class,
                false);*/

        //ConverterXML converterXML = new ConverterXML();
       // converterXML.convertToCSV("xml.xml", "");
        List<Car> carList = new ArrayList<>();
        carList.add(new Car (213,"Audi","A3",2000));
        carList.add(new Car (313,"Audi","A4",2001));
        carList.add(new Car (413,"Audi","A5",2002));
        DataAccessXML dataAccessXML = new DataAccessXML("cars.xml", Car.class);
      // dataAccessXML.writeObject(new Car (213,"Audi","A3",2000));
       //dataAccessXML.writeList(Collections.singletonList(carList), "Cars");
      dataAccessXML.appendObject(new Car (413,"Audi","A5",2002));
    }
}

