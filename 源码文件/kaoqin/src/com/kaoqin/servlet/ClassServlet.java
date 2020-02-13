package com.kaoqin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaoqin.factory.ServicesFactory;
import com.kaoqin.services.ClassService;

/**
 * �༶�ӿ�
 */
@WebServlet("/class")
public class ClassServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	/**��Ӱ༶*/
	public void addClass(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getClassService().addClass(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**�鿴�༶*/
	public void showclass(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getClassService().showclass(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
