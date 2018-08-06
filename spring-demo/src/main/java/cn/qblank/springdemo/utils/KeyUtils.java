package cn.qblank.springdemo.utils;

import java.util.Random;

public class KeyUtils {
    /**
     * 生成唯一主键
     * 格式：时间毫秒数+6位随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer randomNum = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(randomNum);

    }

}
