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
 * 考勤service
 * @author garen
 */
public class LateService {

	/**录入迟到信息
	 * @throws IOException */
	public void insert(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		获取周次
		String week = request.getParameter("week");
//		获取星期
		String day = request.getParameter("day");
//		获取word字符串
		String str = request.getParameter("word");
//		按空格分割
		String[] nums = str.split(" ");
		
//		获取当前班级信息
		HttpSession session = request.getSession();
		List<Student> allstu = (List) session.getAttribute("allstu");
		if(Objects.isNull(allstu)){
			System.out.println("未初始化");
			PrintWriter pw = response.getWriter();
			pw.write("请查看尾号进行初始化");
			pw.flush();
			pw.close();
			return;
		}
//		去重
		List<String> list = new ArrayList<String>(Arrays.asList(nums));
		List<String> disnum = list.stream().distinct().collect(Collectors.toList());
		
		Connection conn = DBUtils.getconn();
		LateDAO lateDAO = DAOFactory.newInstance().getLateDAO(conn);
		boolean f = false;
		for (String num : disnum) {
//			根据num获取sid
			String sid = this.getSidByNum(allstu, num);
//			获取num出现次数
			int time = this.countNumInArray(num, nums);
			System.out.println("学号:"+sid+" 编号:"+num+" 次数:"+time);
			Late l = new Late();
			l.setSid(sid);
			l.setWeek(Integer.parseInt(week));
			l.setDay(Integer.parseInt(day));
			l.setTime(time);
			f = lateDAO.insert(l);
		}
		PrintWriter pw = response.getWriter();
		if(f){
			pw.write("录入成功");
		}else{
			pw.write("录入失败");
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
	
	/**加载本周迟到list
	 * @throws IOException */
	public void loadLatelistByWeek(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		获取周次
		String week = request.getParameter("week");
		if(week.equals("")){
			return;
		}
//		获取classid
		HttpSession session = request.getSession();
		Student me = (Student)session.getAttribute("me");
		String classid = me.getClassid();
		
		LateDAO lateDAO = DAOFactory.newInstance().getLateDAO(null);
		List<Kaoqin> kaoqin = lateDAO.getLateByWeekAndClassid(Integer.parseInt(week),classid);
		List<Latelist> latelist = lateDAO.generateLatelist(kaoqin);
//		存入session
		session.setAttribute("latelist", latelist);
		session.setAttribute("defweek", Integer.parseInt(week));
		
//		输出提示
		PrintWriter pw = response.getWriter();
		pw.write("true");
		pw.flush();
		pw.close();
	}
	
	/**教师加载本周迟到list
	 * @throws IOException */
	public void loadLatelistByWeekClassid(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		获取周次
		String week = request.getParameter("week");
		if(week.equals("")){
			return;
		}
//		获取classid
		String classid = request.getParameter("classid");
		HttpSession session = request.getSession();
		LateDAO lateDAO = DAOFactory.newInstance().getLateDAO(null);
		List<Kaoqin> kaoqin = lateDAO.getLateByWeekAndClassid(Integer.parseInt(week),classid);
		List<Latelist> latelist = lateDAO.generateLatelist(kaoqin);
//		存入session
		session.setAttribute("latelist", latelist);
		session.setAttribute("defweek", Integer.parseInt(week));
		session.setAttribute("classid", classid);
		
//		输出提示
		PrintWriter pw = response.getWriter();
		pw.write("true");
		pw.flush();
		pw.close();
	}
	
	/**统计出现次数*/
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
	
	/**从list中根据num找sid*/
	private String getSidByNum(List<Student> allstu,String num){
		for (Student stu : allstu) {
			if(stu.getNum().equals(num)){
				return stu.getSid();
			}
		}
		return null;
	}
	
}
