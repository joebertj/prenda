/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.helper;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {
	private static Connection conn=null;
	
	private DatabaseConnection(){
		
	}
	
	public static Connection getConnection(){
		if(conn==null){
			Properties props = new Properties();
			try {
				props.load(DatabaseConnection.class.getResourceAsStream("/application.properties"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String driver = props.getProperty("mainDataSource.driverClass");
			String url = props.getProperty("mainDataSource.jdbcUrl");
			String user = props.getProperty("mainDataSource.user");
			String password = props.getProperty("mainDataSource.password");
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url + "?useUnicode=true&characterEncoding=UTF-8&user=" + user + "&password=" + password);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
