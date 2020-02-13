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

	/**��¼*/
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		System.out.println("��½����");
		HttpSession session = request.getSession();
//		��ȡ��֤��
		String key = request.getParameter("key");
		String code = (String) session.getAttribute("code");
//		�����ִ�Сд
		key = key.toLowerCase();
		code = code.toLowerCase();
		if(!key.equals(code)){
			PrintWriter pw = response.getWriter();
			pw.write("errorkey");
			pw.flush();
			pw.close();
			return;
		}
		
//		��ȡѧ������
		String sid = request.getParameter("username");
		String password = request.getParameter("password");
//		��ȡѧ��
		Student s = DAOFactory.newInstance().getStudentDAO(null).getStudentBySid(sid);
		System.out.println(s);
		PrintWriter pw = response.getWriter();
//		���ѧ�����ڣ���������ͬ
		if(Objects.nonNull(s) && s.getPassword().equals(password)){
			System.out.println("����");
//			��ȡ�༶��
			Class c = DAOFactory.newInstance().getClassDAO(null).getClassByClassid(s.getClassid());
			String classname = c.getClassname();
//			�Ѱ�ί����session
			session.setAttribute("me", s);
			session.setAttribute("cname", classname);
			pw.write("true");
			pw.flush();
		}else{
			System.out.println("������");
			pw.write("false");
			pw.flush();
		}
		pw.close();
	}
	
	/**ɾ��ѧ��
	 * @throws IOException 
	 * @throws SQLException */
	public void delete(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
//		��ȡѧ��
		String sid = request.getParameter("sid");
		Connection conn = DBUtils.getconn();
		boolean f = DAOFactory.newInstance().getStudentDAO(conn).deleteStudentBySid(sid);
		PrintWriter pw = response.getWriter();
		if(f){
			pw.write("ɾ���ɹ�");
		}else{
			pw.write("ɾ��ʧ��");
		}
		pw.flush();
		pw.close();
		conn.close();
	}
	
	/**���ã���ʼ���������*/
	public void initnum(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
		HttpSession session = request.getSession();
		Student me = (Student) session.getAttribute("me");
		System.out.println(me);
		new StudentService().initNumByClassid(me.getClassid());
		System.out.println("�������");
		PrintWriter pw = response.getWriter();
		pw.write("�������");
		pw.flush();
		pw.close();
	}
	
	/**�鿴������ڱ��
	 * @throws IOException 
	 * @throws ServletException */
	public void shownum(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//		��ȡ�༶id
		HttpSession session = request.getSession();
		Student me = (Student) session.getAttribute("me");
//		��ȡ�༶����ѧ��
		List<Student> allstu = DAOFactory.newInstance().getStudentDAO(null).getAllStuByClassid(me.getClassid());
//		��ѧ������session
		session.setAttribute("allstu", allstu);
		PrintWriter pw = response.getWriter();
		pw.write("inclassnum.jsp");
		pw.flush();
		pw.close();
	}
	
	/**�������ѧ����ѧ��    ������
	 * @throws SQLException 
	 * @throws IOException */
	public void addStudentByWord(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
//		��ȡclassid
		String classid = request.getParameter("classid");
//		��ȡרҵ��
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
//		����ѧ��
		for (String stu : stulist) {
			String sid = stu.split(" ")[0];
			String sname = stu.split(" ")[1];
			System.out.println("���ѧ��"+sname);
			Student s = new Student();
			s.setSid(sid);
			s.setSname(sname);
			s.setClassid(classid);
			s.setSubname(subname);
//			��ӵ����ݿ�
			studentDAO.insert(s);
		}
		conn.close();
		PrintWriter pw = response.getWriter();
		pw.write("�ɹ����"+stulist.length+"��ѧ��");
		pw.flush();
		pw.close();
	}
	
	/**����classid��ȡ����ѧ��JSON����
	 * @throws IOException */
	public void getStuByClassid(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		��ǰ��ȡ��classid
		String classid = request.getParameter("classid");
		System.out.println(classid);
//		ͨ��daoȡ��list
		List<Student> list = DAOFactory.newInstance().getStudentDAO(null).getAllStuByClassid(classid);
//		ת��json
		String json = JSON.toJSONString(list);
		PrintWriter pw = response.getWriter();
		pw.write(json);
		pw.flush();
		pw.close();
	}

	/**����sid�޸�num
	 * @throws IOException 
	 * @throws SQLException */
	public void updateNumBySid(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
		System.out.println("�޸�num");
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

	/**����Ȩ��
	 * @throws IOException 
	 * @throws SQLException */
	public void setlevel(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
//		��ȡsid
		String sid = request.getParameter("sid");
//		��ȡlevel
		int level = Integer.parseInt(request.getParameter("level"));
//		�޸�Ȩ��
		Connection conn = DBUtils.getconn();
		StudentDAO studentDAO = DAOFactory.newInstance().getStudentDAO(conn);
		boolean f = studentDAO.setLevelBySid(sid, level);
//		�޸�����
		studentDAO.updatePwdBySid(sid, "123123");
		
		PrintWriter pw = response.getWriter();
		pw.write(""+f);
		pw.flush();
		pw.close();
		conn.close();
	}
	
	/**������
	 * @throws IOException */
	public void repassword(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		��ȡ��ǰ�û�sid
		HttpSession session = request.getSession();
		Student me = (Student) session.getAttribute("me");
		String sid = me.getSid();
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
		boolean f = DAOFactory.newInstance().getStudentDAO(conn).updatePwdBySid(sid, newpassword);
		if(f){
			pw.write("�޸ĳɹ�");
		}else{
			pw.write("�޸�ʧ��");
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
	
	/**��ʦ �鿴������ڱ��
	 * @throws IOException 
	 * @throws ServletException */
	public void THshownum(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//		��ȡ�༶id
		String classid = request.getParameter("classid");
		System.out.println("���ذ༶id:"+classid);
//		����༶idΪ0
		if(classid.equals("0")){
			return;
		}
		HttpSession session = request.getSession();
//		��classid����session
		session.setAttribute("classid", classid);
//		��ȡ�༶����ѧ��
		List<Student> allstu = DAOFactory.newInstance().getStudentDAO(null).getAllStuByClassid(classid);
//		��ѧ������session
		session.setAttribute("allstu", allstu);
		PrintWriter pw = response.getWriter();
		pw.write("th-classnum.jsp");
		pw.flush();
		pw.close();
	}
	
	/**��ʦ ���ã���ʼ���������*/
	public void THinitnum(HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException {
//		��ȡ��ǰ�༶
		HttpSession session = request.getSession();
		String classid= (String) session.getAttribute("classid");
		new StudentService().initNumByClassid(classid);
		System.out.println("�������");
		PrintWriter pw = response.getWriter();
		pw.write("�������");
		pw.flush();
		pw.close();
	}

	/**����classid��ʼ��������ڱ��
	 * @throws SQLException */
	private void initNumByClassid(String Classid) throws SQLException {
		System.out.println("��ʼ���༶"+Classid+"���ڱ��");
//		�Ȼ�ȡ��������ѧ��
		List<Student> allstu = DAOFactory.newInstance().getStudentDAO(null).getAllStuByClassid(Classid);
		Connection conn = DBUtils.getconn();
		StudentDAO studentDAO = DAOFactory.newInstance().getStudentDAO(conn);
		for (Student s : allstu) {
			String sid = s.getSid();
//			ȡѧ�ź���λ
			String num = sid.substring(sid.length()-2, sid.length());
//			ת�����֣�ȥ��0
			num = ""+Integer.parseInt(num);
			System.out.println("sid:"+sid+" num:"+num);
			studentDAO.updateNumBySid(sid, num);
		}
		conn.close();
	}
}
