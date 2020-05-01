package com.bp.zookeeper;

import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: daiyu
 * @Date: 1/5/20 13:46
 * @Description:
 */
public class TestZookeeper {


    private String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";

    private int sessionTimeout = 2000;

    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {

        zkClient = new ZooKeeper(connectString, sessionTimeout, event -> {

            	System.out.println("---------start----------");
				List<String> children;
				try {
					children = zkClient.getChildren("/", true);
					for (String child : children) {
						System.out.println(child);
					}
					System.out.println("---------end----------");
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        });
    }

    @Test
    public void createNode() throws KeeperException, InterruptedException{

        String path = zkClient.create("/atguigu", "dahaigezuishuai".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);

    }

    @Test
    public void getDataAndWatch() throws KeeperException, InterruptedException{

        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        Thread.sleep(1000 * 60);
    }

    @Test
    public void exist() throws KeeperException, InterruptedException{

        Stat stat = zkClient.exists("/atguigu", false);
        System.out.println(stat==null? "not exist":"exist");
    }
}
