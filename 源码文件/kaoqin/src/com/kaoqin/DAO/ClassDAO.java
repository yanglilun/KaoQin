package com.kaoqin.DAO;

import java.sql.Connection;
import java.util.List;

import com.kaoqin.entity.Class;
import com.kaoqin.tool.DBUtils;

/**
 * 班级DAO
 * @author garen
 */
public class ClassDAO {
	
	private Connection conn;

	public ClassDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	/**根据班级id查找班级*/
	public Class getClassByClassid(String Classid) {
		String sql = "select * from class where classid=?";
		Class c = DBUtils.queryOneObject(Class.class, sql, Classid);
		return c;
	}
	
	/**根据subid获取所有班级*/
	public List<Class> getClassBySubid(String subid) {
		String sql = "select * from class where subid=? order by classid asc";
		List<Class> list = DBUtils.queryList(Class.class, sql, subid);
		return list;
	}
	
	/**添加班级*/
	public boolean insert(Class c) {
		String sql = "insert into class values(?,?,?,?,?)";
		int i = DBUtils.executeUpdate(conn, sql,c.getClassid(),c.getSubname(),c.getClassname(),c.getSubid(),c.getYear());
		return i>0;
	}
}
