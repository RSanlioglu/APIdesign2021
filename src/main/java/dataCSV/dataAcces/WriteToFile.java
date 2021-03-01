package dataCSV.dataAcces;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToFile {
    private String path;

    private String cmd;
    public WriteToFile(String path,String cmd){
        this.path = path;
        this.cmd = cmd;
        checkCmd();
    }
    // checking commando
    private void checkCmd(){
        switch(cmd){

            case("Runner"):

                writeRunner();
                break;
            default:
                System.out.println("ERROR: Wrong commando");
        }
    }


    private void writeRunner(){
        try {
            //creating bufferstream for this file
            BufferedWriter bWriter = new BufferedWriter(fWriter);

            for(Runner r: DataAccess.getRunnerListe()){

                String runnerId = r.getRunnerId();
                String fName = r.getFirstName();
                String sName = r.getLastName();
                Integer age = r.getAge();
                Character sex = r.getSex();
                String country = r.getCountry();
                Float speed = r.getSpeed();
                Integer goldenMedals = r.getGoldenMedals();
                Integer olympicMedals = r.getOlympicMedals();
                Integer worldRecords = r.getWorldRecords();

                String sep = ",";
                //writing new line to the file
                bWriter.write(runnerId + sep +fName +sep+sName+sep+age+sep+sex+sep+country+ sep+
                        speed+sep+goldenMedals+sep+olympicMedals+sep+worldRecords+"\n");


            bWriter.close();
            fWriter.close();
        }
    } catch (IOException e) {
        System.out.println(e);
    }

    }
}
