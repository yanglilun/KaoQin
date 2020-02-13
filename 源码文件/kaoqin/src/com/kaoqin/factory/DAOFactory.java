package com.kaoqin.factory;

import java.sql.Connection;

import com.kaoqin.DAO.ApplyDAO;
import com.kaoqin.DAO.ClassDAO;
import com.kaoqin.DAO.EndListDAO;
import com.kaoqin.DAO.GradeDAO;
import com.kaoqin.DAO.LateDAO;
import com.kaoqin.DAO.StudentDAO;
import com.kaoqin.DAO.SubjectDAO;
import com.kaoqin.DAO.TeacherDAO;
import com.kaoqin.DAO.wllDAO;

/**
 * DAO工厂，单例模式
 * @author garen
 */
public class DAOFactory {

	//饿汉模式
	private static DAOFactory factory = new DAOFactory();
	
	public static DAOFactory newInstance(){
		return factory;
	}
	
	public StudentDAO getStudentDAO(Connection conn) {
		return new StudentDAO(conn);
	}
	
	public ClassDAO getClassDAO(Connection conn) {
		return new ClassDAO(conn);
	}
	
	public LateDAO getLateDAO(Connection conn) {
		return new LateDAO(conn);
	}
	
	public ApplyDAO getApplyDAO(Connection conn) {
		return new ApplyDAO(conn);
	}
	
	
	public TeacherDAO getTeacherDAO(Connection conn) {
		return new TeacherDAO(conn);
	}
	
	public SubjectDAO getSubjectDAO(Connection conn) {
		return new SubjectDAO(conn);
	}
	
	public GradeDAO getGradeDAO(Connection conn) {
		return new GradeDAO(conn);
	}
	
	public wllDAO getWllDAO() {
		return new wllDAO();
	}
	
	public EndListDAO getEndListDAO() {
		return new EndListDAO();
	}
}
