package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserUtils {

    public static boolean isExist(String name) {
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;

        String sql = "select password from userinfo where name = ?";

        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if(!rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try{
                if(ps != null)
                ps.close();
                if(rs != null)
                rs.close();
                if(con != null)
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean identify(String name, String password){
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        String sql = "select password from userinfo where name = ?";

        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if(!rs.next()) {
                return false;
            }
            if(rs.getString(1).compareTo(password) != 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try{
                if(ps != null)
                    ps.close();
                if(rs != null)
                    rs.close();
                if(con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean register(String name, String password){
        PreparedStatement ps = null;
        Connection con = null;
        ResultSet rs = null;
        String sql = "insert into userinfo values(?,?)";

        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        } finally {
            try{
                if(ps != null)
                    ps.close();
                if(rs != null)
                    rs.close();
                if(con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
