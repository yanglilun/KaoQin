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
 * web文件下载工具
 * @author garen
 */
public class FileDownloadTools {

	public static void download(String path,HttpServletResponse response) throws IOException{
//		根据路径获取文件
		File file = new File(path);
		System.out.println("文件存在?:"+file.exists());
		String name = file.getName();
		System.out.println("文件名:"+name);
//		设置文件名编码
		name = URLEncoder.encode(name, "utf-8");
//		输入流
		FileInputStream fis = new FileInputStream(path);
//		输出流
		ServletOutputStream os = response.getOutputStream();
//		设置响应头
		Properties prop = PropUtil.getProp();
		String mode = prop.getProperty("mode");
		response.setHeader("content-disposition", mode+";filename="+name);
//		读取
//		缓存区
		byte temp[] = new byte[1024];
//		长度
		int len;
//		循环读取
		System.out.println("开始传输");
		while((len=fis.read(temp))>0){
//			写入
			os.write(temp,0,len);
		}
		os.close();
		fis.close();
	}
}
