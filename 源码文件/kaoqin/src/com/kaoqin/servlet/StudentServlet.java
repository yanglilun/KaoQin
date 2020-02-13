package com.kaoqin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaoqin.factory.ServicesFactory;

/**
 * ѧ���ӿ�
 */
@WebServlet("/stu")
public class StudentServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
    
	/**��½����
	 * @throws IOException */
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ServicesFactory.newinstance().getStudentService().login(request, response);
	}
	
	/**�鿴ѧ�����ڱ�� */
	public void shownum(HttpServletRequest request,HttpServletResponse response){
		try {
			ServicesFactory.newinstance().getStudentService().shownum(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**����classid��ȡ����ѧ��*/
	public void getStuByClassid(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getStudentService().getStuByClassid(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**�޸�ѧ�����ڱ�� */
	public void updatenum(HttpServletRequest request,HttpServletResponse response){
		try {
			ServicesFactory.newinstance().getStudentService().updateNumBySid(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**��ʼ�������ð��ڱ�� */
	public void initnum(HttpServletRequest request,HttpServletResponse response){
		try {
			ServicesFactory.newinstance().getStudentService().initnum(request, response);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**�޸�����*/
	public void repassword(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getStudentService().repassword(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**����Ȩ��*/
	public void setlevel(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getStudentService().setlevel(request, response);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**�˳���¼*/
	public void logout(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getStudentService().logout(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**ɾ��ѧ��*/
	public void delete(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getStudentService().delete(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	/**��ʦ�鿴ĳ����*/
	public void THshownum(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		ServicesFactory.newinstance().getStudentService().THshownum(request, response);
	}
	
	/**��ʦ��ʼ�������ð��ڱ�� */
	public void THinitnum(HttpServletRequest request,HttpServletResponse response){
		try {
			System.out.println("��ʦ���ð��ڱ��");
			ServicesFactory.newinstance().getStudentService().THinitnum(request, response);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**�������ѧ��*/
	public void addStudentByWord(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getStudentService().addStudentByWord(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
