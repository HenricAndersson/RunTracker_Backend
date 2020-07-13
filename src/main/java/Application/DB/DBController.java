package Application.DB;

import Application.Model.Run;
import Application.Model.RunCollection;
import Application.Utils.Strings;

import java.sql.*;
import java.util.ArrayList;

public class DBController {

    private static Connection con = null;


    public static void connect() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(
                "jdbc:mysql://" +
                        Strings.dbAdress +
                        ":" +
                        Strings.dbPort +
                        "/RunTracker?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                        , "root"
                        , "root");
    }

    public static void disconnect() throws SQLException {
        con.close();
    }

    // Makes sure the user ID of a logged in person exists
    public static void login(String userID) throws SQLException, ClassNotFoundException {

        if(!DBController.userExists(userID)){
            DBController.addUser(userID);
        }
    }

    //Checks whether a specific user ID exists in the database
    private static boolean userExists(String userID) throws SQLException, ClassNotFoundException {

        connect();

        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM T_User WHERE userID = ? LIMIT 1");
        pstmt.setString(1, userID);
        ResultSet rs = pstmt.executeQuery();
        boolean userExists = rs.next();

        disconnect();

        return userExists;
    }

    //Adds a user ID to the database
    public static void addUser(String userID) throws SQLException, ClassNotFoundException {

        connect();

        PreparedStatement pstmt = con.prepareStatement("INSERT INTO T_User VALUES (?)");
        pstmt.setString(1, userID);
        pstmt.executeUpdate();

        disconnect();
    }


    //Gets all the runs of a specific user ID
    public static RunCollection getRuns(String userID) throws SQLException, ClassNotFoundException {

        connect();

        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM T_Run where T_Run.userID = ?");
        pstmt.setString(1, userID);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Run> runs = new ArrayList<>();

        while(rs.next()){
            runs.add(new Run(
                rs.getString("userID"),
                rs.getInt("id"),
                rs.getDate("runDate").toLocalDate().toString(),
                rs.getDouble("runDistance"),
                rs.getDouble("runTime")
            ));
        }

        disconnect();

        return new RunCollection(userID, runs);
    }
    S
    //Posts a run to the database
    public static void postRun(Run run) throws SQLException, ClassNotFoundException {

        String userID = run.getUserID();

        if(!DBController.userExists(userID)){
            DBController.addUser(userID);
        }
        connect();

        PreparedStatement pstmt = con.prepareStatement("INSERT INTO T_Run (userID, runDate, runDistance, runTime) VALUES (?, ?, ?, ?)");
        pstmt.setString(1, userID);
        pstmt.setDate(2, java.sql.Date.valueOf(run.getDate()));
        pstmt.setDouble(3, run.getDistance());
        pstmt.setDouble(4, run.getTime());

        System.out.println(pstmt.toString());

        pstmt.executeUpdate();

        disconnect();
    }

}
