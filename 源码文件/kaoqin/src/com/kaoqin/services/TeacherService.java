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
 * ��ʦservice
 * @author garen
 *
 */
public class TeacherService {

	/**��½����*/
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("��½����");
//		��ȡ�˺ź�����
		String tid = request.getParameter("username");
		String password = request.getParameter("password");
//		��ȡ��ʦ
		Teacher t = DAOFactory.newInstance().getTeacherDAO(null).getTeacherByTid(tid);
		System.out.println(t);
		PrintWriter pw = response.getWriter();
//		�����ʦ����,��������ͬ
		if(Objects.nonNull(t)&&t.getPassword().equals(password)){
			System.out.println("��½�ɹ�");
//			�ѽ�ʦ����session
			HttpSession session = request.getSession();
			session.setAttribute("me", t);
//			��ȡרҵ������session
			Subject subject = DAOFactory.newInstance().getSubjectDAO(null).getSubjectBySubid(t.getSubid());
			session.setAttribute("subname", subject.getSubname());
//			��ȡ��ʦ��������а༶������session
			new ClassService().saveAllClass(request, response);
			pw.write("true");
			pw.flush();
		}else{
			System.out.println("��½ʧ��");
			pw.write("false");
			pw.flush();
		}
		pw.close();
	}
	
	/**������
	 * @throws IOException 
	 * @throws SQLException */
	public void repassword(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
//		��ȡ��ǰ�û�tid
		HttpSession session = request.getSession();
		Teacher me = (Teacher) session.getAttribute("me");
		String tid = me.getTid();
//		��ȡ������
		String newpassword = request.getParameter("repassword");
		
		PrintWriter pw = response.getWriter();
//		���������;�������ͬ
		if(me.getPassword().equals(newpassword)){
			pw.write("���������ͬ��");
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

	/**ע��
	 * @throws IOException */
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		PrintWriter pw = response.getWriter();
		pw.write("�˳���¼");
		pw.flush();
		pw.close();
	}
	
	/**�������ձ�
	 * @throws IOException */
	public void generateEnd(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		��ȡ��ʼ�ܺͽ�����
		int start = Integer.parseInt(request.getParameter("start"));
		int end = Integer.parseInt(request.getParameter("end"));
//		��ȡ��ǰ��ʦ��רҵ
		HttpSession session = request.getSession();
		String subname = (String) session.getAttribute("subname");
		System.out.println("�����ܱ�רҵ:"+subname);
		
		List<WeekLateList> wllByWeek = DAOFactory.newInstance().getWllDAO().getWllByWeek(start, end,subname);
		List<EndList> endList = DAOFactory.newInstance().getEndListDAO().initEndList(wllByWeek);
//		����session
		session.setAttribute("endlist", endList);
		endList.forEach(e->System.out.println(e));
		PrintWriter pw = response.getWriter();
		pw.write(JSON.toJSONString(endList));
		pw.flush();
		pw.close();
	}
	
	/**����endlist
	 * @throws IOException */
	public void download(HttpServletRequest request , HttpServletResponse response) throws IOException {
//		��ȡbase��ַ
		String base = PropUtil.getProp().getProperty("base");
		File f = new File(base+File.separator+"temp.txt");
//		��ȡendlist
		HttpSession session = request.getSession();
		List<EndList> endlist = (List<EndList>) session.getAttribute("endlist");
//		����
		FileOutputStream fos = new FileOutputStream(f);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		for (EndList e : endlist) {
			String str = "";
			str+=e.getSname()+"\t";
			str+="��"+e.getWeek()+"��"+"\t";
			str+=e.getTime()+"��"+"\t";
			str+=e.getAlltime()+"��"+"\n";
			bw.append(str);
		}
		bw.close();
		osw.close();
		fos.close();
		
//		�����ļ�		
		FileDownloadTools.download(f.getAbsolutePath(), response);
//		ɾ����ʱ�ļ�
		f.delete();
	}
}
