package cn.qblank.springdemo.service;

import cn.qblank.springdemo.domain.SellerInfo;

public interface SellerService {
    /**
     * 通过open查找用户信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
