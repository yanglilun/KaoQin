package com.kaoqin.DAO;

import java.sql.Connection;

import com.kaoqin.entity.Teacher;
import com.kaoqin.tool.DBUtils;


/**
 * 教师DAO
 * @author garen
 *
 */
public class TeacherDAO {

	private Connection conn;

	public TeacherDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	/**根据tid获取teacher*/
	public Teacher getTeacherByTid(String tid) {
		String sql = "select * from teacher where tid = ?";
		Teacher teacher = DBUtils.queryOneObject(Teacher.class, sql, tid);
		return teacher;
	}
	
	/**根据tid修改密码*/
	public boolean updatePwdByTid(String tid,String newpassword) {
		String sql = "update teacher set password=? where tid=?";
		int i = DBUtils.executeUpdate(conn, sql, newpassword,tid);
		return i>0;
	}
}
