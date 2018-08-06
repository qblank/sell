package cn.qblank.springdemo.loggerTest;

import cn.qblank.springdemo.domain.OrderDetail;
import cn.qblank.springdemo.repository.OrderDetailRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("10010");
        orderDetail.setOrderId("1101");
        orderDetail.setProductIcon("http://img5.imgtn.bdimg.com/it/u=2170096466,3985115755&fm=27&gp=0.jpg");
        orderDetail.setProductId("1001");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(1.2));
        orderDetail.setProductQuantity(2);
        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByOrderId(){
        List<OrderDetail> orderDetails = repository.findByOrderId("1101");
        for (OrderDetail orderDetail:orderDetails){
            System.out.println(orderDetail);
        }
        Assert.assertNotEquals(0,orderDetails.size());
    }

}
