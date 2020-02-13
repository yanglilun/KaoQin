package com.kaoqin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaoqin.factory.ServicesFactory;

/**
 * 学生接口
 */
@WebServlet("/stu")
public class StudentServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
    
	/**登陆操作
	 * @throws IOException */
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ServicesFactory.newinstance().getStudentService().login(request, response);
	}
	
	/**查看学生班内编号 */
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
	
	/**根据classid获取所有学生*/
	public void getStuByClassid(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getStudentService().getStuByClassid(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**修改学生班内编号 */
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
	
	/**初始化、重置班内编号 */
	public void initnum(HttpServletRequest request,HttpServletResponse response){
		try {
			ServicesFactory.newinstance().getStudentService().initnum(request, response);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**修改密码*/
	public void repassword(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getStudentService().repassword(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**设置权限*/
	public void setlevel(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getStudentService().setlevel(request, response);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**退出登录*/
	public void logout(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getStudentService().logout(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**删除学生*/
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
		
	/**教师查看某班编号*/
	public void THshownum(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		ServicesFactory.newinstance().getStudentService().THshownum(request, response);
	}
	
	/**教师初始化、重置班内编号 */
	public void THinitnum(HttpServletRequest request,HttpServletResponse response){
		try {
			System.out.println("教师重置班内编号");
			ServicesFactory.newinstance().getStudentService().THinitnum(request, response);
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**批量添加学生*/
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
