package com.kaoqin.DAO;

import java.sql.Connection;

import com.kaoqin.entity.Teacher;
import com.kaoqin.tool.DBUtils;


/**
 * ��ʦDAO
 * @author garen
 *
 */
public class TeacherDAO {

	private Connection conn;

	public TeacherDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	/**����tid��ȡteacher*/
	public Teacher getTeacherByTid(String tid) {
		String sql = "select * from teacher where tid = ?";
		Teacher teacher = DBUtils.queryOneObject(Teacher.class, sql, tid);
		return teacher;
	}
	
	/**����tid�޸�����*/
	public boolean updatePwdByTid(String tid,String newpassword) {
		String sql = "update teacher set password=? where tid=?";
		int i = DBUtils.executeUpdate(conn, sql, newpassword,tid);
		return i>0;
	}
}
