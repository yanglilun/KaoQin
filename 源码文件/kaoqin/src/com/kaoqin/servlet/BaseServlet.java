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
	//		获取执行的方法名
			String methodname = arg0.getParameter("method");
			
//			获取调用baseservlet的class
			Class clz = this.getClass();
			
//			获取被调用servlet的方法
			Method method = clz.getMethod(methodname, HttpServletRequest.class,HttpServletResponse.class);
			
//			让servlet执行他这个方法
			Object obj = method.invoke(this, arg0,arg1);
			//判断方法执行后是否有返回值
			if(Objects.nonNull(obj)){
				//将获取的返回值转换为字符串
				String page = obj.toString();
				if(page.startsWith("redirect:")){
					//重定向
					page = page.substring("redirect:".length());
					arg1.sendRedirect(page.trim()); 
				}else{
					//请求转发
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
