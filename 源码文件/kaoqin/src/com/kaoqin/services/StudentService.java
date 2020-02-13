package com.kaoqin.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.kaoqin.DAO.StudentDAO;
import com.kaoqin.entity.Class;
import com.kaoqin.entity.Student;
import com.kaoqin.factory.DAOFactory;
import com.kaoqin.tool.DBUtils;

public class StudentService {

	/**登录*/
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("登陆操作");
		HttpSession session = request.getSession();
//		获取验证码
		String key = request.getParameter("key");
		String code = (String) session.getAttribute("code");
//		不区分大小写
		key = key.toLowerCase();
		code = code.toLowerCase();
		if(!key.equals(code)){
			PrintWriter pw = response.getWriter();
			pw.write("errorkey");
			pw.flush();
			pw.close();
			return;
		}
		
//		获取学号密码
		String sid = request.getParameter("username");
		String password = request.getParameter("password");
//		获取学生
		Student s = DAOFactory.newInstance().getStudentDAO(null).getStudentBySid(sid);
		System.out.println(s);
		PrintWriter pw = response.getWriter();
//		如果学生存在，且密码相同
		if(Objects.nonNull(s) && s.getPassword().equals(password)){
			System.out.println("存在");
//			读取班级名
			Class c = DAOFactory.newInstance().getClassDAO(null).getClassByClassid(s.getClassid());
			String classname = c.getClassname();
//			把班委存入session
			session.setAttribute("me", s);
			session.setAttribute("cname", classname);
			pw.write("true");
			pw.flush();
		}else{
			System.out.println("不存在");
			pw.write("false");
			pw.flush();
		}
		pw.close();
	}
	
	/**删除学生
	 * @throws IOException 
	 * @throws SQLException */
	public void delete(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
//		获取学号
		String sid = request.getParameter("sid");
		Connection conn = DBUtils.getconn();
		boolean f = DAOFactory.newInstance().getStudentDAO(conn).deleteStudentBySid(sid);
		PrintWriter pw = response.getWriter();
		if(f){
			pw.write("删除成功");
		}else{
			pw.write("删除失败");
		}
		pw.flush();
		pw.close();
		conn.close();
	}
	
	/**重置，初始化班内序号*/
	public void initnum(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
		HttpSession session = request.getSession();
		Student me = (Student) session.getAttribute("me");
		System.out.println(me);
		new StudentService().initNumByClassid(me.getClassid());
		System.out.println("重置完成");
		PrintWriter pw = response.getWriter();
		pw.write("重置完成");
		pw.flush();
		pw.close();
	}
	
	/**查看本班班内编号
	 * @throws IOException 
	 * @throws ServletException */
	public void shownum(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//		获取班级id
		HttpSession session = request.getSession();
		Student me = (Student) session.getAttribute("me");
//		获取班级所有学生
		List<Student> allstu = DAOFactory.newInstance().getStudentDAO(null).getAllStuByClassid(me.getClassid());
//		把学生存入session
		session.setAttribute("allstu", allstu);
		PrintWriter pw = response.getWriter();
		pw.write("inclassnum.jsp");
		pw.flush();
		pw.close();
	}
	
	/**批量添加学生（学号    姓名）
	 * @throws SQLException 
	 * @throws IOException */
	public void addStudentByWord(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
//		获取classid
		String classid = request.getParameter("classid");
//		获取专业名
		HttpSession session = request.getSession();
		String subname = (String) session.getAttribute("subname");
		System.out.println("classid:"+classid);
		System.out.println("subname:"+subname);
		
		Connection conn = DBUtils.getconn();
		StudentDAO studentDAO = DAOFactory.newInstance().getStudentDAO(conn);
		String word = request.getParameter("word");
		word = word.replaceAll("\t", " ");
		word = word.replaceAll("-", " ");
		System.out.println(word);
		String[] stulist = word.split("\n");
//		遍历学生
		for (String stu : stulist) {
			String sid = stu.split(" ")[0];
			String sname = stu.split(" ")[1];
			System.out.println("添加学生"+sname);
			Student s = new Student();
			s.setSid(sid);
			s.setSname(sname);
			s.setClassid(classid);
			s.setSubname(subname);
//			添加到数据库
			studentDAO.insert(s);
		}
		conn.close();
		PrintWriter pw = response.getWriter();
		pw.write("成功添加"+stulist.length+"个学生");
		pw.flush();
		pw.close();
	}
	
	/**根据classid获取所有学生JSON对象
	 * @throws IOException */
	public void getStuByClassid(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		从前端取出classid
		String classid = request.getParameter("classid");
		System.out.println(classid);
//		通过dao取出list
		List<Student> list = DAOFactory.newInstance().getStudentDAO(null).getAllStuByClassid(classid);
//		转成json
		String json = JSON.toJSONString(list);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
	}

	/**根据sid修改num
	 * @throws IOException 
	 * @throws SQLException */
	public void updateNumBySid(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
		System.out.println("修改num");
		String sid = request.getParameter("sid");
		String num = request.getParameter("num");
		Connection conn = DBUtils.getconn();
		boolean f = DAOFactory.newInstance().getStudentDAO(conn).updateNumBySid(sid, num);
		System.out.println(f);
		conn.close();
		PrintWriter pw = response.getWriter();
		if(f){
			pw.write("true");			
		}else{
			pw.write("false");
		}
		pw.flush();
		pw.close();
	}

	/**设置权限
	 * @throws IOException 
	 * @throws SQLException */
	public void setlevel(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
//		获取sid
		String sid = request.getParameter("sid");
//		获取level
		int level = Integer.parseInt(request.getParameter("level"));
//		修改权限
		Connection conn = DBUtils.getconn();
		StudentDAO studentDAO = DAOFactory.newInstance().getStudentDAO(conn);
		boolean f = studentDAO.setLevelBySid(sid, level);
//		修改密码
		studentDAO.updatePwdBySid(sid, "123123");
		
		PrintWriter pw = response.getWriter();
		pw.write(""+f);
		pw.flush();
		pw.close();
		conn.close();
	}
	
	/**改密码
	 * @throws IOException */
	public void repassword(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		获取当前用户sid
		HttpSession session = request.getSession();
		Student me = (Student) session.getAttribute("me");
		String sid = me.getSid();
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
		boolean f = DAOFactory.newInstance().getStudentDAO(conn).updatePwdBySid(sid, newpassword);
		if(f){
			pw.write("修改成功");
		}else{
			pw.write("修改失败");
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
	
	/**教师 查看本班班内编号
	 * @throws IOException 
	 * @throws ServletException */
	public void THshownum(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//		获取班级id
		String classid = request.getParameter("classid");
		System.out.println("加载班级id:"+classid);
//		如果班级id为0
		if(classid.equals("0")){
			return;
		}
		HttpSession session = request.getSession();
//		把classid存入session
		session.setAttribute("classid", classid);
//		获取班级所有学生
		List<Student> allstu = DAOFactory.newInstance().getStudentDAO(null).getAllStuByClassid(classid);
//		把学生存入session
		session.setAttribute("allstu", allstu);
		PrintWriter pw = response.getWriter();
		pw.write("th-classnum.jsp");
		pw.flush();
		pw.close();
	}
	
	/**教师 重置，初始化班内序号*/
	public void THinitnum(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
//		获取当前班级
		HttpSession session = request.getSession();
		String classid= (String) session.getAttribute("classid");
		new StudentService().initNumByClassid(classid);
		System.out.println("重置完成");
		PrintWriter pw = response.getWriter();
		pw.write("重置完成");
		pw.flush();
		pw.close();
	}

	/**根据classid初始化本班班内编号
	 * @throws SQLException */
	private void initNumByClassid(String Classid) throws SQLException {
		System.out.println("初始化班级"+Classid+"班内编号");
//		先获取本班所有学生
		List<Student> allstu = DAOFactory.newInstance().getStudentDAO(null).getAllStuByClassid(Classid);
		Connection conn = DBUtils.getconn();
		StudentDAO studentDAO = DAOFactory.newInstance().getStudentDAO(conn);
		for (Student s : allstu) {
			String sid = s.getSid();
//			取学号后两位
			String num = sid.substring(sid.length()-2, sid.length());
//			转成数字，去掉0
			num = ""+Integer.parseInt(num);
			System.out.println("sid:"+sid+" num:"+num);
			studentDAO.updateNumBySid(sid, num);
		}
		conn.close();
	}
}
