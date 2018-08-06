package cn.qblank.springdemo.service;

import cn.qblank.springdemo.domain.ProductCategory;

import java.util.List;

public interface CategoryService {
    /**
     * 通过编号查询
     * @param categoryId
     * @return
     */
    ProductCategory findOne(Integer categoryId);

    /**
     * 查询所有
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 通过类型列表查询类型
     * @param categoryTypeList
     * @return
     */

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 新增类别
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
