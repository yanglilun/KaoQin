package com.kaoqin.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaoqin.factory.ServicesFactory;
import com.kaoqin.services.LateService;

/**
 * 考勤接口
 */
@WebServlet("/late")
public class LateServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	/**录入迟到*/
    public void luru(HttpServletRequest request,HttpServletResponse response){
    	System.out.println("录入操作");
    	try {
    		ServicesFactory.newinstance().getLateService().insert(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    /**加载迟到列表*/
    public void load(HttpServletRequest request,HttpServletResponse response) {
    	System.out.println("加载迟到列表");
		try {
			ServicesFactory.newinstance().getLateService().loadLatelistByWeek(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    /**教师加载迟到列表*/
    public void THload(HttpServletRequest request,HttpServletResponse response) {
    	System.out.println("加载迟到列表");
		try {
			ServicesFactory.newinstance().getLateService().loadLatelistByWeekClassid(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
