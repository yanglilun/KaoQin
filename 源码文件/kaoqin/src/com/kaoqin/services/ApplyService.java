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
 * 审批service
 * @author garen
 *
 */
public class ApplyService {

	public void apply(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException, SQLException {
//		获取信息
		String sid = request.getParameter("sid");
		int week = Integer.parseInt(request.getParameter("week"));
		int day = Integer.parseInt(request.getParameter("day"));
		String reason = request.getParameter("reason");
		Part part = request.getPart("pic");
		String fname="";
		if(!part.getSubmittedFileName().equals("")){
//		获取服务器存储路径
			Properties prop = PropUtil.getProp();
			String base = prop.getProperty("base")+File.separator+"kaoqin";
//		获取文件名
			fname = part.getSubmittedFileName();
			String path = base+File.separator+fname;
			File f = new File(base);
			if(!f.exists()){
				f.mkdirs();
			}
			part.write(path);			
		}
//		存入数据库
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
	
	/**查看本专业补签申请
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
	
	/**保存申请id
	 * @throws IOException */
	public void saveaid(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String aid = request.getParameter("aid");
		HttpSession session = request.getSession();
		session.setAttribute("aid", aid);
		System.out.println("保存aid"+aid);
		PrintWriter pw = response.getWriter();
		pw.write("applyinfo.jsp");
		pw.flush();
		pw.close();
	}
	
	/**审核申请id
	 * @throws SQLException 
	 * @throws IOException */
	public void checkApply(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
//		获取aid
		HttpSession session = request.getSession();
		String aid = (String) session.getAttribute("aid");
		
//		根据aid获取apply补签对象
		Apply a = DAOFactory.newInstance().getApplyDAO(null).getApplyByAid(aid);
		
//		通过or不通过?
		String check = request.getParameter("check");
		Connection conn = DBUtils.getconn();
		PrintWriter pw = response.getWriter();
		if(check.equals("true")){
			System.out.println("通过");
			pw.write("通过");
//			更新审补签表
			DAOFactory.newInstance().getApplyDAO(conn).updateStatusByAid(aid, 1);
//			更新迟到表
			DAOFactory.newInstance().getLateDAO(conn).deleteLateBySidWeekDay(a.getSid(), a.getWeek(), a.getDay());
		}else{
			System.out.println("不通过");
			pw.write("不通过");
			DAOFactory.newInstance().getApplyDAO(conn).updateStatusByAid(aid, 2);
		}
		conn.close();
		pw.flush();
		pw.close();
	}
}
