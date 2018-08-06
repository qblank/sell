package cn.qblank.springdemo.dto;

import cn.qblank.springdemo.domain.OrderDetail;
import cn.qblank.springdemo.enums.OrderStatusEnum;
import cn.qblank.springdemo.enums.PayStatusEnum;
import cn.qblank.springdemo.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    /** 订单id*/
    @Id
    private String orderId;
    /** 卖家名字 */
    private String buyerName;
    /** 卖家联系电话 */
    private String buyerPhone;
    /** 卖家地址 */
    private String buyerAddress;
    /** 卖家微信open_id */
    private String buyerOpenid;
    /** 订单总金额 */
    private BigDecimal orderAmount;
    /** 订单状态，默认为 0：新下单 */
    private Integer orderStatus;
    /** 支付状态:默认为 0：未支付 */
    private Integer payStatus;
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

    List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
