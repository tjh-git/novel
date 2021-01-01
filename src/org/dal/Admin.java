package org.dal;

//import org.common.Conn;
import org.common.Conn;
import org.model.AdminInfo;
import utils.DBUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
	Conn conn = new Conn();

	public Admin() throws IOException, SQLException {
	}

	/**
	 * 判断登陆用户是否存在
	 * @param adminName
	 * @param adminPassword
	 * @return
	 * @throws SQLException
	 */
	public boolean isExist(String adminName,String adminPassword)throws SQLException{
		boolean result=false;

		String sql="select * from admin where adminName='"+adminName+"'and adminPassword='"+adminPassword+"'";
		System.out.println(sql);
		ResultSet rs=conn.executeQuery(sql);
		if(rs.next()){
			result = true;
			System.out.println("存在");
		}
		conn.close();
//		System.out.println(result);
		return result;
	}
}
