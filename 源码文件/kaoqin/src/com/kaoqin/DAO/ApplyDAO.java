package com.kaoqin.DAO;

import java.sql.Connection;
import java.util.List;

import com.kaoqin.entity.Apply;
import com.kaoqin.tool.DBUtils;

/**
 * ��ǩDAO
 * @author garen
 */
public class ApplyDAO {
	
	private Connection conn;

	public ApplyDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	/**�������*/
	public boolean insert(Apply a) {
		String sql = "insert into apply values(?,?,?,?,?,?,?)";
		int i = DBUtils.executeUpdate(conn, sql,null,a.getSid(),a.getWeek(),a.getDay(),a.getPath(),a.getStatus(),a.getReason());	
		return i>0;
	}
	
	/**����subname��ȡ����*/
	public List<Apply> getApplyBySubname(String subname) {
		String sql = "SELECT apply.* FROM apply,student "
				+ "WHERE	student.sid=apply.sid "
				+ "AND student.subname = ?";
		return DBUtils.queryList(Apply.class, sql, subname);
	}
	
	/**����aid��������*/
	public boolean updateStatusByAid(String aid,int status) {
		String sql = "update apply set status = ? where aid = ?";
		int i = DBUtils.executeUpdate(conn, sql, status,aid);
		return i>0;
	}
	
	/**����aid��ȡapply*/
	public Apply getApplyByAid(String aid) {
		String sql = "select * from apply where aid = ?";
		return DBUtils.queryOneObject(Apply.class, sql, aid);
	}

}
