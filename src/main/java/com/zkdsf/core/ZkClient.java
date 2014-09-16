package com.zkdsf.core;

import java.io.IOException;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZkClient {

	private ZooKeeper zk;
	

	/**
	 * 连接到zkserver集群
	 * 
	 * @param hosts
	 *            zk列表(192.138.23.1:2181,123.55.45.15:2181)
	 * @param timeout
	 *            超时时间
	 * @throws IOException
	 */
	public void connect(String hosts, int timeout, Watcher watcher) throws IOException {
		zk = new ZooKeeper(hosts, timeout, watcher);
	}

	public ZkClient(String hosts, int timeout, Watcher watcher) throws IOException {
		this.connect(hosts, timeout, watcher);
	}

	public ZooKeeper getZk() {
		return zk;
	}

}
