package com.kaoqin.DAO;

import java.sql.Connection;

import com.kaoqin.entity.Grade;
import com.kaoqin.tool.DBUtils;

/**
 * ѧԺDAO
 * @author garen
 */
public class GradeDAO {

	private Connection conn;

	public GradeDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	/**����gid��ȡԺ����*/
	public Grade getGradeByGid(String gid) {
		String sql = "select * from grade where gid = ? ";
		return DBUtils.queryOneObject(Grade.class, sql, gid);
	}
}
