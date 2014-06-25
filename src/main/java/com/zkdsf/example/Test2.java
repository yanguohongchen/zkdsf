package com.zkdsf.example;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class Test2 {

	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		// 创建一个与服务器的连接
		 ZooKeeper zk = new ZooKeeper("localhost:" + 2181, 
		        2000, new Watcher() { 
		            // 监控所有被触发的事件
		            public void process(WatchedEvent event) { 
		                System.out.println("父亲观察：已经触发了" + event.getType() + "事件！路径为："+event.getPath()); 
		            } 
		        }); 
		 
		 // 创建一个目录节点
		 zk.exists("/testRootPath", true);
		 zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE,
		   CreateMode.PERSISTENT); 
		 
//		 zk.getChildren("/testRootPath", true);
		 zk.exists("/testRootPath/testChildPathOne", true);
		 zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),
				 Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
		 
		 
		 zk.getChildren("/testRootPath", true);
		 zk.exists("/testRootPath/testChildPathOne", true);
		 zk.setData("/testRootPath/testChildPathOne", "2".getBytes(), -1);
		 
		 
		 
		 
		 
		 zk.exists("/testRootPath/testChildPathOne", true);
		 zk.delete("/testRootPath/testChildPathOne",-1); 
		 
		 zk.exists("/testRootPath", true);
		 zk.delete("/testRootPath",-1); 
		 // 关闭连接
		 zk.close();
	}
	
}
