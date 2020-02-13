package com.kaoqin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaoqin.factory.ServicesFactory;
import com.kaoqin.services.ApplyService;

/**
 * ≤π«©Ω”ø⁄
 */
@WebServlet("/apply")
@MultipartConfig
public class ApplyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	/**Ã·Ωª…Í«Î*/
	public void apply(HttpServletRequest request,HttpServletResponse response) {
	  try {
		ServicesFactory.newinstance().getApplyService().apply(request, response);
	} catch (IOException | ServletException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	/**±£¥Ê…Í«Îid*/
	public void saveaid(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getApplyService().saveaid(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**…Û∫À…Í«Îid*/
	public void checkApply(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getApplyService().checkApply(request, response);
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**≤Èø¥…Í«Î*/
	public void showApply(HttpServletRequest request,HttpServletResponse response) {
		try {
			ServicesFactory.newinstance().getApplyService().showApply(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
