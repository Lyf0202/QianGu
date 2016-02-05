package com.qiangu.keyu.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Sqlite {

	String sqlitePath = "jdbc:sqlite:F:\\hometown.db";

	public void createSqlite() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(sqlitePath);
			c.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Opened database successfully");
	}

	/**
	 * 
	 * 
	 * @param sql
	 */
	public void createTable(String sql) {
		Connection c = null;
		Statement sta = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(sqlitePath);
			sta = c.createStatement();

			sta.executeUpdate(sql);
			sta.close();
			c.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Table created successfully");
	}

	public void insert(String sql) {
		Connection c = null;
		Statement sta = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(sqlitePath);
			c.setAutoCommit(false);
			sta = c.createStatement();
			sta.executeUpdate(sql);
			sta.close();
			c.commit();
			c.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("error");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error2");
		}

	}

	public void update(String sql) {
		Connection c = null;
		Statement sta = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(sqlitePath);
			c.setAutoCommit(false);
			sta = c.createStatement();
			sta.executeUpdate(sql);
			c.commit();
			sta.close();
			c.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("error");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error2");
		}
	}

	public List<Map<String, String>> select(String sql) {
		Connection c = null;
		Statement sta = null;
		ResultSet rs = null;
		List<Map<String, String>> list = new Vector<Map<String, String>>();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(sqlitePath);
			c.setAutoCommit(false);
			sta = c.createStatement();
			System.out.println("SQL:::::::  " + sql);
			rs = sta.executeQuery(sql);
			ResultSetMetaData metadata = rs.getMetaData();
			int num = metadata.getColumnCount();

			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < num; i++) {
					map.put(metadata.getColumnName(i + 1), rs.getString(i + 1));
					System.out.println(metadata.getColumnName(i + 1) + " : " + rs.getString(i + 1));
				}
				list.add(map);
			}
			sta.close();
			c.commit();
			c.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("error");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error2");
		}
		return list;
	}

	public Map<String, String> selectStartTime(String sql) {

		Connection c = null;
		Statement sta = null;
		ResultSet rs = null;
		int count = 0;
		Map<String, String> map = new HashMap<String, String>();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(sqlitePath);
			c.setAutoCommit(false);
			sta = c.createStatement();
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				map.put("id", String.valueOf(rs.getInt("id")));
				map.put("startTime", rs.getString("startTime"));
			}
			sta.close();
			c.commit();
			c.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("error");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error2");
		}
		return map;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sqlite sqlite = new Sqlite();
		// sqlite.createSqlite();
		// String createTablePro = "CREATE TABLE provinces(id INTEGER,province
		// TEXT,provinceId TEXT,PRIMARY KEY(id));";
		// String createTableCities = "CREATE TABLE cities(id INTEGER,cityId
		// TEXT,city TEXT,provinceId TEXT,PRIMARY KEY(id))";
		// String createTableAreas = "CREATE TABLE areas(id INTEGER,areaId
		// TEXT,area TEXT,cityId TEXT,PRIMARY KEY(id))";
		// sqlite.createTable(createTablePro);
		// sqlite.createTable(createTableCities);
		// sqlite.createTable(createTableAreas);
		String insertSql = "insert into provinces values (1,'110000','北京市')";
		sqlite.insert(insertSql);
	}

}
