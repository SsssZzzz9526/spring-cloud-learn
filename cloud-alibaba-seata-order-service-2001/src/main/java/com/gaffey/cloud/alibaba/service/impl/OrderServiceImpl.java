package com.gaffey.cloud.alibaba.service.impl;

import com.gaffey.cloud.alibaba.dao.OrderMapper;
import com.gaffey.cloud.alibaba.entity.Order;
import com.gaffey.cloud.alibaba.service.AccountService;
import com.gaffey.cloud.alibaba.service.OrderService;
import com.gaffey.cloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    @Override
    public Order insert(Order record) {
        // 添加订单
        record.setStatus(0);
        orderMapper.insert(record);
        // 扣除库存
        storageService.updateStorage(record.getProductId(), record.getCount());
        // 扣除余额
        accountService.updateAccount(record.getUserId(), record.getMoney());
        // 更新订单状态
        orderMapper.update(record.getId(), 1);
        return record;
    }

}
