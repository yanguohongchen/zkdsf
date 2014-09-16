package com.zkdsf.register;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.google.gson.Gson;
import com.zkdsf.core.ServerInstanceInfo;
import com.zkdsf.core.ZkClient;

public class ServiceInstance {

	private ZooKeeper zk;

	/**
	 * 创建服务实例
	 * 
	 * @param servicename
	 * @param serverInstanceInfo
	 * @throws KeeperException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public ServiceInstance(String connecthoststring, String servicename, ServerInstanceInfo serverInstanceInfo) throws KeeperException, InterruptedException, IOException {

		zk = new ZkClient(connecthoststring, 10000, new ServerWatcher()).getZk();
		// 先判断servicename是否存在
		Stat stat = zk.exists("/" + servicename, false);
		if (stat == null) {
			throw new RuntimeException(servicename + " is no exist,please create it!");
		}

		stat = zk.exists("/" + servicename + "/servergroup", false);
		if (stat == null) {
			zk.create("/" + servicename + "/servergroup", null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}

		// /com.baidu.serviename/192.168.253.123:8080
		String path = "/" + servicename + "/" + "servergroup" + "/" + serverInstanceInfo.getIp() + ":" + serverInstanceInfo.getPort();
		Gson gson = new Gson();
		zk.create(path, gson.toJson(serverInstanceInfo).getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
	}

	class ServerWatcher implements Watcher {
		public void process(WatchedEvent event) {
			switch (event.getType()) {

			case NodeCreated:
				break;

			case NodeDeleted:
				break;

			case NodeDataChanged:
				break;

			case NodeChildrenChanged:
				break;

			default:
				break;
			}

		}
	}

}
