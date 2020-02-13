package com.kaoqin.DAO;

import java.util.Comparator;
import java.util.List;

import com.kaoqin.DTO.WeekLateList;
import com.kaoqin.tool.DBUtils;

/**
 * �ٵ�-�����ܴ�DAO�����
 * @author garen
 */
public class wllDAO {
	
	/**��ȡ��n�ܵĳٵ��б�*/
	public List<WeekLateList> getWllByWeek(int n) {
		String sql = "select student.sname,late.`week`,SUM(late.time) as time from student,late "
				+ "WHERE (student.subname =  convert('�����Ӧ�ü���' using gbk)) "
				+ "AND student.sid = late.sid "
				+ "AND late.`week`=? "
				+ "GROUP BY student.sname";
		List<WeekLateList> list = DBUtils.queryList(WeekLateList.class, sql, n);
		return list;
	}
	
	/**n-m�ܵĳٵ��б�*/
	public List<WeekLateList> getWllByWeek(int n,int m,String subname) {
		StringBuffer sql = new StringBuffer();
		for (; n<=m; n++) {
		 sql.append("select student.sname,late.`week`,SUM(late.time) as time from student,late "
				+ "WHERE (student.subname =  convert('"+subname+"' using gbk)) "
				+ "AND student.sid = late.sid "
				+ "AND late.`week`= "+n
				+ " GROUP BY student.sname");
		 if(n<m){
			 sql.append(" UNION ");
			 
		 }
		}
		List<WeekLateList> list = DBUtils.queryList(WeekLateList.class, sql.toString());
//		����
		list.sort(new Comparator<WeekLateList>() {

			@Override
			public int compare(WeekLateList o1, WeekLateList o2) {
				return o2.getSname().compareTo(o1.getSname());
			}
		});
		return list;
	}
	
}
