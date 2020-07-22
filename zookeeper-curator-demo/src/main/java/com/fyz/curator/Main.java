package com.fyz.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    CuratorFramework client;

    static final String connectString = "192.168.159.128:2181,192.168.159.128:2182,192.168.159.128:2183";
    static final int sessionTimeoutMs = 60 * 1000;
    static final int connectionTimeoutMs = 15 * 1000;
    static final RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);

    @Before
    public void initConnection() {
        client = CuratorFrameworkFactory.newClient(connectString, sessionTimeoutMs, connectionTimeoutMs, retryPolicy);
        client.start();
    }

    @Test
    public void anotherInitWay() {
        client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(sessionTimeoutMs)
                .connectionTimeoutMs(connectionTimeoutMs)
                .retryPolicy(retryPolicy)
                // 绑定命令空间
                .namespace("/zoo")
                .build();
    }

    @Test
    public void addZNode() throws Exception {
        String s = this.client.create().forPath("/curator");
        System.out.println(s);
    }

    @Test
    public void addZNode1() throws Exception {
        String s = this.client.create()
                // 递归创建父节点
                .creatingParentContainersIfNeeded()
                // 指定节点类型
                .withMode(CreateMode.PERSISTENT)
                // 指定路径和内容
                .forPath("/curator/parent/child1", "一次添加测试".getBytes(StandardCharsets.UTF_8));
        System.out.println(s);
    }

    @Test
    public void removeNode() throws Exception {
        this.client.delete()
                .deletingChildrenIfNeeded()
                .forPath("/curator");
    }

    @Test
    public void getNodeData() throws Exception {
        Stat stat = new Stat();
        byte[] bytes = this.client.getData()
                .storingStatIn(stat)
                .forPath("/curator/parent/child1");
        System.out.println("stat: " + stat);
        System.out.println("data: " + new String(bytes, StandardCharsets.UTF_8));
    }

    @Test
    public void setData() throws Exception {
        Stat stat = this.client.checkExists()
                .forPath("/curator/parent/child1");
        if (Objects.nonNull(stat)) {
            this.client.setData()
                    // 指定版本
                    .withVersion(stat.getVersion())
                    .forPath("/curator/parent/child1", "一次更新测试".getBytes(StandardCharsets.UTF_8));
        }
    }

    @Test
    public void getChildren() throws Exception {
        List<String> children = this.client.getChildren().forPath("/curator/parent");
        for (String child : children) {
            System.out.println(child);
        }
    }

    @Test
    public void transaction() throws Exception {
        String path = "/curator/parent/child3";
        List<CuratorOp> curatorOps = new ArrayList<>();
        // add
        curatorOps.add(this.client.transactionOp()
                .create().forPath(path));
        // check
        curatorOps.add(this.client.transactionOp()
                .check().forPath(path));
        // set
        curatorOps.add(this.client.transactionOp()
        .setData().forPath(path, "hello world".getBytes()));
        this.client.transaction()
                .forOperations(curatorOps)
                .forEach(res -> {
                    System.err.println("-----------------");
                    System.out.println(res.getResultPath());
                    System.out.println(res.getType());
                    System.out.println(res.getResultStat());
                });
    }
}
