package com.zkdsf.register;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

import com.google.gson.Gson;
import com.zkdsf.core.ServiceDefineInfo;
import com.zkdsf.core.ZkClient;

public class Service {

	private ZooKeeper zk;

	public Service(String connecthoststring, ServiceDefineInfo serviceInfo) throws IOException, KeeperException, InterruptedException {

		zk = new ZkClient(connecthoststring, serviceInfo.getTimeout(), new ServiceWatcher()).getZk();

		Gson gson = new Gson();
		zk.create("/" + serviceInfo.getServicename(), gson.toJson(serviceInfo).getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

	}

	class ServiceWatcher implements Watcher {

		public void process(WatchedEvent event) {

		}

	}

}
