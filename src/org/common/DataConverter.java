package org.common;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author dalin
 *
 */
public class DataConverter {
	/**
	 * @param date
	 * @return
	 */
	public static String dataToString(Date date){
		return dataToString(date,"yyyy-MM-dd HH:mm:ss");
		
	}
	public static String dataToString(Date date,String formatType){
		if(date==null){
			date=Utilty.getNowDateTime();
		}
		SimpleDateFormat formatter=new SimpleDateFormat(formatType);
		return formatter.format(date);
	}
	
	/**
	 * 
	 * @param input
	 */
	public static Date toDate(String input){
		return toDate(input,"yyyy-MM-dd HH:mm:ss");
	}
	public static Date toDate(String input,String formatType){
		SimpleDateFormat format=new SimpleDateFormat(formatType);
		Date dt=new Date();
		if(DataValidator.isNullOrEmpty(input)){
			return dt;
		}
		try{
			dt=format.parse(input);
		}catch(Exception e){
			
		}
		return dt;
	}
	/**
	 * 
	 * @param input
	 * @return
	 */
	public static Date toShortDate(String input){
		return toDate(input,"yyyy-MM-dd");
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public static Date toFullDate(String input) {
		// TODO Auto-generated method stub
		return toDate(input,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 *
	 * @param input
	 * @return
	 */
	public static int toInt(String input){
		try{
		return Integer.parseInt(input);
		}catch(Exception e){
			return 0;
		}
	}

	/**
	 * 数据库text字段在jsp中显示正常的空格、换行、回车
	 * @param str
	 * @return
	 */
	public static String toText(String str) {
		// 下面的代码将字符串以正确方式显示（包括回车，换行，空格）
		if (str != null && !"".equals(str)) {
			while (str.indexOf("\n") != -1) {
				str = str.substring(0, str.indexOf("\n")) + "<br>"
						+ str.substring(str.indexOf("\n") + 1);
			}
			while (str.indexOf(" ") != -1) {
				str = str.substring(0, str.indexOf(" ")) + "&nbsp;"
						+ str.substring(str.indexOf(" ") + 1);
			}
		}
		System.out.println(str);
		return str;

	}
}
