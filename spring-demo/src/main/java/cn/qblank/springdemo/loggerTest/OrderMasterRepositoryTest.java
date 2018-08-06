package cn.qblank.springdemo.loggerTest;

import cn.qblank.springdemo.domain.OrderMaster;
import cn.qblank.springdemo.repository.OrderMasterRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    private final String OPENID = "110110";

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1102");
        orderMaster.setBuyerName("evan_qb");
        orderMaster.setBuyerPhone("182923643414");
        orderMaster.setBuyerAddress("湖南");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1101");
        orderMaster = repository.findOne(orderMaster.getOrderId());
        orderMaster.setBuyerOpenid(OPENID);
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid(){
        PageRequest request = new PageRequest(0,3);
        Page<OrderMaster> orderMasters = repository.findByBuyerOpenid(OPENID, request);
        for (OrderMaster orderMaster:orderMasters) {
            System.out.println(orderMaster);
        }
        Assert.assertNotEquals(0,orderMasters.getTotalElements());
    }

}
