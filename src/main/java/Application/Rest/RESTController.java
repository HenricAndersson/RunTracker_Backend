package Application.Rest;

import Application.DB.DBController;
import Application.Model.Run;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@RestController
public class RESTController {



    //REST GET-method for getting all the runs of a specific user
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getRuns")
    public String getRuns(@RequestParam(value = "userID") String userID){

        String jsonRuns;
        try{
            jsonRuns = DBController.getRuns(userID).toJson().toString();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return e.getCause().toString();
        }

        return jsonRuns;
    }

    //REST POST-method for posting a new run
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value ="/postRun", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postRun(@RequestBody Run run){


        try{
            DBController.postRun(run);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return e.getCause().toString();
        }
            finally{
            return "SUCCESSFUL";
        }

    }

    //REST POST-method for making sure the logged in user's user ID exists in the database
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public String login(@RequestBody String userID
    )  {

        try{
            DBController.login(userID);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return e.getCause().toString();
        }
        finally{
            System.out.println("Successful login");
            return "SUCCESSFUL";
        }

    }



}
