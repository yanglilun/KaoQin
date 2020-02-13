package com.kaoqin.DTO;

import java.math.BigDecimal;

/**
 * 按周统计迟到项
 * @author garen
 *
 */
public class WeekLateList{

	private String sname;
	
	private int week;
	
	private BigDecimal time;
	
	public WeekLateList() {
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

	@Override
	public String toString() {
		return "WeekLateList [sname=" + sname + ", week=" + week + ", time=" + time + "]";
	}


}
