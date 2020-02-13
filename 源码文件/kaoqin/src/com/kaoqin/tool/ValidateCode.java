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
    //������֤��Ļ����ַ�����
	private static final String CODES = "abcdefghijkmnpqrstuvwxyz23456789ABCDEFGHIJKLMNPQRSTUVWXYZ";
	//��ʼ����֤��Ŀ����߶�
	private static final int WIDTH = 100;
	private static final int HEIGHT = 40;
	//��ʼ����֤���ַ���
	private static final int CODE_COUNT = 4;
	//��ʼ��������
	private static final double POINTS = 0.05;
	//�����ߵ�����
	private static final int LINE_COUNT = 10;
	
	private Random random = new Random();
	
    public ValidateCode() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	//�����֤������ɹ���
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.������֤����ַ�
		
		//2.����ͼƬ
		BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		//��ȡ����
		Graphics g = img.getGraphics();
		//���ɻ�������
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		//��������
		Font font = new Font("����", Font.BOLD, 25);
		//��������
		g.setFont(font);
		
		//���������ַ������󣬴洢���ɵ���֤��
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < CODE_COUNT; i++) {
			//���û�����ɫ
			g.setColor(genColor());
			//����һ���ַ�
			char c = genCode();
			//�����ַ���ͼ����
			g.drawString(c+"", i*20+5, 25);
			//ƴ�ӵ��ַ�����
			buff.append(c);
		}
		
		//������
		int area = (int)(WIDTH * HEIGHT * POINTS);
		for (int i = 0; i < area; i++) {
			g.setColor(genColor());
			int x = random.nextInt(WIDTH);
			int y = random.nextInt(HEIGHT);
			img.setRGB(x, y, genColor().getRGB());
		}
		
		//��Ӹ�����
		for (int i = 0; i < LINE_COUNT; i++) {
			//������û�����ɫ
			g.setColor(genColor());
			//����߶���ʼ������λ��
			int xstart = random.nextInt(WIDTH);
			int ystart = random.nextInt(HEIGHT);
			int xend = random.nextInt(WIDTH);
			int yend = random.nextInt(HEIGHT);
			//�����߶�
			g.drawLine(xstart, ystart, xend, yend);
		}
		
		//��֤�뻺�浽session��
		request.getSession().setAttribute("code", buff.toString());
		System.out.println("��֤��:"+buff.toString());
		//3.ͨ����������
		//������Ӧ���ݵ�����
		response.setContentType("image/jpeg");//MIME-Type
		//������棨��ֹ��������棩
		response.setHeader("pregam", "No-cache");
		response.setHeader("cache-control", "No-cache");
		response.setDateHeader("expire", 0);		
		//��ҳ������
		ImageIO.write(img, "JPG", response.getOutputStream());
	}
	
	//���������ɫ
	private Color genColor(){
		return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	}
	
	//�������һ���ַ�
	private char genCode(){
		return CODES.charAt(random.nextInt(CODES.length()));
	}
	

}
