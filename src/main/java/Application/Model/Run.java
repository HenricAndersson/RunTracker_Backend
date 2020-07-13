package Application.Model;

import com.google.gson.JsonObject;

import java.time.LocalDate;

//Model class that models a specific run
public class Run {

    private int id;
    private String userID;
    private LocalDate date;
    private double distance;
    private double time;

    public Run(){
        super();
    }



    public Run(String userID, int id, String date, double distance, double time) {
        this.userID=userID;
        this.id = id;
        this.date = LocalDate.parse(date);
        this.distance = distance;
        this.time = time;
    }

    public Run(String userID, String date, double distance, double time) {
        this.userID=userID;
        this.date = LocalDate.parse(date);
        this.distance = distance;
        this.time = time;
    }

    public Run(String date, int distance, int time) {
        this.date = LocalDate.parse(date);
        this.distance = distance;
        this.time = time;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public JsonObject toJson(){

        JsonObject jsonRun = new JsonObject();

        jsonRun.addProperty("id", this.id);
        jsonRun.addProperty("date", String.valueOf(this.date));
        jsonRun.addProperty("distance",this.distance);
        jsonRun.addProperty("time", this.time);

        return jsonRun;
    }
}