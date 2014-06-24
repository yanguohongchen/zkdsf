package com.zkdsf.core;

public class ServerInstanceInfo {

	private String ip;
	
	private int port;

	private String state;

	private int weight_value;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getWeight_value() {
		return weight_value;
	}

	public void setWeight_value(int weight_value) {
		this.weight_value = weight_value;
	}
	
	
}
