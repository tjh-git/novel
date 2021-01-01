package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
//import org.apache.commons.beanutils.BeanUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

public class DBUtils {
    public static Connection getConnection() throws SQLException {
        DataSource ds = null;
        try {
            Properties pro = new Properties();
            InputStream in = DBUtils.class.getClassLoader().getResourceAsStream("config/db.properties");
            pro.load(in);
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds.getConnection();
    }
    public static <T> List<T> getList(Class<T> clazz,String sql,Object...args){
            List<T> list = new ArrayList<T>();
            Connection con = null;
            PreparedStatement pdsm = null;
            ResultSet rs = null;
        try {
            con = getConnection();
            pdsm= con.prepareStatement(sql);
            for(int i = 0; i<args.length;i++) {
                pdsm.setObject(i + 1, args[i]);
            }
            rs = pdsm.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int size = rsmd.getColumnCount();
            while(rs.next()) {
                Map<String,Object> map = new HashMap<String,Object>();
                for (int i = 1; i <= size; i++) {
                    String title = rsmd.getColumnLabel(i);
                    Object value = rs.getObject(title);
                    map.put(title, value);
                }
                T temp = clazz.newInstance();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object values = entry.getValue();
                    String methodname = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
                    Method method = clazz.getMethod(methodname, values.getClass());
                    System.out.println(values);
                    method.invoke(temp,values);
//                    BeanUtils.setProperty(temp,key,values);
                }
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pdsm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public static boolean save(String sql,Object... argx) throws SQLException {
        Connection con = getConnection();
        PreparedStatement pdst = con.prepareStatement(sql);
        for(int i = 0; i < argx.length; i++){
            pdst.setObject(i+1,argx[i]);
        }
        int result = pdst.executeUpdate();
        con.close();
        pdst.close();
        return result>0;
    }
}
