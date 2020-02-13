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
 * �༶service
 * @author garen
 *
 */
public class ClassService {

	/**��Ӱ༶
	 * @throws IOException 
	 * @throws SQLException */
	public void addClass(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
//		��ȡsubid
		String subid = request.getParameter("subid");
//		����subid��ȡsubname
		String subname = DAOFactory.newInstance().getSubjectDAO(null).getSubjectBySubid(subid).getSubname();
//		��ȡclassid
		String classid = request.getParameter("classid");
//		��ȡclassname
		String classname = request.getParameter("classname");
//		��ȡ����
		String year = request.getParameter("year");
//		����class����
		Class c = new Class();
		c.setClassid(classid);
		c.setSubname(subname);
		c.setClassname(classname);
		c.setSubid(subid);
		c.setYear(year);
//		��ӵ����ݿ�
		Connection conn = DBUtils.getconn();
		boolean f = DAOFactory.newInstance().getClassDAO(conn).insert(c);
		conn.close();
		System.out.println(f);
		if(f){
	//		��ȡ��ǰ���а༶����session
			new ClassService().saveAllClass(request, response);
			response.sendRedirect("addstudent.jsp");			
		}else{
			response.sendRedirect("adderror.html");
		}
	}
	
	/**�鿴�༶
	 * @throws IOException */
	public void showclass(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String subid = request.getParameter("subid");
//		����subid��ȡ���а༶
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
	
	/**����subid��ȡ���а༶����session*/
	public void saveAllClass(HttpServletRequest request,HttpServletResponse response) {
//		��ȡsubid
		HttpSession session = request.getSession();
		Teacher me = (Teacher) session.getAttribute("me");
		String subid = me.getSubid();
//		��ȡ���а༶
		List<Class> allclass = DAOFactory.newInstance().getClassDAO(null).getClassBySubid(subid);
		session.setAttribute("allclass", allclass);
	}
}
