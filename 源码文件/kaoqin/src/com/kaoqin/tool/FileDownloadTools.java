package com.kaoqin.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * web�ļ����ع���
 * @author garen
 */
public class FileDownloadTools {

	public static void download(String path,HttpServletResponse response) throws IOException{
//		����·����ȡ�ļ�
		File file = new File(path);
		System.out.println("�ļ�����?:"+file.exists());
		String name = file.getName();
		System.out.println("�ļ���:"+name);
//		�����ļ�������
		name = URLEncoder.encode(name, "utf-8");
//		������
		FileInputStream fis = new FileInputStream(path);
//		�����
		ServletOutputStream os = response.getOutputStream();
//		������Ӧͷ
		Properties prop = PropUtil.getProp();
		String mode = prop.getProperty("mode");
		response.setHeader("content-disposition", mode+";filename="+name);
//		��ȡ
//		������
		byte temp[] = new byte[1024];
//		����
		int len;
//		ѭ����ȡ
		System.out.println("��ʼ����");
		while((len=fis.read(temp))>0){
//			д��
			os.write(temp,0,len);
		}
		os.close();
		fis.close();
	}
}
