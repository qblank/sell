package cn.qblank.springdemo.loggerTest;


import cn.qblank.springdemo.domain.ProductInfo;
import cn.qblank.springdemo.enums.ProductStatusEnum;
import cn.qblank.springdemo.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductService productService;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1003");
        productInfo.setProductName("百合薏仁粥");
        productInfo.setProductPrice(new BigDecimal(6.20));
        productInfo.setProductStock(10);
        productInfo.setProductDescription("很好吃百合薏仁粥");
        productInfo.setProductIcon("http://recipe0.hoto.cn/pic/recipe/l/98/bd/900504_76f733.jpg");
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        productInfo.setCategoryType(3);
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne(){
        ProductInfo productInfo = productService.findOne("1001");
        System.out.println(productInfo);
    }

    @Test
    public void findAll(){
        PageRequest request = new PageRequest(0,2);
        Page<ProductInfo> productInfoPages = productService.findAll(request);
        for (ProductInfo productInfoPage:productInfoPages){
            System.out.println(productInfoPage);
        }
    }

    @Test
    public void findByProductStatus(){
        List<ProductInfo> productInfoList = productService.findUpAll();
        for(ProductInfo productInfo: productInfoList){
            System.out.println(productInfo);
        }
    }
}
