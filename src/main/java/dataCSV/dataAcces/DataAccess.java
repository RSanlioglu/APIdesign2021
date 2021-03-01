package dataCSV.dataAcces;

import java.util.ArrayList;

public class DataAccess {

    private static ArrayList<Runner> runnerListe = new ArrayList<>();


    public static ArrayList<Runner> getRunnerListe() {
        return runnerListe;
    }

    public static void setRunnerListe(ArrayList<Runner> runnerListe) {
        DataAccess.runnerListe = runnerListe;
    }
}
