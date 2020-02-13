package com.kaoqin.DAO;

import java.sql.Connection;
import java.util.List;

import com.kaoqin.entity.Student;
import com.kaoqin.factory.DAOFactory;
import com.kaoqin.tool.DBUtils;

/**
 * ѧ������DAO
 * @author garen
 */
public class StudentDAO {

	private Connection conn;

	public StudentDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	/**���ѧ��*/
	public boolean insert(Student s) {
		String sql = "insert into student values(?,?,?,?,?,?,?)";
		int i = DBUtils.executeUpdate(conn, sql, s.getSid(),s.getSname(),s.getClassid(),
				s.getPassword(),s.getLevel(),s.getSubname(),s.getNum());
		return i>0;
	}
	
	/**����sid��ȡѧ��*/
	public Student getStudentBySid(String sid) {
		String sql = "select * from student where sid = ?";
		return DBUtils.queryOneObject(Student.class, sql, sid);
	}

	/**����ѧ���޸�ѧ����Ϣ*/
	public boolean updateStuBySid(Student s) {
		String sql = "update student set sname=?,classid=?,password=?,level=?,subname=? where sid=?";
		int f = DBUtils.executeUpdate(conn, sql, s.getSname(),s.getClassid(),s.getPassword(),s.getLevel(),s.getSubname(),s.getSid());
		return f>0;
	}
	
	/**���ݰ༶����ѧ��*/
	public List<Student> getAllStuByClassid(String classid) {
		String sql = "select * from student where classid=? order by sid asc";
		List<Student> list = DBUtils.queryList(Student.class, sql, classid);
		return list;
	}
	
	/**����ѧ���޸İ��ڱ��*/
	public boolean updateNumBySid(String sid,String num) {
		String sql = "update student set num = ? where sid=?";
		int i = DBUtils.executeUpdate(conn, sql, num, sid);
		return i>0;
	}
	
	/**����ѧ������Ȩ��*/
	public boolean setLevelBySid(String sid,int level) {
		String sql = "update student set level = ? where sid = ?";
		int i = DBUtils.executeUpdate(conn, sql, level,sid);
		return i>0;
	}
	
	/**����sid�޸�����*/
	public boolean updatePwdBySid(String sid,String newpassword) {
		String sql = "update student set password=? where sid=?";
		int i = DBUtils.executeUpdate(conn, sql, newpassword,sid);
		return i>0;
	}
	
	/**����רҵ����ȡѧ��*/
	public List<Student> getStudentBySubname(String subname) {
		String sql = "select * from student where subname = ?";
		return DBUtils.queryList(Student.class, sql, subname);
		
	}
	
	/**����sidɾ��ѧ��*/
	public boolean deleteStudentBySid(String sid) {
		String sql = "delete from student where sid = ?";
		int i = DBUtils.executeUpdate(conn, sql, sid);
		return i>0;
	}
	
}
