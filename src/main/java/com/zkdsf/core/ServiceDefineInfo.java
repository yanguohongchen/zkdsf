package com.zkdsf.core;

public class ServiceDefineInfo {

	private String servicename;

	private String description;

	private String protol;

	private String routestrage;

	private String failstrage;

	private int timeout;

	private String version;

	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProtol() {
		return protol;
	}

	public void setProtol(String protol) {
		this.protol = protol;
	}

	public String getRoutestrage() {
		return routestrage;
	}

	public void setRoutestrage(String routestrage) {
		this.routestrage = routestrage;
	}

	public String getFailstrage() {
		return failstrage;
	}

	public void setFailstrage(String failstrage) {
		this.failstrage = failstrage;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
