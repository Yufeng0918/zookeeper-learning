package com.bp.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistributeClient {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		
		DistributeClient client = new DistributeClient();
		client.getConnect();
		client.getChlidren();
		client.business();
		
	}

	private void business() throws InterruptedException {
		Thread.sleep(Long.MAX_VALUE);
	}

	private void getChlidren() throws KeeperException, InterruptedException {
		
		List<String> children = zkClient.getChildren("/servers", true);
		
		ArrayList<String> hosts = new ArrayList<>();
		for (String child : children) {
			
			byte[] data = zkClient.getData("/servers/"+child, false, null);
			hosts.add(new String(data));
		}
		System.out.println(hosts);
		
	}

	private String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
	private int sessionTimeout = 2000;
	private ZooKeeper zkClient;

	private void getConnect() throws IOException {
	
		zkClient = new ZooKeeper(connectString , sessionTimeout ,  event ->  {
				try {
					getChlidren();
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		});
	}
}
