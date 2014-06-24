package com.zkdsf.register;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import com.google.gson.Gson;
import com.zkdsf.core.ServerInstanceInfo;
import com.zkdsf.core.ZkClient;

public class ServiceInstance {

	private ZooKeeper zk ;
	private Gson gson = new Gson();


	/**
	 * 创建服务实例
	 * 
	 * @param servicename
	 * @param serverInstanceInfo
	 * @throws KeeperException
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public ServiceInstance(String servicename,
			ServerInstanceInfo serverInstanceInfo) throws KeeperException,
			InterruptedException, IOException {
		
		String hosts = serverInstanceInfo.getIp()+":"+serverInstanceInfo.getPort();
		 zk= new ZkClient(hosts,10000,new ServerWatcher()).getZk();
		
		// /com.baidu.serviename/192.168.253.123:8080
		String path = "/" + servicename + "/" + serverInstanceInfo.getIp()
				+ ":" + serverInstanceInfo.getPort();
		zk.create(path, gson.toJson(serverInstanceInfo).getBytes(),
				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}

	class ServerWatcher implements Watcher {
		public void process(WatchedEvent event) {
			if (event.getPath().equals("")) {
				
			}
		}

	}

}
