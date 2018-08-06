package cn.qblank.springdemo.repository;


import cn.qblank.springdemo.domain.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
    /**
     * 通过openid查找
     * @param openid
     * @return
     */
    SellerInfo findByOpenid(String openid);

}
