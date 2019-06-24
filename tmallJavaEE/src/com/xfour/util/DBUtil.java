package com.xfour.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 主要就是初始化数据库连接，并提供一个getConnection()方法获取连接
 * @author square
 *
 */
public class DBUtil {
	
	private static String ip ="127.0.0.1";
	private static int port = 3306;
	private static String database = "tmiao";
	private static String encoding = "UTF-8";
	private static String userName = "root";
	private static String password = "admin";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s&serverTimezone=UTC",ip,port,database,encoding);
		return DriverManager.getConnection(url,userName,password);
	}

}
