package com.joysus.entity;

import java.io.Serializable;

/**
 * 排行榜
 * 
 * @author howso
 * 
 */
public class TripOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * "uid": 185, "headPicPath":
	 * "/Uploads/Picture/2015-09-14/55f66fef065ff.jpg", "pdesc":
	 * "234发的冯绍峰是打发沙发sadf是打发发放给对方个地方官的风格的风格的风格的风格忙什么呢蜜", "prices": 5003,
	 * "username": "11111"
	 */
	private int uid;
	private String headPicPath;
	private String pdesc;
	private double prices;
	private String username;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getHeadPicPath() {
		return headPicPath;
	}

	public void setHeadPicPath(String headPicPath) {
		this.headPicPath = headPicPath;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public double getPrices() {
		return prices;
	}

	public void setPrices(double prices) {
		this.prices = prices;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
