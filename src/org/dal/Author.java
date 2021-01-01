package org.dal;

import org.common.Conn;
import org.common.DataValidator;
import org.model.AuthorInfo;
import org.model.PageBean;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Author {
	Conn conn=new Conn();

	public Author() throws IOException {
	}

	/**
	 *  判断登陆用户是否合法
	 * @param authorName
	 * @param authorPassword
	 * @return
	 * @throws SQLException
	 */
	public boolean isExist(String authorName,String authorPassword)throws SQLException{
		boolean result=false;
		AuthorInfo auinfo =new AuthorInfo();
		String sql="select * from author where authorName='"+authorName+"'and authorPassword='"+authorPassword+"'";
		System.out.println(sql);
		ResultSet rs=conn.executeQuery(sql);
		if(rs.next()){
			auinfo.setAuthorName(rs.getString("authorName"));
			auinfo.setAuthorPassword(rs.getString("authorPassword"));
			result=true;
		}
		conn.close();
		return result;
	}


	/**
	 * 判断注册用户是否已经存在
	 * @param authorName
	 * @return
	 * @throws SQLException
	 */
	public boolean isExistAuthorInfo(String authorName)throws SQLException{
		boolean result=false;
		AuthorInfo auInfo=new AuthorInfo();
		String sql="select * from author au where authorName='"+authorName+"'";
		ResultSet rs=conn.executeQuery(sql);
		if(rs.next()){
			auInfo.setAuthorName(rs.getString("authorName"));
			result=true;
		}
		conn.close();
		return result;
	}
	/**
	 *用户注册
	 * @param info
	 * @return
	 */
	public int insert(AuthorInfo info)throws SQLException{
		String sql="insert into author(authorName,authorPassword,authorEmail) values";
		sql=sql+"('"+info.getAuthorName()+"','"+info.getAuthorPassword()+"','"+info.getAuthorEmail()+"')";
		int result=0;
		result=conn.executeUpdate(sql);
		conn.close();
		return result;
	}
	/**
	 * 修改用户信息
	 * @param info
	 * @return
	 */
	public int update(AuthorInfo info){
		String sql="update author set authorPassword='"+info.getAuthorPassword()+"',authorEmail='"+info.getAuthorEmail()+"'"
				+ "where authorName='"+info.getAuthorName()+"'";
		int result=0;
		result=conn.executeUpdate(sql);
		conn.close();
		return result;
	}
	/**
	 * 删除已注册的用户
	 *
	 */
	public int delete(String authorName){
		String sql="delete from author where authorName='"+authorName+"'";
		int result=0;
		result=conn.executeUpdate(sql);
		conn.close();
		return result;
	}


	/**
	 * 删除已注册的用户
	 * @param info
	 *
	 */
	public int delById(String id){
		String sql="delete from author where id='"+id+"'";
		int result=0;
		result=conn.executeUpdate(sql);
		conn.close();
		return result;
	}

	/**
	 * 删除已注册的用户
	 *
	 */
	public void delByIds(String[] ids){
		for(String id:ids) {
			this.delById(id);
		}
	}
	/**
	 *获取用户列表
	 * @param keyword
	 * @return
	 * @throws SQLException
	 */
	public List<AuthorInfo>getList(String keyword)throws SQLException{
		List<AuthorInfo> list=new ArrayList<AuthorInfo>();
		String sql="select * from author";
		if(DataValidator.isNullOrEmpty(keyword)){
			sql=sql+ " order by id desc";
		}else{
			sql=sql+" where authorName like '%"+keyword+"%' order by id desc";
		}
		ResultSet rs=conn.executeQuery(sql);
		while(rs.next()){
			AuthorInfo ainfo=new AuthorInfo();
			ainfo.setId(rs.getInt("id"));
			ainfo.setAuthorName(rs.getString("authorName"));
			ainfo.setAuthorPassword(rs.getString("authorPassword"));
			ainfo.setAuthorEmail(rs.getString("authorEmail"));
			list.add(ainfo);
		}
		conn.close();
		return list;
		
	}

	/**
	 * 获取作者所有信息
	 * @return
	 * @throws SQLException
	 */
	public List<AuthorInfo>getAll() throws SQLException{
		List<AuthorInfo> list = new ArrayList<AuthorInfo>();
		String sql="select * from author";
		ResultSet rs=conn.executeQuery(sql);
		while(rs.next()){
			AuthorInfo ainfo=new AuthorInfo();
			ainfo.setId(rs.getInt("id"));
			ainfo.setAuthorName(rs.getString("authorName"));
			ainfo.setAuthorPassword(rs.getString("authorPassword"));
			ainfo.setAuthorEmail(rs.getString("authorEmail"));
			list.add(ainfo);
		}
		conn.close();
		return list;
	}

//	/**
//	 * 分页查询
//	 * @param _currentPage
//	 * @param _rows
//	 * @return
//	 */
//	public PageBean<AuthorInfo> findUserByPage(String _currentPage, String _rows) throws SQLException, IOException {
//		int currentPage = Integer.parseInt(_currentPage);
//		int rows = Integer.parseInt(_rows);
//		if(currentPage<=1){
//			currentPage=1;
//		}
//
//		PageBean<AuthorInfo> pb = new PageBean<AuthorInfo>();
//		int totalCount = this.findTotalCount();
//		if(currentPage>=totalCount){
//			currentPage=totalCount;
//		}
//		pb.setTotalCount(totalCount);
//
//
//		pb.setCurrentPage(currentPage);
//		pb.setRows(rows);
//
//
//
//		int start = (currentPage)*rows;
//		List<AuthorInfo>  list = findByPage(start,rows);
//		pb.setList(list);
//
//		int totalPage = (totalCount%rows)==0? totalCount/rows : totalCount/rows+1;
//		pb.setTotalPage(totalPage);
//		return pb;
//	}

	private List<AuthorInfo> findByPage(int start, int rows, Map<String, String[]> condition) throws SQLException {
//		String sql = "select * from author limit start,rows";

//		String sql="SELECT * FROM (SELECT TOP rows * from (SELECT TOP (start*rows) * FROM author ORDER BY id asc) " +
//				"as b ORDER BY id desc) as c ORDER BY id asc";
		String sql ="SELECT * FROM (SELECT TOP "+(rows)+"* from (SELECT TOP "+(start)+" * FROM author ORDER BY id asc) as b ORDER BY id desc) as c ORDER BY id asc";

		String sql1="select * from author where 1 = 1 ";

		StringBuilder sb= new StringBuilder(sql1);

		Set<String> keySet = condition.keySet();
		for(String key: keySet){
			String value = condition.get(key)[0];
			if(key.equals("currentPage") || key.equals("rows")){
				continue;
			}
			if(value!=null && !"".equals(value)){
				sb.append(" and "+key+" like '%"+value+"%' ");
			}
		}

		System.out.println("sb  SQL : "+sb.toString());
		String s= "SELECT * FROM (SELECT TOP "+(rows)+"* from (SELECT TOP "+(start)+" * FROM(";
		StringBuilder sb1 = new StringBuilder(s);
		sb1.append(sb).append( " ) as o ORDER BY id asc) as b ORDER BY id desc) as c ORDER BY id asc;");
		System.out.println("sb1:    "+sb1.toString());
		sql=sb1.toString();
		//  SELECT * FROM (SELECT TOP 5* from (SELECT TOP 5 * FROM(select * from author where 1 = 1  and authorName like '%大罗金仙%'  and authorEmail like '%1@qq.com%' ORDER BY id asc) as b ORDER BY id desc) as c ORDER BY id asc
		List<AuthorInfo> list = new ArrayList<AuthorInfo>();

//		sql = sb.toString();

		ResultSet rs=conn.executeQuery(sql);
		while(rs.next()){
			AuthorInfo ainfo=new AuthorInfo();
			ainfo.setId(rs.getInt("id"));
			ainfo.setAuthorName(rs.getString("authorName"));
			ainfo.setAuthorPassword(rs.getString("authorPassword"));
			ainfo.setAuthorEmail(rs.getString("authorEmail"));
			list.add(ainfo);
		}
		conn.close();
		return list;
	}

	/**
	 * 返回总页数
	 * @return
	 */
	private static int findTotalCount(Map<String, String[]> condition) throws IOException, SQLException {


		Conn conn1 = new Conn();
		String sql="select count(*) from author where 1 = 1 ";
		StringBuilder sb=new StringBuilder(sql);

		Set<String> keySet = condition.keySet();
		for(String key: keySet){
			String value = condition.get(key)[0];
			if(key.equals("currentPage") || key.equals("rows")){
				continue;
			}
			if(value!=null && !"".equals(value)){
				sb.append(" and "+key+" like '%"+value+"%' ");
			}
		}

		System.out.println("SQL : "+sb.toString());

		ResultSet result = null;
		result=conn1.executeQuery(sb.toString());
		int count=0;
		while(result.next()){
			count = result.getInt(1);
		}
//		System.out.println(result.toString());
		conn1.close();
		return count;

	}
	/**
	 * 分页条件查询
	 * @param _currentPage
	 * @param _rows
	 * @return
	 */
	public PageBean<AuthorInfo> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) throws SQLException, IOException {
		int currentPage = Integer.parseInt(_currentPage);
		int rows = Integer.parseInt(_rows);
		if(currentPage<=1){
			currentPage=1;
		}

		PageBean<AuthorInfo> pb = new PageBean<AuthorInfo>();
		int totalCount = findTotalCount(condition);
		if(currentPage>=totalCount){
			currentPage=totalCount;
		}
		pb.setTotalCount(totalCount);


		pb.setCurrentPage(currentPage);
		pb.setRows(rows);



		int start = (currentPage)*rows;
		List<AuthorInfo>  list = findByPage(start,rows,condition);
		pb.setList(list);

		int totalPage = (totalCount%rows)==0? totalCount/rows : totalCount/rows+1;
		pb.setTotalPage(totalPage);
		return pb;

	}

//	public static void main(String[] args) throws IOException, SQLException {
//		System.out.println("共有数据："+Author.findTotalCount());
//
//	}
}
