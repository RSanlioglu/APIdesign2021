package Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"registrationID", "producer", "model", "year", "cylinderVolume"})
public class Car {
    @JsonProperty("registrationID")
    int registrationID;
    @JsonProperty("producer")
    String producer;
    @JsonProperty("model")
    String model;
    @JsonProperty("year")
    int year;
    @JsonProperty("cylinderVolume")
    double cylinderVolume;

    public Car(int registrationID, String producer, String model, int year, double cylinderVolume) {
        this.registrationID = registrationID;
        this.producer = producer;
        this.model = model;
        this.year = year;
        this.cylinderVolume = cylinderVolume;
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
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getCylinderVolume() {
        return cylinderVolume;
    }

    public void setCylinderVolume(double cylinderVolume) {
        this.cylinderVolume = cylinderVolume;
    }

    @Override
    public String toString() {
        return "Model.Car{" +
                "registrationID=" + registrationID +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", cylinderVolume=" + cylinderVolume +
                '}';
    }
}

