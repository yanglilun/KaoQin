 
package com.kaoqin.tool;
/**
 * ͳ�Ƶ�ǰ����Ŀ¼������.java�ļ���������
 */
 
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
 
public class CountLine {
	protected File root;						// FileFinder�Ĳ��ҷ�Χ
	protected int lineCounter;					// ����������
	public void Dir(String rootname)			// ����Ŀ¼���Ӹ�Ŀ¼��ʼ����
	{
		if (rootname.charAt(rootname.length()-1)!=File.separatorChar)
		{
			rootname = rootname+File.separator;	// �����β���Ƿָ����������Ϸָ���
		}
		root = new File(rootname);				// ��ʼ��root����
	}
	public void countLine()						// ͳ�Ƹ�Ŀ¼������.java�ļ���������
	{
		lineCounter = 0;						// ��������������
		recurrent(root);						// �г���Ŀ¼������Ŀ¼�������.java��ͳ������
	}
	public int getLines()						// ��ȡ����ͳ�ƽ��
	{
		return lineCounter;
	}
	protected void recurrent(File root)			// �����Ŀ¼���ݹ������Ŀ¼�¸�����Ŀ¼�����ļ�
	{								
		if (root != null)						// ���Ŀ¼�ǿ�
		{
			String sroot = root.getAbsolutePath();	// root�ļ��ľ���·��
			String[] tmp = sroot.split("\\\\");		// ��������ʽ�ֿ�����·��
			// getAbsolutePath�������ļ�·���ַ���������\\��ʾ\��,\\��ת���ַ�����\\\\
			String filename = tmp[tmp.length-1];	// ȡ���һ�����ļ���
			if (root.isDirectory())					// �����Ŀ¼���ļ���
			{
				File[] fileArray = root.listFiles();// ������fileArray�����Ŀ¼�������ļ�/�ļ���
				for (File fa : fileArray)			// �����������ļ�/��Ŀ¼	
				{
					recurrent(fa);					// �ݹ����recurrent����
				}
			}
			else									// �����Ŀ¼���ļ�
			{
				if (filename.contains(".java"))
				{
					try {
						BufferedReader fin = new BufferedReader(new FileReader(root));
						while (fin.readLine() != null)		// ���������β
						{
							lineCounter++;
						}
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				System.out.println(filename);		// ����ļ���
			}
		}
	}
	
	public static void main(String args[])
	{
		CountLine cl = new CountLine();
		cl.Dir("D:\\javacode\\kaoqin\\src");	// ���̴���·��
		cl.countLine();												// ����.java����������
		System.out.println(cl.getLines());							// �������������Ļ
	}
}