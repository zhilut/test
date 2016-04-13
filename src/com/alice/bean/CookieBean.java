package com.alice.bean;

import org.genericdao.PrimaryKey;

@PrimaryKey("gid")
public class CookieBean {
	private long gid;
	private long hid;
	private String domain;

	public long getGid() {
		return gid;
	}

	public void setGid(long gid) {
		this.gid = gid;
	}

	public long getHid() {
		return hid;
	}

	public void setHid(long hid) {
		this.hid = hid;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}
