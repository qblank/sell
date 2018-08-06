package cn.qblank.springdemo.loggerTest;

import cn.qblank.springdemo.domain.ProductCategory;
import cn.qblank.springdemo.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
        ProductCategory productCategory = repository.findOne(1);
        System.out.println(productCategory);
    }

    @Test
    public void saveOneTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("鲁菜");
        productCategory.setCategoryType(4);
        repository.save(productCategory);
    }

    @Test
    public void updateOneTest(){
        ProductCategory productCategory = repository.findOne(3);
        productCategory.setCategoryType(3);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotEquals(null,result);
    }

    @Test
    public void findAll(){
        List<ProductCategory> productCategories = repository.findAll();
        for (ProductCategory productCategory:productCategories) {
            System.out.println(productCategory);
        }
    }
}
