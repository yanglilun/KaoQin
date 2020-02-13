package com.kaoqin.MyFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 实现Filter接口
 * 重写init destroy doFilter方法
 * 配置过滤器，3.0版本以前需要在web.xml中配置，3.0以后直接加注解即可
 * @author garen
 *
 */
@WebFilter(urlPatterns={"/*"},initParams={@WebInitParam(name = "encoding", value = "utf-8")})//参数规则 /* , *.do , *.action , /static/*
public class EncodingFilter implements Filter{
	String encode="utf-8";
	
	@Override
	public void destroy() {
		System.out.println("过滤器销毁");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("进入过滤器");
//		设置请求回复的编码
		request.setCharacterEncoding(encode);
		response.setCharacterEncoding(encode);
//		放行请求
		chain.doFilter(request, response);	
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("过滤器初始化");
//		获取编码
		encode = config.getInitParameter("encoding");
		System.out.println("初始化编码"+encode);
		
	}

}
