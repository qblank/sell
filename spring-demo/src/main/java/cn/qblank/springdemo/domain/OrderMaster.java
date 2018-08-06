package cn.qblank.springdemo.domain;

import cn.qblank.springdemo.enums.OrderStatusEnum;
import cn.qblank.springdemo.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单主表
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /** 支付状态:默认为 0：未支付 */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
