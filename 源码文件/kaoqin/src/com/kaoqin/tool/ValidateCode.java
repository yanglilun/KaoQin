package com.kaoqin.tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mrchai
 */
@WebServlet("/ValidateCode")
public class ValidateCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //生成验证码的基本字符序列
	private static final String CODES = "abcdefghijkmnpqrstuvwxyz23456789ABCDEFGHIJKLMNPQRSTUVWXYZ";
	//初始化验证码的宽度与高度
	private static final int WIDTH = 100;
	private static final int HEIGHT = 40;
	//初始化验证码字符数
	private static final int CODE_COUNT = 4;
	//初始化噪点比例
	private static final double POINTS = 0.05;
	//干扰线的条数
	private static final int LINE_COUNT = 10;
	
	private Random random = new Random();
	
    public ValidateCode() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	//完成验证码的生成过程
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.生成验证码的字符
		
		//2.生成图片
		BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		//获取画笔
		Graphics g = img.getGraphics();
		//填充可绘制区域
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//创建字体
		Font font = new Font("黑体", Font.BOLD, 25);
		//设置字体
		g.setFont(font);
		
		//声明缓冲字符串对象，存储生成的验证码
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < CODE_COUNT; i++) {
			//设置画笔颜色
			g.setColor(genColor());
			//生成一个字符
			char c = genCode();
			//绘制字符到图像中
			g.drawString(c+"", i*20+5, 25);
			//拼接到字符串中
			buff.append(c);
		}
		
		//添加噪点
		int area = (int)(WIDTH * HEIGHT * POINTS);
		for (int i = 0; i < area; i++) {
			g.setColor(genColor());
			int x = random.nextInt(WIDTH);
			int y = random.nextInt(HEIGHT);
			img.setRGB(x, y, genColor().getRGB());
		}
		
		//添加干扰线
		for (int i = 0; i < LINE_COUNT; i++) {
			//随机设置画笔颜色
			g.setColor(genColor());
			//随机线段起始，结束位置
			int xstart = random.nextInt(WIDTH);
			int ystart = random.nextInt(HEIGHT);
			int xend = random.nextInt(WIDTH);
			int yend = random.nextInt(HEIGHT);
			//绘制线段
			g.drawLine(xstart, ystart, xend, yend);
		}
		
		//验证码缓存到session中
		request.getSession().setAttribute("code", buff.toString());
		System.out.println("验证码:"+buff.toString());
		//3.通过输出流输出
		//设置响应数据的类型
		response.setContentType("image/jpeg");//MIME-Type
		//清除缓存（禁止浏览器缓存）
		response.setHeader("pregam", "No-cache");
		response.setHeader("cache-control", "No-cache");
		response.setDateHeader("expire", 0);		
		//向页面端输出
		ImageIO.write(img, "JPG", response.getOutputStream());
	}
	
	//随机生成颜色
	private Color genColor(){
		return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	}
	
	//随机生成一个字符
	private char genCode(){
		return CODES.charAt(random.nextInt(CODES.length()));
	}
	

}
