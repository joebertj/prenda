/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.prenda.helper.DatabaseConnection;

public class LevelService {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private int id;
	private String description;
	
	public LevelService(){
		conn = DatabaseConnection.getConnection();
	}

	public String getDescription() {
		description = getDescription(id);
		return description;
	}
	
	public String getDescription(int id) {
		String description = "";
		try {
			pstmt = conn.prepareStatement("select description from level where id=?");
			pstmt.setInt(1,id);
			ResultSet rs=pstmt.executeQuery();
			if(rs.first()){
				description = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		id = getId(description);
		return id;
	}
	
	public int getId(String description) {
		int id = 0;
		try {
			pstmt = conn.prepareStatement("select id from level where description=?");
			pstmt.setString(1,description);
			ResultSet rs=pstmt.executeQuery();
			if(rs.first()){
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
