import dataXML.Car;
import dataXML.dataAccess.DataAccessXML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

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
        DataAccessXML dataAccessXML = new DataAccessXML("cars.xml", Car.class);
       // dataAccessXML.createXML("cars.xml", new Car());

    }
}

