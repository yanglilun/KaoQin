package com.kaoqin.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaoqin.entity.Class;
import com.kaoqin.entity.Teacher;
import com.kaoqin.factory.DAOFactory;
import com.kaoqin.tool.DBUtils;

/**
 * 班级service
 * @author garen
 *
 */
public class ClassService {

	/**添加班级
	 * @throws IOException 
	 * @throws SQLException */
	public void addClass(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
//		获取subid
		String subid = request.getParameter("subid");
//		根据subid获取subname
		String subname = DAOFactory.newInstance().getSubjectDAO(null).getSubjectBySubid(subid).getSubname();
//		获取classid
		String classid = request.getParameter("classid");
//		获取classname
		String classname = request.getParameter("classname");
//		获取届数
		String year = request.getParameter("year");
//		创建class对象
		Class c = new Class();
		c.setClassid(classid);
		c.setSubname(subname);
		c.setClassname(classname);
		c.setSubid(subid);
		c.setYear(year);
//		添加到数据库
		Connection conn = DBUtils.getconn();
		boolean f = DAOFactory.newInstance().getClassDAO(conn).insert(c);
		conn.close();
		System.out.println(f);
		if(f){
	//		获取当前所有班级存入session
			new ClassService().saveAllClass(request, response);
			response.sendRedirect("addstudent.jsp");			
		}else{
			response.sendRedirect("adderror.html");
		}
	}
	
	/**查看班级
	 * @throws IOException */
	public void showclass(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String subid = request.getParameter("subid");
//		根据subid获取所有班级
		List<Class> allclass = DAOFactory.newInstance().getClassDAO(null).getClassBySubid(subid);
		String end = "";
		for (Class c : allclass) {
			end+=c.getClassid()+" "+c.getClassname()+"\n";
		}
		PrintWriter pw = response.getWriter();
		pw.write(end);
		pw.flush();
		pw.close();
	}
	
	/**根据subid获取所有班级存入session*/
	public void saveAllClass(HttpServletRequest request,HttpServletResponse response) {
//		获取subid
		HttpSession session = request.getSession();
		Teacher me = (Teacher) session.getAttribute("me");
		String subid = me.getSubid();
//		获取所有班级
		List<Class> allclass = DAOFactory.newInstance().getClassDAO(null).getClassBySubid(subid);
		session.setAttribute("allclass", allclass);
	}
}
