import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "registrationID", "producer", "model", "Year",})
public class Car {
    int registrationID;
    String producer;
    String model;
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
        return "Car{" +
                "registrationID=" + registrationID +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", Year=" + Year +
                '}';
    }
}
