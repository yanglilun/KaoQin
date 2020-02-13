package com.kaoqin.services;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSON;
import com.kaoqin.entity.Apply;
import com.kaoqin.entity.Student;
import com.kaoqin.factory.DAOFactory;
import com.kaoqin.tool.DBUtils;
import com.kaoqin.tool.PropUtil;

/**
 * ����service
 * @author garen
 *
 */
public class ApplyService {

	public void apply(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException, SQLException {
//		��ȡ��Ϣ
		String sid = request.getParameter("sid");
		int week = Integer.parseInt(request.getParameter("week"));
		int day = Integer.parseInt(request.getParameter("day"));
		String reason = request.getParameter("reason");
		Part part = request.getPart("pic");
		String fname="";
		if(!part.getSubmittedFileName().equals("")){
//		��ȡ�������洢·��
			Properties prop = PropUtil.getProp();
			String base = prop.getProperty("base")+File.separator+"kaoqin";
//		��ȡ�ļ���
			fname = part.getSubmittedFileName();
			String path = base+File.separator+fname;
			File f = new File(base);
			if(!f.exists()){
				f.mkdirs();
			}
			part.write(path);			
		}
//		�������ݿ�
		Apply a = new Apply();
		a.setSid(sid);
		a.setWeek(week);
		a.setDay(day);
		a.setPath(fname);
		a.setReason(reason);
		
		Connection conn = DBUtils.getconn();
		boolean flag = DAOFactory.newInstance().getApplyDAO(conn).insert(a);
		conn.close();
		response.sendRedirect("applyok.html");
   }
	
	/**�鿴��רҵ��ǩ����
	 * @throws IOException */
	public void showApply(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String subname = (String) session.getAttribute("subname");
		List<Apply> subapply = DAOFactory.newInstance().getApplyDAO(null).getApplyBySubname(subname);
		System.out.println(subapply);
		String json = JSON.toJSONString(subapply);
		System.out.println(json);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
	}
	
	/**��������id
	 * @throws IOException */
	public void saveaid(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String aid = request.getParameter("aid");
		HttpSession session = request.getSession();
		session.setAttribute("aid", aid);
		System.out.println("����aid"+aid);
		PrintWriter pw = response.getWriter();
		pw.write("applyinfo.jsp");
		pw.flush();
		pw.close();
	}
	
	/**�������id
	 * @throws SQLException 
	 * @throws IOException */
	public void checkApply(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
//		��ȡaid
		HttpSession session = request.getSession();
		String aid = (String) session.getAttribute("aid");
		
//		����aid��ȡapply��ǩ����
		Apply a = DAOFactory.newInstance().getApplyDAO(null).getApplyByAid(aid);
		
//		ͨ��or��ͨ��?
		String check = request.getParameter("check");
		Connection conn = DBUtils.getconn();
		PrintWriter pw = response.getWriter();
		if(check.equals("true")){
			System.out.println("ͨ��");
			pw.write("ͨ��");
//			������ǩ��
			DAOFactory.newInstance().getApplyDAO(conn).updateStatusByAid(aid, 1);
//			���³ٵ���
			DAOFactory.newInstance().getLateDAO(conn).deleteLateBySidWeekDay(a.getSid(), a.getWeek(), a.getDay());
		}else{
			System.out.println("��ͨ��");
			pw.write("��ͨ��");
			DAOFactory.newInstance().getApplyDAO(conn).updateStatusByAid(aid, 2);
		}
		conn.close();
		pw.flush();
		pw.close();
	}
}
