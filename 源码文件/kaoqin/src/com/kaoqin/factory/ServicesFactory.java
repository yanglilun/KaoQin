package com.kaoqin.factory;

import com.kaoqin.services.ApplyService;
import com.kaoqin.services.ClassService;
import com.kaoqin.services.LateService;
import com.kaoqin.services.StudentService;
import com.kaoqin.services.SubjectService;
import com.kaoqin.services.TeacherService;

/**
 * service¹¤³§
 * @author garen
 */
public class ServicesFactory {

	private static ServicesFactory servicefactory;
	
	public static ServicesFactory newinstance() {
		if(servicefactory == null){
			servicefactory = new ServicesFactory();
		}
		return servicefactory;
	}
	
	public ApplyService getApplyService() {
		return new ApplyService();
	}
	
	public ClassService getClassService() {
		return new ClassService();
	}
	
	public LateService getLateService() {
		return new LateService();
	}
	
	public StudentService getStudentService() {
		return new StudentService();
	}
	
	public SubjectService getSubjebtService() {
		return new SubjectService();
	}
	
	public TeacherService getTeacherService() {
		return new TeacherService();
	}
	
}
