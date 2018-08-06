package cn.qblank.springdemo.service.Impl;

import cn.qblank.springdemo.exception.SellException;
import cn.qblank.springdemo.service.RedisLock;
import cn.qblank.springdemo.service.SecKillService;
import cn.qblank.springdemo.utils.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SecKillServiceImpl implements SecKillService {
    /** 超时时间 */
    private static final int TIMOUT = 10000;

    @Autowired
    private RedisLock redisLock;

    /**
     * 中秋活动 秒杀月饼 限量100000
     */
    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;

    static {
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("1001", 100000);
        stock.put("1001", 100000);
    }

    private String queryMap(String productId) {
        return "中秋活动，月饼特价，限量份"
                + products.get(productId)
                + " 还剩：" + stock.get(productId) + " 份"
                + " 该商品成功下单用户数目："
                + orders.size() + " 人";
    }


    @Override
    public String querySecKillProductInfo(String productId) {
        return queryMap(productId);
    }

    @Override
    public void orderProductMockDiffUser(String productId) {
        long time = System.currentTimeMillis() + TIMOUT;
        //加锁
        if (!redisLock.lock(productId,String.valueOf(time))){
            throw new SellException(110,"没抢到，换个姿势再来一遍");
        }
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            //库存不足
            throw new SellException(100, "活动已经结束,请留意下次活动");
        } else {

            orders.put(KeyUtils.getUniqueKey(), productId);
            stockNum = stockNum - 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            stock.put(productId, stockNum);
        }
        //解锁
        redisLock.unlock(productId,String.valueOf(time));
    }

}
