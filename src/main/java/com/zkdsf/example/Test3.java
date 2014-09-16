package com.zkdsf.example;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;

import com.zkdsf.core.ServerInstanceInfo;
import com.zkdsf.core.ServiceDefineInfo;
import com.zkdsf.core.SubscribeInfo;
import com.zkdsf.register.RegeisterClient;
import com.zkdsf.register.Service;
import com.zkdsf.register.ServiceInstance;

public class Test3 {

	public static void main(String[] args) {
		
//		ServiceInfo serviceInfo = new ServiceInfo();
//		serviceInfo.setServicename("test");
//		serviceInfo.setTimeout(1000);
//		serviceInfo.setProtol("round");
//		try {
//			Service service = new Service("localhost:2181",serviceInfo);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (KeeperException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
		
		ServerInstanceInfo serviceInstanceInfo = new ServerInstanceInfo();
		serviceInstanceInfo.setIp("10.1.1.1");
		serviceInstanceInfo.setPort(8080);
		
		try {
			ServiceInstance serviceInstance = new ServiceInstance("localhost:2181","test",serviceInstanceInfo);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SubscribeInfo subscribeInfo = new SubscribeInfo();
		subscribeInfo.setServicename("test");
		subscribeInfo.setRegisterclienthostname("client");
		subscribeInfo.setIp("10.1.11.156");
		try {
			RegeisterClient regeisterClient = new RegeisterClient("localhost:2181", subscribeInfo);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
