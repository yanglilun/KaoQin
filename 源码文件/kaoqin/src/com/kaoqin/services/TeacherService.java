package com.kaoqin.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.kaoqin.DTO.EndList;
import com.kaoqin.DTO.WeekLateList;
import com.kaoqin.entity.Subject;
import com.kaoqin.entity.Teacher;
import com.kaoqin.factory.DAOFactory;
import com.kaoqin.tool.DBUtils;
import com.kaoqin.tool.FileDownloadTools;
import com.kaoqin.tool.PropUtil;

/**
 * 教师service
 * @author garen
 *
 */
public class TeacherService {

	/**登陆操作*/
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("登陆操作");
//		获取账号和密码
		String tid = request.getParameter("username");
		String password = request.getParameter("password");
//		获取教师
		Teacher t = DAOFactory.newInstance().getTeacherDAO(null).getTeacherByTid(tid);
		System.out.println(t);
		PrintWriter pw = response.getWriter();
//		如果教师存在,且密码相同
		if(Objects.nonNull(t)&&t.getPassword().equals(password)){
			System.out.println("登陆成功");
//			把教师存入session
			HttpSession session = request.getSession();
			session.setAttribute("me", t);
//			获取专业名存入session
			Subject subject = DAOFactory.newInstance().getSubjectDAO(null).getSubjectBySubid(t.getSubid());
			session.setAttribute("subname", subject.getSubname());
//			获取教师管理的所有班级，存入session
			new ClassService().saveAllClass(request, response);
			pw.write("true");
			pw.flush();
		}else{
			System.out.println("登陆失败");
			pw.write("false");
			pw.flush();
		}
		pw.close();
	}
	
	/**改密码
	 * @throws IOException 
	 * @throws SQLException */
	public void repassword(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
//		获取当前用户tid
		HttpSession session = request.getSession();
		Teacher me = (Teacher) session.getAttribute("me");
		String tid = me.getTid();
//		获取新密码
		String newpassword = request.getParameter("repassword");
		
		PrintWriter pw = response.getWriter();
//		如果新密码和旧密码相同
		if(me.getPassword().equals(newpassword)){
			pw.write("与旧密码相同！");
			pw.flush();
			pw.close();
			return;
		}
		
		Connection conn = DBUtils.getconn();
		boolean f = DAOFactory.newInstance().getTeacherDAO(conn).updatePwdByTid(tid, newpassword);
		conn.close();
		if(f){
			pw.write("true");
		}else{
			pw.write("false");
		}
		pw.flush();			
		pw.close();
	}

	/**注销
	 * @throws IOException */
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		PrintWriter pw = response.getWriter();
		pw.write("退出登录");
		pw.flush();
		pw.close();
	}
	
	/**生成最终表
	 * @throws IOException */
	public void generateEnd(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		获取起始周和结束周
		int start = Integer.parseInt(request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));
//		获取当前教师的专业
		HttpSession session = request.getSession();
		String subname = (String) session.getAttribute("subname");
		System.out.println("生成总表，专业:"+subname);
		
		List<WeekLateList> wllByWeek = DAOFactory.newInstance().getWllDAO().getWllByWeek(start, end,subname);
		List<EndList> endList = DAOFactory.newInstance().getEndListDAO().initEndList(wllByWeek);
//		存入session
		session.setAttribute("endlist", endList);
		endList.forEach(e->System.out.println(e));
		PrintWriter pw = response.getWriter();
		pw.write(JSON.toJSONString(endList));
		pw.flush();
		pw.close();
	}
	
	/**下载endlist
	 * @throws IOException */
	public void download(HttpServletRequest request , HttpServletResponse response) throws IOException {
//		获取base地址
		String base = PropUtil.getProp().getProperty("base");
		File f = new File(base+File.separator+"temp.txt");
//		获取endlist
		HttpSession session = request.getSession();
		List<EndList> endlist = (List<EndList>) session.getAttribute("endlist");
//		遍历
		FileOutputStream fos = new FileOutputStream(f);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		for (EndList e : endlist) {
			String str = "";
			str+=e.getSname()+"\t";
			str+="第"+e.getWeek()+"周"+"\t";
			str+=e.getTime()+"次"+"\t";
			str+=e.getAlltime()+"次"+"\n";
			bw.append(str);
		}
		bw.close();
		osw.close();
		fos.close();
		
//		下载文件		
		FileDownloadTools.download(f.getAbsolutePath(), response);
//		删除临时文件
		f.delete();
	}
}
