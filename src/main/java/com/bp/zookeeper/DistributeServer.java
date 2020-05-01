package com.bp.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.Scanner;

public class DistributeServer {

	public static void main(String[] args) throws Exception {
		
		DistributeServer server = new DistributeServer();
		server.getConnect();
		server.regist(args[0]);
		new Scanner(System.in).nextLine();
	}

	private void regist(String hostname) throws KeeperException, InterruptedException {
		
		String path = zkClient.create("/servers/server", hostname.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println(hostname +"is online ");
	}

	private String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
	private int sessionTimeout = 2000;
	private ZooKeeper zkClient;

	private void getConnect() throws IOException {
		
		zkClient = new ZooKeeper(connectString , sessionTimeout , event -> {});
	}
}
