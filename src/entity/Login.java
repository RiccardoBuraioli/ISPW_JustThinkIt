package entity;

import java.sql.*;

import Dao.Login_Dao;
import connector.Connector;

public class Login {

    private Connector connector;
    public static String tableUser;


    public Login(Connector connector) {
        this.connector = connector;
    }
    
    public static String getTableUser() {
    	System.out.println(tableUser);
        return tableUser;
    }
 
    public  void setTableUser(String codice) {
        this.tableUser = codice;
        System.out.println(tableUser);
    }

    public Login() {
    	this.tableUser = tableUser;
    }



  
    
    //Probabilmente sbagliata
    //Serve a ritornare l'ID della mail corrispondente (nel sistema non ci possono essere due mail uguali)
  
}


