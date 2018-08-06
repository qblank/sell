package cn.qblank.springdemo.domain.mapper;

import cn.qblank.springdemo.domain.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("category_name","家乡菜");
        map.put("category_type",6);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }

    @Test
    public void insertByObject() throws Exception{
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("火鸡菜");
        productCategory.setCategoryType(7);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByCategoryType(){
        ProductCategory productCategory = mapper.findByCategoryType(1);
        Assert.assertNotEquals(null,productCategory);
    }

    @Test
    public void findCategoryByName(){
        List<ProductCategory> result = mapper.findByCategoryName("湖南菜");
        Assert.assertNotNull(result);
    }

    @Test
    public void updateByCategoryType(){
        int result = mapper.updateByCategoryType("瓜皮菜", 3);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("川菜");
        productCategory.setCategoryType(3);
        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteByType(){
        int result = mapper.deleteByCategoryType(7);
        Assert.assertEquals(1,result);
    }
    @Test
    public void selectAllCategory(){
        List<ProductCategory> productCategoryList = mapper.selectAllCategory();
        Assert.assertNotNull(productCategoryList);
    }
}