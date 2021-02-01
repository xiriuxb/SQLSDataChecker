package db;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
public class Conexion {
	private Connection cnx;
	private String serverName,port, user, password, database = "";
	public Conexion(String serverName, String port, String user, String password, String database) {
		super();
		this.serverName = serverName;
		this.port = port;
		this.user = user;
		this.password = password;
		this.database = database;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public Conexion(){
        
    }
    public Connection getCnx() {
        return cnx;
    }

    public void setCnx(Connection cnx) {
        this.cnx = cnx;
    }
    
	public Connection obtener (){
        if(cnx==null){
            try {
            	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                cnx = DriverManager.getConnection("jdbc:sqlserver://"+ serverName +":"+port+";"
                        + "database="+ database+";"
                        + "user="+user+";"
                        + "password="+password+";"
                        + "encrypt=false;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=10;");
                System.out.println("Conn OK");
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error en datos.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        }
        return cnx;
    }
    
    public void cerrar(){
        if(cnx!=null){
            try {
                cnx.close();
                System.out.println("Conn cerrada");
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
