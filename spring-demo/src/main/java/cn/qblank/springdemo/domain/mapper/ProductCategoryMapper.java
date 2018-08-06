package cn.qblank.springdemo.domain.mapper;

import cn.qblank.springdemo.domain.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface ProductCategoryMapper {
    /**
     * 通过map插入数据
     * @param map
     * @return
     */
    @Insert("insert into product_category(category_name,category_type) values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);

    /**
     * 通过对象插入数据
     * @param productCategory
     * @return
     */
    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    /**
     * 通过类型查找
     * @param categoryType
     * @return
     */
    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
        @Result(column = "category_id",property = "categoryId"),
        @Result(column = "category_name",property = "categoryName"),
        @Result(column = "category_type",property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("select * from product_category where category_name = #{categoryName}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

    /**
     * 通过类型修改
     * @param categoryName
     * @param categoryType
     * @return
     */
    @Update("update product_category set category_name=#{categoryName} where category_type=#{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,
                             @Param("categoryType") Integer categoryType);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);

    /**
     * 查询全部
     * @return
     */
    //xml方式
    List<ProductCategory> selectAllCategory();



}
