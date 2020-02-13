package com.kaoqin.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaoqin.DAO.LateDAO;
import com.kaoqin.DTO.Kaoqin;
import com.kaoqin.DTO.Latelist;
import com.kaoqin.entity.Late;
import com.kaoqin.entity.Student;
import com.kaoqin.factory.DAOFactory;
import com.kaoqin.tool.DBUtils;

/**
 * ����service
 * @author garen
 */
public class LateService {

	/**¼��ٵ���Ϣ
	 * @throws IOException */
	public void insert(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		��ȡ�ܴ�
		String week = request.getParameter("week");
//		��ȡ����
		String day = request.getParameter("day");
//		��ȡword�ַ���
		String str = request.getParameter("word");
//		���ո�ָ�
		String[] nums = str.split(" ");
		
//		��ȡ��ǰ�༶��Ϣ
		HttpSession session = request.getSession();
		List<Student> allstu = (List) session.getAttribute("allstu");
		if(Objects.isNull(allstu)){
			System.out.println("δ��ʼ��");
			PrintWriter pw = response.getWriter();
			pw.write("��鿴β�Ž��г�ʼ��");
			pw.flush();
			pw.close();
			return;
		}
//		ȥ��
		List<String> list = new ArrayList<String>(Arrays.asList(nums));
		List<String> disnum = list.stream().distinct().collect(Collectors.toList());
		
		Connection conn = DBUtils.getconn();
		LateDAO lateDAO = DAOFactory.newInstance().getLateDAO(conn);
		boolean f = false;
		for (String num : disnum) {
//			����num��ȡsid
			String sid = this.getSidByNum(allstu, num);
//			��ȡnum���ִ���
			int time = this.countNumInArray(num, nums);
			System.out.println("ѧ��:"+sid+" ���:"+num+" ����:"+time);
			Late l = new Late();
			l.setSid(sid);
			l.setWeek(Integer.parseInt(week));
			l.setDay(Integer.parseInt(day));
			l.setTime(time);
			f = lateDAO.insert(l);
		}
		PrintWriter pw = response.getWriter();
		if(f){
			pw.write("¼��ɹ�");
		}else{
			pw.write("¼��ʧ��");
		}
		pw.flush();
		pw.close();
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**���ر��ܳٵ�list
	 * @throws IOException */
	public void loadLatelistByWeek(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		��ȡ�ܴ�
		String week = request.getParameter("week");
		if(week.equals("")){
			return;
		}
//		��ȡclassid
		HttpSession session = request.getSession();
		Student me = (Student)session.getAttribute("me");
		String classid = me.getClassid();
		
		LateDAO lateDAO = DAOFactory.newInstance().getLateDAO(null);
		List<Kaoqin> kaoqin = lateDAO.getLateByWeekAndClassid(Integer.parseInt(week),classid);
		List<Latelist> latelist = lateDAO.generateLatelist(kaoqin);
//		����session
		session.setAttribute("latelist", latelist);
		session.setAttribute("defweek", Integer.parseInt(week));
		
//		�����ʾ
		PrintWriter pw = response.getWriter();
		pw.write("true");
		pw.flush();
		pw.close();
	}
	
	/**��ʦ���ر��ܳٵ�list
	 * @throws IOException */
	public void loadLatelistByWeekClassid(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		��ȡ�ܴ�
		String week = request.getParameter("week");
		if(week.equals("")){
			return;
		}
//		��ȡclassid
		String classid = request.getParameter("classid");
		HttpSession session = request.getSession();
		LateDAO lateDAO = DAOFactory.newInstance().getLateDAO(null);
		List<Kaoqin> kaoqin = lateDAO.getLateByWeekAndClassid(Integer.parseInt(week),classid);
		List<Latelist> latelist = lateDAO.generateLatelist(kaoqin);
//		����session
		session.setAttribute("latelist", latelist);
		session.setAttribute("defweek", Integer.parseInt(week));
		session.setAttribute("classid", classid);
		
//		�����ʾ
		PrintWriter pw = response.getWriter();
		pw.write("true");
		pw.flush();
		pw.close();
	}
	
	/**ͳ�Ƴ��ִ���*/
	public int countNumInArray(String s, String[] alls) {
		List<String> list = Arrays.asList(alls);
		long count = list.stream().filter(new Predicate<String>() {

			@Override
			public boolean test(String t) {
				return t.equals(s);
			}
		}).count();
		return (int)count;
	}
	
	/**��list�и���num��sid*/
	private String getSidByNum(List<Student> allstu,String num){
		for (Student stu : allstu) {
			if(stu.getNum().equals(num)){
				return stu.getSid();
			}
		}
		return null;
	}
	
}
