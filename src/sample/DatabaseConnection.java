package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by srinath on 9/25/2015.
 */
public class DatabaseConnection {


    public static void saveDetials(CarObject car){

        dbConn();
        Connection conn = null;
        try {
            // System.out.println("try to connect");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_car","root", "");
            Statement stmt = conn.createStatement();
            //  System.out.println("sjkdfbsfbsjfdh       sadjfbhs fshd sahf jsdhf gsjfd jsd js fjgsd f");
            String sql = "INSERT INTO `cardetails` (carType,carName,minPrice,maxPrice,entryTime,stayTime,batteryIntial,batteryCriticalMin) "
                    + "VALUES ('"+car.getType()+"','"+car.getCarName()+"',"+car.getBuyPrice()+","+car.getSellingPrice()+",'" + car.getPlugInTime()+"','"
                    +car.getExistTime()+"',"+car.getInitialBattery()+","+car.getCriticalMinBattery()+")";

            try
            {
                // ResultSet rs = stmt.executeQuery(sql);
                stmt.executeUpdate(sql);

                System.out.println("details saved");
            }
            catch (SQLException sqle)
            {
                System.out.println("SQL Exception thrown: " + sqle);
            }

        } catch (SQLException re) {
            System.out.println("failed to connect");
          //  re.printStackTrace();
        }


    }

    public static void updateDeatils(CarObject car, int n){
        dbConn();
        Connection conn = null;
        try {
            // System.out.println("try to connect");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_car","root", "");
            Statement stmt = conn.createStatement();
            //  System.out.println("sjkdfbsfbsjfdh       sadjfbhs fshd sahf jsdhf gsjfd jsd js fjgsd f");
            String sql = "UPDATE  `cardetails` SET carType = '"+car.getType()+"',carName ='"+car.getCarName()+"',minPrice = '"+car.getBuyPrice()+"',maxPrice = '"
                    +car.getSellingPrice()+"',entryTime = '"+ car.getPlugInTime() +"',stayTime = '"  +car.getExistTime()+"',batteryIntial = '"+car.getInitialBattery()+
                    "',batteryCriticalMin ='"+car.getCriticalMinBattery()+"'  WHERE carId ="+n;

            System.out.println(sql);



            try
            {
                // ResultSet rs = stmt.executeQuery(sql);
                System.out.println("details saved");
                stmt.executeUpdate(sql);

            }
            catch (SQLException sqle)
            {
                System.out.println("SQL Exception thrown: " + sqle);
            }

        } catch (SQLException re) {
            System.out.println("failed to connect");
            //  re.printStackTrace();
        }
    }



    public static void dbConn(){
        try
        {
            // loads com.mysql.jdbc.Driver into memory
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException cnf)
        {
            System.out.println("Driver could not be loaded: " + cnf);
        }

    }

}
