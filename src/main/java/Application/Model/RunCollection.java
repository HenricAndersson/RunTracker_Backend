package Application.Model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

//Model class that models a user and all their runs
public class RunCollection {


    private final String userID;
    private ArrayList<Run> runs;

    public RunCollection(String userID) {
        this.userID = userID;
        this.runs = new ArrayList<>();
    }

    public RunCollection(String userID, ArrayList<Run> runs) {
        this.userID = userID;
        this.runs = runs;
    }

    public String getUserID() {
        return userID;
    }

    public ArrayList<Run> getRuns() {
        return runs;
    }

    public void setRuns(ArrayList<Run> runs) {
        this.runs = runs;
    }

    public JsonObject toJson(){
        JsonObject jsonRunCollection = new JsonObject();

        jsonRunCollection.addProperty("userID", this.userID);

        JsonArray jsonRuns = new JsonArray();
        for(Run run: this.runs){
            jsonRuns.add(run.toJson());
        }

        jsonRunCollection.add("runs", jsonRuns);

        return jsonRunCollection;

    }

}
