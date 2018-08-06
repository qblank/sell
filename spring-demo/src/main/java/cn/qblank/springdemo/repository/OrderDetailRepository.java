package cn.qblank.springdemo.repository;

import cn.qblank.springdemo.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    /**
     * 通过订单编号查找订单
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

}
