package com.kaoqin.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaoqin.factory.ServicesFactory;
import com.kaoqin.services.SubjectService;

/**
 * רҵ�ӿ�
 */
@WebServlet("/sub")
public class SubjectServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	/**��������רҵ*/
	public void loadSubject(HttpServletRequest request, HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getSubjebtService().loadSubject(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
