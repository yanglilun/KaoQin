package com.kaoqin.DTO;

/**
 * 多表查询考勤结果对象
 * 根据周次查找出来
 * sname week day time
 * @author garen
 */
public class Kaoqin {

	private String sname;
	private int week;
	private int day;
	private int time;
	
	
	
	public Kaoqin(String sname, int week, int day, int time) {
		super();
		this.sname = sname;
		this.week = week;
		this.day = day;
		this.time = time;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Kaoqin [sname=" + sname + ", week=" + week + ", day=" + day + ", time=" + time + "]";
	}
	
	
	
}
