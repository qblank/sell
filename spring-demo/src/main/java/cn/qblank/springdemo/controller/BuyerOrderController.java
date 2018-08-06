package cn.qblank.springdemo.controller;

import cn.qblank.springdemo.VO.ResultVO;
import cn.qblank.springdemo.converter.OrderForm2OrderDTOConverter;
import cn.qblank.springdemo.dto.OrderDTO;
import cn.qblank.springdemo.enums.ResultEnum;
import cn.qblank.springdemo.exception.SellException;
import cn.qblank.springdemo.form.OrderForm;
import cn.qblank.springdemo.service.OrderService;
import cn.qblank.springdemo.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车内容不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());

        return ResultVOUtil.success(map);

    }

    /**
     * 订单列表
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);
        return  ResultVOUtil.success(orderDTOPage.getContent());
    }

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    @GetMapping("/orderDetail")
    public ResultVO<OrderDTO> orderDetail(String orderId){
        if(StringUtils.isEmpty(orderId)){
            log.error("【查询订单详情】orderId为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = orderService.findOne(orderId);
        return ResultVOUtil.success(orderDTO);
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping("/cancelOrder")
    public ResultVO<OrderDTO> cancelOrder(String orderId){
        if (StringUtils.isEmpty(orderId)){
            log.error("【取消订单】orderDTO的orderId为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderDTO result = orderService.cancel(orderDTO);
        return ResultVOUtil.success(result);

    }
}
