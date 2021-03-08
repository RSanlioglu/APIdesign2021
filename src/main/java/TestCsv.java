import dataCSV.dataAcces.DataAccess;
import dataCSV.dataAcces.Runner;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class TestCsv {

    public static void main(String[] args) {
        DataAccess csv = new DataAccess();

        ArrayList<Runner> runner = new ArrayList <>();
        runner.add(new Runner("01","Roberts", "Juarez",21, 'M',"Ethiopia",9.5f,
                10, 3, 0));

        csv.writeCollectionToDataFile(Collections.singletonList(runner),"runner.csv", Runner.class,
                false);

    }
}

