package org.dal;

import org.common.Conn;
import org.model.CommentInfo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Comment {
	Conn conn=new Conn();

	public Comment() throws IOException {
	}

	/**
	 * 获取评论列表
	 * @return
	 * @throws SQLException
	 */
	public List<CommentInfo> getList() throws SQLException{
		List<CommentInfo> list=new ArrayList<CommentInfo>();
		String sql="select * from comment order by id desc";
		ResultSet rs=conn.executeQuery(sql);
		while(rs.next()){
			CommentInfo info=new CommentInfo();
			info.setNovelId(rs.getInt("Id"));
			info.setContext(rs.getString("Context"));
			info.setNovelId(rs.getInt("NovelId"));
			info.setCreatedTime(rs.getDate("CreatedTime"));
			info.setReaderName(rs.getString("ReaderName"));
			list.add(info);
			System.out.print(list);
		}
		conn.close();
		return list;
	}
	/**
	 * 
	 * @param classId
	 * @return
	 * @throws SQLException
	 */
	public CommentInfo getCommentInfo(int id)throws SQLException{
		CommentInfo info=new CommentInfo();
		String sql="select * from Comment c where id="+id+"";
		ResultSet rs=conn.executeQuery(sql);
		while(rs.next()){
			info.setId(rs.getInt("Id"));
			info.setContext(rs.getString("Context"));
			info.setNovelId(rs.getInt("NovelId"));
			info.setCreatedTime(rs.getDate("CreatedTime"));
			info.setReaderName(rs.getString("ReaderName"));
		}
		conn.close();
		return info;
		
	}
	/**
	 *  获取某小说下的评论
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List<CommentInfo> getListByNovelId(int novelid) throws SQLException{
		List<CommentInfo> list=new ArrayList<CommentInfo>();
		String sql="select * from comment  where novelId="+novelid+" order by id desc";
		ResultSet rs=conn.executeQuery(sql);
		while(rs.next()){
			CommentInfo info=new CommentInfo();
			info.setNovelId(rs.getInt("novelId"));
			info.setContext(rs.getString("Context"));
			info.setNovelId(rs.getInt("NovelId"));
			info.setCreatedTime(rs.getDate("CreatedTime"));
			info.setReaderName(rs.getString("ReaderName"));
			info.setId(rs.getInt("id"));
			list.add(info);
		}
		conn.close();
		System.out.println("CommenetInfo: list "+list);

		return list;
	}
	/**
	 * 插入评论
	 * @param info
	 * @return
	 */
	public int insert(CommentInfo info){
		String sql="insert into comment(context,CreatedTime,readerName,novelId)values";
		sql=sql+"('"+info.getContext()+"',getdate(),'"+info.getReaderName()+"',"+info.getNovelId()+")";
		int result=0;
		System.out.println(sql);
		result=conn.executeUpdate(sql);
		conn.close();
		return result;
	}
	/**
	 * 更新评论
	 * @param info
	 * @return
	 */
	public int update(CommentInfo info){
		String sql="update Comment set "+" Context='"+info.getContext()+"',novelId='"+info.getNovelId()+"',"
				+ "CreatedTime='"+info.getCreatedTime()+"',readerName='"+info.getReaderName()+"' where id="+info.getId()+"";
		int result=0;
		System.out.println(sql);
		result=conn.executeUpdate(sql);
		conn.close();
		return result;
	}
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	public int delete(int id){
		String sql="delete from comment where id="+id;
		int result=0;
		result=conn.executeUpdate(sql);
		conn.close();
		return result;
	}
}
