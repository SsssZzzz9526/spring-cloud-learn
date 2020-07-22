package com.fyz.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AppClient {
    static final String SERVER_LIST = "192.168.159.128:2181,192.168.159.128:2182,192.168.159.128:2183";
    static final int TIMEOUT = 5000;
    private ZooKeeper client;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        AppClient appClient = new AppClient();
        // 1.建立连接
        appClient.getConnection();
        // 2.获取服务器信息
        appClient.getServers();
        // 3.业务
        appClient.business();
    }

    private void getServers() throws KeeperException, InterruptedException {
        List<String> children = this.client.getChildren("/servers", true);
        List<String> serverHost = new ArrayList<>();
        for (String child : children) {
            String path = "/servers/" + child;
            Stat exists = this.client.exists(path, false);
            if (Objects.nonNull(exists)) {
                byte[] data = this.client.getData(path, false, exists);
                serverHost.add(new String(data));
            }
        }
        System.err.println(serverHost);
    }

    private void business() throws InterruptedException {
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
    }

    private void getConnection() throws IOException {
        this.client = new ZooKeeper(SERVER_LIST, TIMEOUT, event -> {
            if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                try {
                    getServers();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
