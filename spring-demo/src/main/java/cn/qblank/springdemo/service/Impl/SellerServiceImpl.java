package cn.qblank.springdemo.service.Impl;

import cn.qblank.springdemo.domain.SellerInfo;
import cn.qblank.springdemo.repository.SellerInfoRepository;
import cn.qblank.springdemo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
