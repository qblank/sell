package cn.qblank.springdemo.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum{
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消");

    /** 状态 */
    private Integer code;
    private String message;

    OrderStatusEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

    /*public static OrderStatusEnum getOrderStatusEnums(Integer code){
        for (OrderStatusEnum orderStatusEnum: OrderStatusEnum.values()){
            if (orderStatusEnum.getCode().equals(code)){
                return orderStatusEnum;
            }
        }
        return null;
    }*/


}
