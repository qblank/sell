package cn.qblank.springdemo.handler;

import cn.qblank.springdemo.VO.ResultVO;
import cn.qblank.springdemo.exception.SellException;
import cn.qblank.springdemo.utils.ResultVOUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SellExceptionHandler {
    /**
     * 处理异常
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }

    /*@ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleResponseBankException(){

    }*/
}
