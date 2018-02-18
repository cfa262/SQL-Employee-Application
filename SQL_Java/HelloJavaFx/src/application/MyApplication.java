package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class MyApplication  extends Application {
    
	
    @Override
    public void start(Stage primaryStage) {
        try {
            // Read file fxml and draw interface.
            Parent root = FXMLLoader.load(getClass()
                    .getResource("/MyScene.fxml"));
 
            primaryStage.setTitle("My Application");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
         
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws SQLException, IOException{
    	
    	String manssn1 = "333445555";
    	String manssn2 = "987654321";
    	String manssn3 = "888665555";
		Scanner scan = new Scanner(System.in);
    	System.out.println("Enter MANAGER SSN");
		String id = scan.next();
		System.out.println(id);
		if(id.equals(manssn1) || id.equals(manssn2) || id.equals(manssn3)) {
			System.out.println("WELCOME!\n");
			launch(args);
			
		}else {
			System.out.println("WRONG MANAGER SSN\n");
			System.exit(0);
		}
       
    }
    
}
