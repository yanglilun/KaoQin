package com.kaoqin.DAO;

import java.sql.Connection;
import java.util.List;

import com.kaoqin.entity.Subject;
import com.kaoqin.tool.DBUtils;

/**
 * רҵDAO
 * @author garen
 */
public class SubjectDAO {
	private Connection conn;

	public SubjectDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	/**����gid��ȡ����רҵ*/
	public List<Subject> getSubjectByGid(String gid) {
		String sql = "select * from subject where gid = ?";
		List<Subject> list = DBUtils.queryList(Subject.class, sql, gid);
		return list;
	}
	
	/**����subid��ȡsubject*/
	public Subject getSubjectBySubid(String subid) {
		String sql = "select * from subject where subid = ?";
		Subject subject = DBUtils.queryOneObject(Subject.class, sql, subid);
		return subject;
	}
}
