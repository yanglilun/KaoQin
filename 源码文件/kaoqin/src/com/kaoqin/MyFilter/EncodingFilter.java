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
 * ʵ��Filter�ӿ�
 * ��дinit destroy doFilter����
 * ���ù�������3.0�汾��ǰ��Ҫ��web.xml�����ã�3.0�Ժ�ֱ�Ӽ�ע�⼴��
 * @author garen
 *
 */
@WebFilter(urlPatterns={"/*"},initParams={@WebInitParam(name = "encoding", value = "utf-8")})//�������� /* , *.do , *.action , /static/*
public class EncodingFilter implements Filter{
	String encode="utf-8";
	
	@Override
	public void destroy() {
		System.out.println("����������");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("���������");
//		��������ظ��ı���
		request.setCharacterEncoding(encode);
		response.setCharacterEncoding(encode);
//		��������
		chain.doFilter(request, response);	
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("��������ʼ��");
//		��ȡ����
		encode = config.getInitParameter("encoding");
		System.out.println("��ʼ������"+encode);
		
	}

}
