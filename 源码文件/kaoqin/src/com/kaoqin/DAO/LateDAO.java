package com.kaoqin.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.kaoqin.DTO.Kaoqin;
import com.kaoqin.DTO.Latelist;
import com.kaoqin.entity.Late;
import com.kaoqin.factory.DAOFactory;
import com.kaoqin.tool.DBUtils;
import com.kaoqin.tool.DBUtils.CallBack;

/**
 * 考勤表DAO
 * @author garen
 */
public class LateDAO {

	private Connection conn;

	public LateDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	/**插入迟到记录*/
	public boolean insert(Late l) {
		String sql = "insert into late values(?,?,?,?)";
		int i = DBUtils.executeUpdate(conn, sql, l.getSid(),l.getWeek(),l.getDay(),l.getTime());
		return i>0;
	}
	
	/**获取迟到名单（Latelist格式）*/
	public List<Latelist> generateLatelist(List<Kaoqin> kaoqin) {
//		获取迟到人的姓名names(去重)
		List<String> names = new ArrayList<>();
		for (Kaoqin k : kaoqin) {
			names.add(k.getSname());
		}
		names = names.stream().distinct().collect(Collectors.toList());
		
		
		List<Latelist> latelist = new ArrayList<>();
//		有多少人就遍历多少次
		for (String name : names) {
//			遍历kaoqin
			
//			创建latelist对象
			Latelist ll = new Latelist();
			ll.setName(name);
			for (Kaoqin k : kaoqin) {
//				如果是当前学生的
				if(k.getSname().equals(name)){
//					获取当前星期
					int day = k.getDay();
//					switch判断星期
					switch(day){
					case 1:
						ll.setMonday(""+k.getTime());
						break;
					case 2:
						ll.setTuesday(""+k.getTime());
						break;
					case 3:
						ll.setWednesday(""+k.getTime());
						break;
					case 4:
						ll.setThursday(""+k.getTime());
						break;
					case 5:
						ll.setFriday(""+k.getTime());
						break;
					case 6:
						ll.setSaturday(""+k.getTime());
						break;
					case 7:
						ll.setSunday(""+k.getTime());
						break;
					}
//					switch
				}
//				name
			}
			latelist.add(ll);
//			kaoqin
		}
//		allname
		latelist.forEach(e->System.out.println(e));
		return latelist;
	}
	
	/**根据周次查询迟到记录*/
	public List<Kaoqin> getLateByWeekAndClassid(int week , String classid) {
		List<Kaoqin> list = new ArrayList<Kaoqin>();
		
		String sql = "SELECT student.sname,late.day,late.time from late,student "
				+ "where late.sid=student.sid "
				+ "AND late.week=? "
				+ "AND student.classid=?";
		
		DBUtils.query(sql, new CallBack<Kaoqin>() {

			@Override
			public List<Kaoqin> call(ResultSet rs) throws SQLException {
				while(rs.next()){
					String sname = rs.getString("sname");
					int day = rs.getInt("day");
					int time = rs.getInt("time");
					list.add(new Kaoqin(sname, week, day, time));
				}
				return null;
			}
		}, week,classid);
		return list;
	}
	
	/**删除迟到记录*/
	public boolean deleteLateBySidWeekDay(String sid,int week,int day) {
		String sql = "delete from late where sid = ? "
				+ "and week = ? "
				+ "and day = ?";
		int i = DBUtils.executeUpdate(conn, sql, sid,week,day);
		return i>0;
	}
	
}
