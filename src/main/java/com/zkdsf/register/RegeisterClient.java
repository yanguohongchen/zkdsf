package com.zkdsf.register;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.zkdsf.core.ServerInstanceInfo;
import com.zkdsf.core.SubscribeInfo;
import com.zkdsf.core.ZkClient;

public class RegeisterClient {

	private final static Logger logger = LoggerFactory
			.getLogger(RegeisterClient.class.getName());

	public static Map<String, ServerInstanceInfo> serverInstanceInfos = new HashMap<String, ServerInstanceInfo>();

	private ZooKeeper zk;

	private Gson gson = new Gson();

	private String servicename;

	private ServerDataWatcher serverDataWatcher = new ServerDataWatcher();

	public RegeisterClient(String connecthoststring, SubscribeInfo subscribeInfo)
			throws KeeperException, InterruptedException, IOException {

		servicename = subscribeInfo.getServicename();

		//先判断servicename是否存在
		Stat stat = zk.exists("/"+servicename, false);
		if(stat==null){
			throw new RuntimeException(servicename+" is no exist,please create it!");
		}
		
		
		zk = new ZkClient(connecthoststring, 10000,
				new RegeisterClientWatcher()).getZk();

		String path = "/" + subscribeInfo.getServicename() + "/"
				+ "clientgroup" + "/"
				+ subscribeInfo.getRegisterclienthostname() + ":"
				+ subscribeInfo.getIp();

		zk.create(path, gson.toJson(subscribeInfo).getBytes(),
				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

		// 获取服务列表。并监听
		updateServerList("/" + subscribeInfo.getServicename() + "/"
				+ "servergroup");

	}

	public synchronized void updateServerList(String path)
			throws KeeperException, InterruptedException {
		List<String> childs = zk.getChildren(path, serverDataWatcher);
		Stat stat = new Stat();
		serverInstanceInfos.clear();
		for (String child : childs) {
			serverInstanceInfos.put(child, gson.fromJson(
					new String(zk.getData(path, serverDataWatcher, stat)),
					ServerInstanceInfo.class));
		}
		zk.getChildren(path, serverDataWatcher);
	}

	class ServerDataWatcher implements Watcher {

		public void process(WatchedEvent event) {

			try {
				updateServerList("/" + servicename + "/" + "servergroup");
			} catch (KeeperException e) {
				logger.error(e.getMessage(), e);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

	class RegeisterClientWatcher implements Watcher {

		public void process(WatchedEvent event) {
		}
	}

}
