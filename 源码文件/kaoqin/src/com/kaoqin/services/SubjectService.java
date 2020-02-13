package com.kaoqin.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaoqin.entity.Grade;
import com.kaoqin.entity.Subject;
import com.kaoqin.entity.Teacher;
import com.kaoqin.factory.DAOFactory;
import com.sun.org.apache.bcel.internal.generic.FALOAD;

/**
 * רҵservice
 * @author garen
 *
 */
public class SubjectService {

	/**��������רҵ
	 * @throws IOException */
	public void loadSubject(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		��ȡ����ʦ��gid
		HttpSession session = request.getSession();
		Teacher me = (Teacher) session.getAttribute("me");
		String gid = me.getGid();
		List<Subject> allsub = DAOFactory.newInstance().getSubjectDAO(null).getSubjectByGid(gid);
//		������רҵ����session
		session.setAttribute("allsub", allsub);
//		�ѱ�Ժ���ִ���session
		Grade grade = DAOFactory.newInstance().getGradeDAO(null).getGradeByGid(me.getGid());
		session.setAttribute("gname", grade.getGname());
		PrintWriter pw = response.getWriter();
		pw.write("����רҵ");
		pw.flush();
		pw.close();
	}
}
