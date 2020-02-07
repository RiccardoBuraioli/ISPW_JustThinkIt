package controllers;

import java.io.IOException;

import Dao.Login_Dao;
import Dao.VolunteerRepository;
import connector.Connector;
import entity.Login;
import entity.VolunteerUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login_Controller {
	
   
	
	@FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;
    
   
    @FXML
    void loginPressed(ActionEvent event) {
    	String Email = usernameField.getText();
    	String Passw = passwordField.getText();
    	
    	//Connettore non deve essere qui
    	
    	Connector connector = new Connector("jdbc:mysql://127.0.0.1:3306/JustthinkIt", "Login", "password");
    	Login_Dao login = new Login_Dao();
    	boolean loginResult = login.checkEmail(Email, Passw);
    	String Codice = Login.tableUser;
    	System.out.println(Codice);
    	
    	Codice = Login.getTableUser();
    	System.out.println(Codice);
    	//String Getcodice = loginE.getTableUser();
    	if (loginResult= true) {
    		
    		//OK MANDA ALLA HOME CORRETTA
    		System.out.println("Login succesfull");
    		System.out.println(Login.getTableUser());
    		
    		//Volontario
    		if (Codice.contentEquals("Volontario")) {
    			
    			Connector connectorV = new Connector("jdbc:mysql://127.0.0.1:3306/JustthinkIt", "Volontario", "password");
    			VolunteerRepository vrep = new VolunteerRepository(connectorV);
    			
    			int userID = login.returnID(Email,Codice);
    			if (userID == -1) {
    				System.out.println("Errore nel ritornare l'ID");
    			}
    			
    			VolunteerUser loggedUser = vrep.getVolunteerByID(login.returnID(Email,Codice));
    			System.out.println(loggedUser.getCognome());
    			
    			//Manda alla home user
    			try {
        			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/UserHomePage.fxml"));
        			Parent root = loader.load();
        			User_Home_Controller userHomeController = loader.getController();
        			userHomeController.initData(loggedUser);
        			Stage home = (Stage) loginButton.getScene().getWindow();
        			home.setScene(new Scene(root, 800, 600));
        			
        			home.show();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
    		}
    		
    		//Caritas
    		else if (Codice.contentEquals("Caritas")) {
    			
    		}
    		
    		//Negozio
    		else if (Codice.contentEquals("Negozio")) {
    			
    		}
    		    		
    	}
    	else {
    		System.out.println("Login Error");
    	}	

    }

    @FXML
    void registrazionePressed(ActionEvent event) {
    	
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/RegistrazioneMenu.fxml"));
			Stage signUp = (Stage) loginButton.getScene().getWindow();
			Scene scene = new Scene(root,600,400);
			signUp.setScene(scene);
			signUp.show();
			signUp.setResizable(false);
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

}
