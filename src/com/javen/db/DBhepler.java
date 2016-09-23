package com.javen.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBhepler {
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://127.0.0.1:3306/jeesite";
	
	Connection con = null;
	ResultSet res = null;

	public void DataBase() {
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, "root", "123456");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public ResultSet  Search(String sql, String str[]) {
		DataBase();
		try {
			PreparedStatement pst =con.prepareStatement(sql);
			if (str != null) {
				for (int i = 0; i < str.length; i++) {
					pst.setString(i + 1, str[i]);
				}
			}
			res = pst.executeQuery();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	// ��ɾ�޸�
	public int AddU(String sql, String str[]) {
		int a = 0;
		DataBase();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			if (str != null) {
				for (int i = 0; i < str.length; i++) {
					pst.setString(i + 1, str[i]);
				}
			}
			a = pst.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

}
