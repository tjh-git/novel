package org.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author dalin
 *
 */
public class Conn {
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;

	private static String url ;
	private static String rootname;
	private static String rootpass;
	private static String driverName;
	/**
	 * ������
	 */
	public Conn() throws IOException {
		//读取配置文件
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/db.properties");
		//System.out.println(in);
		Properties properties = new Properties();
		properties.load(in);

		url = properties.getProperty("url");
		rootname = properties.getProperty("username");
		rootpass = properties.getProperty("password");
		driverName = properties.getProperty("driverName");
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param sql
	 * @return 
	 */
	public ResultSet executeQuery(String sql){
		try{
		conn=DriverManager.getConnection(url,rootname,rootpass);
		stmt=conn.createStatement();
		rs=stmt.executeQuery(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * @param sql
	 * @return 
	 */
	public int executeUpdate(String sql){
		int result=0;
		try {
			conn=DriverManager.getConnection(url,rootname,rootpass);
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			result=stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result=0;
		}
		return result;	
	}
	
	public void close(){
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(stmt!=null)
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
