package com.kaoqin.DAO;

import java.util.Comparator;
import java.util.List;

import com.kaoqin.DTO.WeekLateList;
import com.kaoqin.tool.DBUtils;

/**
 * 迟到-根据周次DAO（虚表）
 * @author garen
 */
public class wllDAO {
	
	/**获取第n周的迟到列表*/
	public List<WeekLateList> getWllByWeek(int n) {
		String sql = "select student.sname,late.`week`,SUM(late.time) as time from student,late "
				+ "WHERE (student.subname =  convert('计算机应用技术' using gbk)) "
				+ "AND student.sid = late.sid "
				+ "AND late.`week`=? "
				+ "GROUP BY student.sname";
		List<WeekLateList> list = DBUtils.queryList(WeekLateList.class, sql, n);
		return list;
	}
	
	/**n-m周的迟到列表*/
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
//		排序
		list.sort(new Comparator<WeekLateList>() {

			@Override
			public int compare(WeekLateList o1, WeekLateList o2) {
				return o2.getSname().compareTo(o1.getSname());
			}
		});
		return list;
	}
	
}
