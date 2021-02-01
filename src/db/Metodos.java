package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import db.Conexion;

public class Metodos {
	private Conexion cnx;
	
	
	public Metodos(Conexion cnx) {
		super();
		this.cnx = cnx;
	}

	public Conexion getCnx() {
		return cnx;
	}

	public void setCnx(Conexion cnx) {
		this.cnx = cnx;
	}

	private ArrayList<String> getTables() {
		cnx.obtener();
		ArrayList<String> tables = new ArrayList<String>();
		try {
            Statement stmt = cnx.getCnx().createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT TABLE_SCHEMA, TABLE_NAME FROM INFORMATION_SCHEMA.TABLES;");
            while (resultSet.next()) {
                tables.add(resultSet.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		
		return tables;
	}
	
	private int getColumns(String tableName) {
		int numCols = 0;
		try {
            Statement stmt = cnx.getCnx().createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT ORDINAL_POSITION FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = N'"+tableName+"'");
            while (resultSet.next()) {
                numCols+=1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		return numCols;
	}
	
	private String selectTables(String tableName,int numCols) {
		String res = "";
		try {
            Statement stmt = cnx.getCnx().createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM "+tableName+"");
            
            int row = 0;
            while (resultSet.next()) {
            	row +=1;
                for (int i = 1; i <= numCols; i++) {
                	String value = resultSet.getString(i);
                	if(value == null) {
                		value = "null";
                	}
                	switch (value.trim()) {
                	case "null":
						res += "null on row " +row+ " col " +i+"\n";
						break;
					case "":
						res += "empty on row " +row+ " col " +i+"\n";
						break;

					case "UNDECIDED":
						res += "UNDECIDED on row " +row+ " col " +i+"\n";
						break;
						
					default:
						break;
					}
				}
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		return res;
	}
	
	public String searchNulls() {
		String result = "User: "+cnx.getUser()+"\nDatabase: "+ cnx.getDatabase() +"\n"+new Date()+"\n";
		ArrayList<String> tablas = getTables();
		for (String object : tablas) {
			result += object+"\n";
			result += selectTables(object,getColumns(object))+"\n";
		}
		cnx.cerrar();
		return result;
	}
		
}
