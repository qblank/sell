package cn.qblank.springdemo.service.Impl;

import cn.qblank.springdemo.domain.OrderDetail;
import cn.qblank.springdemo.dto.CartDTO;
import cn.qblank.springdemo.dto.OrderDTO;
import cn.qblank.springdemo.enums.OrderStatusEnum;
import cn.qblank.springdemo.enums.PayStatusEnum;
import cn.qblank.springdemo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    private final String BUYER_OPENID = "110110";
    private final String ORDER_ID = "1531976858259856122";

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("evan_qb");
        orderDTO.setBuyerAddress("广东");
        orderDTO.setBuyerPhone("1541256234");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1001");
        orderDetail.setProductQuantity(1);

        OrderDetail orderDetail12 = new OrderDetail();
        orderDetail12.setProductId("1002");
        orderDetail12.setProductQuantity(2);

        orderDetailList.add(orderDetail12);
        orderDetailList.add(orderDetail);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】 result={}",result);
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】 result = {}",result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0, 4);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, request);
        for (OrderDTO orderDTO :
                orderDTOPage) {
            log.info(orderDTO.toString());
        }
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel(){
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO cancelReslt = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),cancelReslt.getOrderStatus());
    }

    @Test
    public void finish(){
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid(){
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }

    @Test
    public void list(){
        PageRequest request = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }
}