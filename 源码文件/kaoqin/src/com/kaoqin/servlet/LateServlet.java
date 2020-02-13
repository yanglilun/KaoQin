package com.kaoqin.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kaoqin.factory.ServicesFactory;
import com.kaoqin.services.LateService;

/**
 * ���ڽӿ�
 */
@WebServlet("/late")
public class LateServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	/**¼��ٵ�*/
    public void luru(HttpServletRequest request,HttpServletResponse response){
    	System.out.println("¼�����");
    	try {
    		ServicesFactory.newinstance().getLateService().insert(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    /**���سٵ��б�*/
    public void load(HttpServletRequest request,HttpServletResponse response) {
    	System.out.println("���سٵ��б�");
		try {
			ServicesFactory.newinstance().getLateService().loadLatelistByWeek(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    /**��ʦ���سٵ��б�*/
    public void THload(HttpServletRequest request,HttpServletResponse response) {
    	System.out.println("���سٵ��б�");
		try {
			ServicesFactory.newinstance().getLateService().loadLatelistByWeekClassid(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
