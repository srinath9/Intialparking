package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.Timestamp;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by srinath on 9/26/2015.
 */
public class ShowDetails {

    static boolean answer;
    private static int i=0;
    static String result;
    static CarObject resultCarDetails;
    private static TableView<CarObject> table;


    public static void display(/*String title, String message, CarObject car1*/){



        final Stage window = new Stage();
        /*Button closeButton = new Button("close the widow");*/

        window.initModality(Modality.APPLICATION_MODAL);
     //  window.setTitle(title);
        window.setMinWidth(500);
        window.setMinHeight(500);

        ComboBox<String> comboBox;
        TextField carname = new TextField();
        TextField batterylife = new TextField();
        TextField addBattery = new TextField();
        int randnum = RandomClass.integerValue(0);

        carname.setText(RandomClass.stringValue());
        batterylife.setText(String.valueOf(randnum));
        addBattery.setText(String.valueOf(RandomClass.integerValue(randnum)));



        Label carlabel = new Label();
        Label batterylabel = new Label();
        Label addBatteryLabel = new Label();
        Label typelabel = new Label();
        carlabel.setText("give the name");
        batterylabel.setText("integer battery level");
        addBatteryLabel.setText("required ammount of battery");



        /*batterylife.setText();*/

        Boolean active;
        active = false;

        /*comboBox = new ComboBox<String>();
        comboBox.getItems().addAll(
                "Distributor",
                "Buyer"
        );*/

        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton();
        rb1.setText("Seller");
        RadioButton rb2 = new RadioButton("Buyer");
        RadioButton rb3 = new RadioButton("Both");
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);

        /*comboBox.setPromptText("What is your Type you want?");*/

       /* comboBox.setOnAction(e -> System.out.print(comboBox.getValue()));*/
        Button yes = new Button("Go to add price preference");
        Button close = new Button("Close");
        close.setOnAction(e-> window.close());
        /*Button no = new Button("NO");*/

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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_car", "root", "");
        } catch (SQLException e) {
          //  e.printStackTrace();
            System.out.println(" error while connecting databse");
        }

        String query = "SELECT * FROM cardetails";

        // create the java statement
        Statement st = null;
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // execute the query, and get a java resultset
        try {
            ResultSet rs = st.executeQuery(query);
            System.out.println(rs);
            CarObject[] savesList = new CarObject[100];
            int i =0;
            while (rs.next())
            {
                int id = rs.getInt("carId");
                String carType = rs.getString("carType");
                String carName = rs.getString("carName");
                Timestamp entryTime = rs.getTimestamp("entryTime");
                Timestamp existTime = rs.getTimestamp("existTime");

              //  System.out.println(rs.getTimestamp("entryTime"));
                Float minPrice = rs.getFloat("minPrice");
                Float maxPrice = rs.getFloat("maxPrice");
                Float intialBattery = rs.getFloat("minPrice");
                Float criticalBattery = rs.getFloat("criticalBattery");
                Float batteryAdd = rs.getFloat("batteryLeveltoAdd");
                Float chargeRate = rs.getFloat(("chargeRate"));
                Float disChargeRate = rs.getFloat(("disChargeRate"));

        //        System.out.format("%s, %s, %s %s %s %s, %s %s\n", id, carType, carName, minPrice, criticalBattery, batteryAdd,existTime , entryTime);
                savesList[i] = new CarObject(carName,carType,minPrice,maxPrice,entryTime,existTime,intialBattery,criticalBattery,"slow",chargeRate,disChargeRate);
                i=i+1;

            }
          //  System.out.println("carlist lenth : "+i);

       //     table = RandomGeneration.tableDisplay(savesList);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();



        Label label1 = new Label();
      //  label1.setText(message);
        VBox layout = new VBox(10);
        HBox hb = new HBox(10);
        HBox hb1 = new HBox(10);
        HBox hb2 = new HBox(10);
        HBox hb3 = new HBox(10);

        VBox vb1 = new VBox(10);
        VBox vb2 = new VBox(10);
        VBox vb3 = new VBox(10);

        vb1.getChildren().addAll(carlabel,batterylabel,addBatteryLabel);
        vb1.setSpacing(20);
        vb2.getChildren().addAll(carname,batterylife,addBattery);

        hb.getChildren().addAll(rb1,rb2,rb3);
        hb1.getChildren().addAll(vb1,vb2);
/*        hb2.getChildren().addAll(batterylabel,batterylife);
        hb3.getChildren().addAll(addBatteryLabel,addBattery);*/
        hb.setAlignment(Pos.CENTER);
        //    hb2.setAlignment(Pos.CENTER);
        hb1.setAlignment(Pos.CENTER);
        //      hb3.setAlignment(Pos.CENTER);
        carname.setMaxWidth(100);
        batterylife.setMaxWidth(100);
        addBattery.setMaxWidth(100);
        //layout.getChildren().addAll(label1, hb1, typelabel, hb,yes,close);
        layout.setAlignment(Pos.CENTER);

     //   Scene scene = new Scene(layout);
     //   window.setTitle("new title");
     //   window.setScene(scene);
    //    window.showAndWait();

        /*return car;*/
        /*return resultCarDetails;*/

    }

    public static <T> String val(T message){
        return ""+message;
    }


    public static void userCarDetails(){
        Stage window = new Stage();
        System.out.println(" indeside car details");

        Label carIndexLabel = new Label("give the index number of the car ");
        TextField carIndex = new TextField("28");
        carIndex.setMaxWidth(100);
        Button search = new Button("Search index");


        search.setOnAction(e -> {
            searchIndex(Integer.parseInt(carIndex.getText()));

            window.close();
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(carIndexLabel,carIndex,search);
        vbox.setMinHeight(200);
        vbox.setMinWidth(300);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox);

        window.setScene(scene);
        window.show();


    }

    public static void searchIndex(int n){
        DatabaseConnection.dbConn();
        System.out.println(" indeside searhc");

        String sql = "SELECT * FROM cardetails WHERE carId LIKE '"+n+"'";

        try {
            // System.out.println("try to connect");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_car", "root", "");
            Statement stmt = conn.createStatement();

            try
            {
                ResultSet rs = stmt.executeQuery(sql);
     /*           stmt.executeUpdate(sql);*/
                System.out.println(rs);
                CarObject[] savesList = new CarObject[100];
                int i =0;
                int id = 0;
                while (rs.next())
                {
                    id = rs.getInt("carId");
                    String carType = rs.getString("carType");
                    String carName = rs.getString("carName");
                    Timestamp entryTime = rs.getTimestamp("entryTime");
                    Timestamp stayTime = rs.getTimestamp("stayTime");
                    Float minPrice = rs.getFloat("minPrice");
                    Float maxPrice = rs.getFloat("maxPrice");
                    Float initialBattery = rs.getFloat("batteryIntial");
                    Float criticalBattery = rs.getFloat("batteryCriticalMin");
                    Float chargeRate = rs.getFloat(("chargeRate"));
                    Float disChargeRate = rs.getFloat(("disChargeRate"));
                    
                    savesList[i] = new CarObject(carName,carType,minPrice,maxPrice,entryTime,stayTime,initialBattery,criticalBattery,"slow",chargeRate,disChargeRate);
                    i=i+1;

                }

                if( i==1) UserInfo.savedDeatials(savesList[0],"Update",id);
                else UserInfo.noResult();

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



}
