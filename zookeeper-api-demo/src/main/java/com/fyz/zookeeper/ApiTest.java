package com.fyz.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ApiTest {
    static final String SERVER_LIST = "192.168.159.128:2181,192.168.159.128:2182,192.168.159.128:2183";
    static final int TIMEOUT = 5000;
    static ZooKeeper client;

    @Before
    public void initClient() {
        try {
            client = new ZooKeeper(SERVER_LIST, TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("------------------");
                    System.out.println(watchedEvent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createZNode() {
        try {
            String s = client.create("/zoo/cat", "动物园的猫".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(s);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeZNode() {
        try {
            Stat exists = client.exists("/zoo/cat", false);
            if (Objects.nonNull(exists)) {
                client.delete("/zoo/cat", exists.getVersion());
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听节点数据变化
     */
    @Test
    public void getZNode() {
        try {
            Stat exists = client.exists("/zoo/cat", false);
            if (Objects.nonNull(exists)) {
                byte[] data = client.getData("/zoo/cat", new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        System.err.println("数据修改回调");
                    }
                }, exists);
                System.out.println(new String(data, StandardCharsets.UTF_8));
                // 在子线程中修改节点数据
                Thread changeData = new Thread(() -> {
                    try {
                    Stat exists1 = client.exists("/zoo/cat", false);
                    if (Objects.nonNull(exists1)) {
                        // 修改数据
                        Stat stat = client.setData("/zoo/cat", "动物园里的猫丢了".getBytes(StandardCharsets.UTF_8), exists1.getVersion());
                        System.err.println(stat.getVersion() > exists1.getVersion() ? "修改成功" : "修改失败");
                    }
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                changeData.start();
                changeData.join();
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听节点数据变化 使用自定义监听器
     */
    @Test
    public void getZNodeWithCustomWatch() {
        try {
            byte[] data = client.getData("/zoo", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.err.println(event);
                }
            }, null);
            System.out.println(new String(data, StandardCharsets.UTF_8));

            TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听节点变化
     */
    @Test
    public void listZNode() {
        try {
            List<String> children = client.getChildren("/zoo", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.err.println("节点变更回调");
                }
            });
            for (String child : children) {
                System.out.println(child);
            }
            // 在子线程中修改节点数据
            Thread changeZNode = new Thread(() -> {
                try {
                    Stat exists1 = client.exists("/zoo", false);
                    if (Objects.nonNull(exists1)) {
                        // 创建子节点
                        String s = client.create("/zoo/pig", "动物园的猪".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                        System.err.println(!s.isEmpty() ? "创建成功" : "创建失败");
                    }
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            changeZNode.start();
            changeZNode.join();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断节点是否存在
     */
    @Test
    public void existZNode() {
        try {
            Stat exists = client.exists("/zoo/cat", false);
            System.err.println(exists);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
