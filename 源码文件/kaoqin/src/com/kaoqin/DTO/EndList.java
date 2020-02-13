package com.kaoqin.DTO;

import java.math.BigDecimal;

/**
 * 总表项对象
 * @author garen
 *
 */
public class EndList {

	private String sname;
	
	private int week;
	
	private BigDecimal time;
	
	private int alltime;
	
	private int num;
	
	
	public EndList() {
		// TODO Auto-generated constructor stub
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


	public BigDecimal getTime() {
		return time;
	}


	public void setTime(BigDecimal time) {
		this.time = time;
	}


	public int getAlltime() {
		return alltime;
	}


	public void setAlltime(int alltime) {
		this.alltime = alltime;
	}

	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	@Override
	public String toString() {
		return "EndList [sname=" + sname + ", week=" + week + ", time=" + time + ", alltime=" + alltime + ", num=" + num
				+ "]";
	}

	
	
}
