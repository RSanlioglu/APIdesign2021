package dataCSV;

import DataAccess.DataAccessCSV;

import java.io.IOException;

public class CoreCSV {
    public static class Car {
        int registrationID;
        String producer, model;
        int Year;

        public Car(int registrationID, String producer, String model, int year) {
            this.registrationID = registrationID;
            this.producer = producer;
            this.model = model;
            Year = year;
        }

        //Tom konstruktør for å lese
        public Car() {

        }

        public int getRegistrationID() {
            return registrationID;
        }

        public void setRegistrationID(int registrationID) {
            this.registrationID = registrationID;
        }

        public String getProducer() {
            return producer;
        }

        public void setProducer(String producer) {
            this.producer = producer;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public int getYear() {
            return Year;
        }

        public void setYear(int year) {
            Year = year;
        }

        @Override
        public String toString() {
            return "Car{" + "registrationID=" + registrationID + ", producer='" + producer + "'" + ", model='" + model + "'" + ", Year=" + Year + '}';
        }
    }

    public static void main(String[] args) throws IOException {
        DataAccessCSV csv = new DataAccessCSV("car.csv",Car.class, false);
        /*List<Car> cars = new ArrayList<>();
        cars.add(new Car(4, "audi","a3",200));
        cars.add(new Car(3,"bmw","x5",2021));
        cars.add(new Car(1,"mercedes","cclass",1995));

        csv.writeList(Colections.singletonList(cars));*/
        //Converting.convertToJSON(csv, "car.csv","cars.json",Car.class,false);
    }
}
