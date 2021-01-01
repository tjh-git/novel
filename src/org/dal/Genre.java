package org.dal;

import org.common.Conn;
import org.model.GenreInfo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Genre {
	Conn conn=new Conn();

	public Genre() throws IOException {
	}

	/**
	 * 获取分类列表
	 * @return
	 * @throws SQLException
	 */
	public List<GenreInfo> getList()throws SQLException{
		List<GenreInfo> list=new ArrayList<GenreInfo>();
		String sql="select * from genre order by Sort asc";
		ResultSet rs=conn.executeQuery(sql);
		while(rs.next()){
			GenreInfo info=new GenreInfo();
			info.setId(rs.getInt("id"));
			info.setName(rs.getString("name"));
			info.setSort(rs.getInt("sort"));
			list.add(info);
	}
		conn.close();
		return list;
	}
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public GenreInfo getGenreInfo(int id)throws SQLException{
		GenreInfo info=new GenreInfo();
		String sql="select * from genre g where id="+id+"";
		ResultSet rs=conn.executeQuery(sql);
		while(rs.next()){
			info.setId(rs.getInt("Id"));
			info.setName(rs.getString("Name"));
			info.setSort(rs.getInt("Sort"));
	}
		conn.close();
		return info;
	}
	/**
	 * 增加分类
	 * @param info
	 * @return
	 */
	public int insert(GenreInfo info){
		String sql="insert into genre(name,sort) values";
		sql=sql+"('"+info.getName()+"','"+info.getSort()+"')";
		int result=0;
		result=conn.executeUpdate(sql);
		conn.close();
		return result;
	}
	/**
	 * 更新分类
	 * 
	 * @param info
	 * @return
	 */
	public int update(GenreInfo info){
		String sql="update genre set "+" Name='"+info.getName()+"',Sort= '"+info.getSort()+"' where id="+info.getId()+"";
		int result=0;
		result=conn.executeUpdate(sql);
		return result;
		
	}
	public int delete(int id){
		String sql="delete from genre where id="+id+"";
		int result=0;
		result=conn.executeUpdate(sql);
		conn.close();
		return result;
	}

	/**
	 * 获得genre总数
	 * @param info
	 *
	 */
	public  int getTotalCount() throws IOException, SQLException {
		Conn conn1 = new Conn();
		String sql="select count(id) from genre ";
		ResultSet result=null;
		result=conn1.executeQuery(sql);

		int r =0;
		while(result.next()){
			r =  result.getInt(1);
		}

		conn1.close();
		return r;
	}

	/**
	 * 删除已注册的分类
	 * @param info
	 *
	 */
	public int delById(String id){
		String sql="delete from genre where id='"+id+"'";
		int result=0;
		result=conn.executeUpdate(sql);
		conn.close();
		return result;
	}
}
