package com.kaoqin.entity;/******************************************************************************* * javaBeans * teacher --> Teacher  * <table explanation> * @author 2019-09-10 16:05:23 *  */	public class Teacher implements java.io.Serializable {	//field	/**  **/	private String tid;	/**  **/	private String tname;	/**  **/	private String password;	/**  **/	private String subid;	/**  **/	private String gid;	//method	public String getTid() {		return tid;	}	public void setTid(String tid) {		this.tid = tid;	}	public String getTname() {		return tname;	}	public void setTname(String tname) {		this.tname = tname;	}	public String getPassword() {		return password;	}	public void setPassword(String password) {		this.password = password;	}	public String getSubid() {		return subid;	}	public void setSubid(String subid) {		this.subid = subid;	}	public String getGid() {		return gid;	}	public void setGid(String gid) {		this.gid = gid;	}	//override toString Method 	public String toString() {		StringBuffer sb=new StringBuffer();		sb.append("{");		sb.append("'tid':'"+this.getTid()+"',");		sb.append("'tname':'"+this.getTname()+"',");		sb.append("'password':'"+this.getPassword()+"',");		sb.append("'subid':'"+this.getSubid()+"',");		sb.append("'gid':'"+this.getGid()+"'");		sb.append("}");		return sb.toString();	}}