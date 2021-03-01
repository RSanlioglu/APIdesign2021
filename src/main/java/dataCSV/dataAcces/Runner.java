package dataCSV.dataAcces;

import java.util.ArrayList;

public class Runner {
    private String runnerId;
    private String firstName;
    private String lastName;
    private int age;
    private char sex;
    private String country;
    private float speed;
    private int goldenMedals;
    private int olympicMedals;
    private int worldRecords;


    public Runner(String runnerId, String firtName, String lastName, int age, char sex, String country,float speed,int goldenMedals,
    int olympicMedals,int worldRecords) {
        this.runnerId = runnerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.country = country;
        this.speed = speed;
        this.goldenMedals = goldenMedals;
        this.olympicMedals = olympicMedals;
        this.worldRecords = worldRecords;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setGoldenMedals(int goldenMedals) {
        this.goldenMedals = goldenMedals;
    }

    public int getOlympicMedals() {
        return olympicMedals;
    }

    public void setOlympicMedals(int olympicMedals) {
        this.olympicMedals = olympicMedals;
    }

    public int getWorldRecords() {
        return worldRecords;
    }

    public void setWorldRecords(int worldRecords) {
        this.worldRecords = worldRecords;
    }

    public String getRunnerId() {
        return runnerId;
    }

    public void setRunnerId(String runnerId) {
        this.runnerId = runnerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGoldenMedals() {
        return goldenMedals;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
