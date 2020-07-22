package com.fyz.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AppServer {
    static final String SERVER_LIST = "192.168.159.128:2181,192.168.159.128:2182,192.168.159.128:2183";
    static final int TIMEOUT = 5000;
    private ZooKeeper client;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        AppServer appServer = new AppServer();
        // 1.建立zookeeper连接
        appServer.getConnection();
        // 2.注册
        appServer.registry(args);
        // 3.业务
        appServer.business();
    }

    private void business() throws InterruptedException {
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
    }

    private void registry(String... hosts) throws KeeperException, InterruptedException {
        for (String host : hosts) {
            String s = this.client.create("/servers/appServer", host.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("服务器上线 - " + s);
        }
    }

    private void getConnection() throws IOException {
        this.client = new ZooKeeper(SERVER_LIST, TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event);
            }
        });
    }
}
