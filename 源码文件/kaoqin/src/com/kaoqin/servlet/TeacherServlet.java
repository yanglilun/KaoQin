package com.kaoqin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaoqin.factory.ServicesFactory;


/**
 * 教师接口
 *
 */
@WebServlet("/th")
public class TeacherServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	/**进入登陆页面*/
	public void jump(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.getRequestDispatcher("admin.html").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**登陆操作
	 * @throws IOException */
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ServicesFactory.newinstance().getTeacherService().login(request, response);
	}
	
	/**修改密码*/
	public void repassword(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getTeacherService().repassword(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**注销*/
	public void logout(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getTeacherService().logout(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**生成最终表*/
	public void generateEnd(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("生成最终表");
		try {
			ServicesFactory.newinstance().getTeacherService().generateEnd(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**下载endlist*/
	public void download(HttpServletRequest request , HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getTeacherService().download(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
