package com.kaoqin.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		try {
	//		��ȡִ�еķ�����
			String methodname = arg0.getParameter("method");
			
//			��ȡ����baseservlet��class
			Class clz = this.getClass();
			
//			��ȡ������servlet�ķ���
			Method method = clz.getMethod(methodname, HttpServletRequest.class,HttpServletResponse.class);
			
//			��servletִ�����������
			Object obj = method.invoke(this, arg0,arg1);
			//�жϷ���ִ�к��Ƿ��з���ֵ
			if(Objects.nonNull(obj)){
				//����ȡ�ķ���ֵת��Ϊ�ַ���
				String page = obj.toString();
				if(page.startsWith("redirect:")){
					//�ض���
					page = page.substring("redirect:".length());
					arg1.sendRedirect(page.trim()); 
				}else{
					//����ת��
					arg0.getRequestDispatcher(page.trim()).forward(arg0, arg1);
				}
			}
			 
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			}
		 
	}
   
}
