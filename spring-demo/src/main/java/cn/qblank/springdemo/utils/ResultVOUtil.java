package cn.qblank.springdemo.utils;

import cn.qblank.springdemo.VO.ResultVO;

import javax.xml.transform.Result;

public class ResultVOUtil {
    /**
     * 成功
     * @param object
     * @return
     */
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    /**
     * 成功：无参
     * @return
     */
    public static ResultVO success(){
        return success(null);
    }

    /**
     * 错误
     * @param code
     * @param message
     * @return
     */
    public static ResultVO error(Integer code,String message){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(message);
        return resultVO;
    }


}
