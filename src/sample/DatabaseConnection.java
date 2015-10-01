package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by srinath on 9/25/2015.
 */
public class DatabaseConnection {

    public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
    }

    public static void saveDetials(CarObject car){

        try
        {
            // loads com.mysql.jdbc.Driver into memory
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException cnf)
        {
            System.out.println("Driver could not be loaded: " + cnf);
        }

        Connection conn = null;
        try {
            // System.out.println("try to connect");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_cars","root", "srinath");
            Statement stmt = conn.createStatement();
            //  System.out.println("sjkdfbsfbsjfdh       sadjfbhs fshd sahf jsdhf gsjfd jsd js fjgsd f");
            String sql = "INSERT INTO `cardetails` (carType,carName,minPrice,maxPrice,entryTime,existTime,batteryMin,batteryMax,batteryLeveltoAdd) "
                    + "VALUES ('"+car.getType()+"','"+car.getCarName()+"',"+car.getMinPrice()+","+car.getMaxPrice()+",'" + car.getEntryTime()+"','"
                    +car.getEstimatedOutTime()+"',"+car.getMinBattery()+","+car.getMaxBattery()+","+car.getBattery()+")";

            try
            {
                // ResultSet rs = stmt.executeQuery(sql);
                stmt.executeUpdate(sql);
            }
            catch (SQLException sqle)
            {
                System.out.println("SQL Exception thrown: " + sqle);
            }

        } catch (SQLException re) {
          //  System.out.println("failed to connect");
            re.printStackTrace();
        }


    }

}
